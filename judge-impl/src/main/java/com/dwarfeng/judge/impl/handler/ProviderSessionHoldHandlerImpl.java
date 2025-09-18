package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.exception.ProviderNotExistsException;
import com.dwarfeng.judge.stack.handler.Provider;
import com.dwarfeng.judge.stack.handler.ProviderLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.ProviderSession;
import com.dwarfeng.judge.stack.handler.ProviderSessionHoldHandler;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class ProviderSessionHoldHandlerImpl implements ProviderSessionHoldHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderSessionHoldHandlerImpl.class);

    private final ProviderLocalCacheHandler providerLocalCacheHandler;
    private final Lock lock = new ReentrantLock();
    private final Map<LongIdKey, ProviderSession> providerSessionMap = new HashMap<>();

    public ProviderSessionHoldHandlerImpl(
            ProviderLocalCacheHandler providerLocalCacheHandler
    ) {
        this.providerLocalCacheHandler = providerLocalCacheHandler;
    }

    @Override
    @BehaviorAnalyse
    public ProviderSession get(LongIdKey providerInfoKey) throws HandlerException {
        lock.lock();
        try {
            return get0(providerInfoKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }

    private ProviderSession get0(LongIdKey providerInfoKey) throws Exception {
        if (providerSessionMap.containsKey(providerInfoKey)) {
            LOGGER.debug("提供器会话已经存在, 直接返回该提供器会话... ");
            return providerSessionMap.get(providerInfoKey);
        }
        if (!providerLocalCacheHandler.exists(providerInfoKey)) {
            LOGGER.debug("提供器不存在, 将抛出异常... ");
            throw new ProviderNotExistsException(providerInfoKey);
        }
        LOGGER.info("提供器存在, 提供器会话不存在, 新建提供器会话... ");
        Provider provider = providerLocalCacheHandler.get(providerInfoKey);
        LOGGER.info("通过提供器构建提供器会话...");
        ProviderSession providerSession = provider.newSession();
        LOGGER.info("提供器会话构建成功");
        LOGGER.info("提供器会话初始化成功");
        LOGGER.info("提供器会话: {}", providerSession);
        LOGGER.debug("打开提供器会话...");
        providerSession.openSession();
        LOGGER.debug("将构建的提供器会话放入提供器会话映射中...");
        providerSessionMap.put(providerInfoKey, providerSession);
        return providerSession;
    }

    @Override
    @BehaviorAnalyse
    public void closeAndClear() throws HandlerException {
        lock.lock();
        try {
            closeAndClear0();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }

    private void closeAndClear0() {
        for (ProviderSession providerSession : providerSessionMap.values()) {
            try {
                providerSession.closeSession();
            } catch (Exception e) {
                LOGGER.warn("关闭提供器会话时发生异常, 提供器会话将不会被关闭, 异常信息如下: ", e);
            }
        }
        providerSessionMap.clear();
    }
}

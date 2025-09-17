package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.SinkerVariableInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableInspectResult;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableUpsertInfo;
import com.dwarfeng.judge.stack.exception.SinkerNotExistsException;
import com.dwarfeng.judge.stack.handler.*;
import com.dwarfeng.judge.stack.struct.SectionBinding;
import com.dwarfeng.judge.stack.struct.SinkerBinding;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class SinkerSessionHoldHandlerImpl implements SinkerSessionHoldHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SinkerSessionHoldHandlerImpl.class);

    private final ApplicationContext ctx;

    private final SinkerLocalCacheHandler sinkerLocalCacheHandler;
    private final SectionBindingLocalCacheHandler sectionBindingLocalCacheHandler;
    private final SinkerBindingLocalCacheHandler sinkerBindingLocalCacheHandler;
    private final SinkerVariableOperateHandler sinkerVariableOperateHandler;

    private final Lock lock = new ReentrantLock();
    private final Map<LongIdKey, SinkerSession> sinkerSessionMap = new HashMap<>();

    public SinkerSessionHoldHandlerImpl(
            ApplicationContext ctx,
            SinkerLocalCacheHandler sinkerLocalCacheHandler,
            SectionBindingLocalCacheHandler sectionBindingLocalCacheHandler,
            SinkerBindingLocalCacheHandler sinkerBindingLocalCacheHandler,
            SinkerVariableOperateHandler sinkerVariableOperateHandler
    ) {
        this.ctx = ctx;
        this.sinkerLocalCacheHandler = sinkerLocalCacheHandler;
        this.sectionBindingLocalCacheHandler = sectionBindingLocalCacheHandler;
        this.sinkerBindingLocalCacheHandler = sinkerBindingLocalCacheHandler;
        this.sinkerVariableOperateHandler = sinkerVariableOperateHandler;
    }

    @Override
    @BehaviorAnalyse
    public SinkerSession get(LongIdKey sinkerInfoKey) throws HandlerException {
        lock.lock();
        try {
            return get0(sinkerInfoKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }

    private SinkerSession get0(LongIdKey sinkerInfoKey) throws Exception {
        if (sinkerSessionMap.containsKey(sinkerInfoKey)) {
            LOGGER.debug("下沉器会话已经存在, 直接返回该下沉器会话... ");
            return sinkerSessionMap.get(sinkerInfoKey);
        }
        if (!sinkerLocalCacheHandler.exists(sinkerInfoKey)) {
            LOGGER.debug("下沉器不存在, 将抛出异常... ");
            throw new SinkerNotExistsException(sinkerInfoKey);
        }
        LOGGER.info("下沉器存在, 下沉器会话不存在, 新建下沉器会话... ");
        Sinker sinker = sinkerLocalCacheHandler.get(sinkerInfoKey);
        LOGGER.info("通过下沉器构建下沉器会话...");
        SinkerSession sinkerSession = sinker.newSession();
        LOGGER.info("下沉器会话构建成功");
        LOGGER.info("构建下沉器会话上下文...");
        SinkerSession.Context context = ctx.getBean(
                InternalSinkerSessionContext.class,
                sinkerInfoKey, sectionBindingLocalCacheHandler, sinkerBindingLocalCacheHandler,
                sinkerVariableOperateHandler
        );
        LOGGER.info("下沉器会话上下文构建成功, 下沉器会话上下文: {}", context);
        LOGGER.info("初始化下沉器会话...");
        sinkerSession.init(context);
        LOGGER.info("下沉器会话初始化成功");
        LOGGER.info("下沉器会话: {}", sinkerSession);
        LOGGER.debug("打开下沉器会话...");
        sinkerSession.openSession();
        LOGGER.debug("将构建的下沉器会话放入下沉器会话映射中...");
        sinkerSessionMap.put(sinkerInfoKey, sinkerSession);
        return sinkerSession;
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
        for (SinkerSession sinkerSession : sinkerSessionMap.values()) {
            try {
                sinkerSession.closeSession();
            } catch (Exception e) {
                LOGGER.warn("关闭下沉器会话时发生异常, 下沉器会话将不会被关闭, 异常信息如下: ", e);
            }
        }
        sinkerSessionMap.clear();
    }

    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Component
    public static class InternalSinkerSessionContext implements SinkerSession.Context {

        private final LongIdKey sinkerInfoKey;

        private final SectionBindingLocalCacheHandler sectionBindingLocalCacheHandler;
        private final SinkerBindingLocalCacheHandler sinkerBindingLocalCacheHandler;
        private final SinkerVariableOperateHandler sinkerVariableOperateHandler;

        public InternalSinkerSessionContext(
                LongIdKey sinkerInfoKey,
                SectionBindingLocalCacheHandler sectionBindingLocalCacheHandler,
                SinkerBindingLocalCacheHandler sinkerBindingLocalCacheHandler,
                SinkerVariableOperateHandler sinkerVariableOperateHandler
        ) {
            this.sinkerInfoKey = sinkerInfoKey;
            this.sectionBindingLocalCacheHandler = sectionBindingLocalCacheHandler;
            this.sinkerBindingLocalCacheHandler = sinkerBindingLocalCacheHandler;
            this.sinkerVariableOperateHandler = sinkerVariableOperateHandler;
        }

        @Nonnull
        @Override
        @BehaviorAnalyse
        public LongIdKey getSinkerInfoKey() {
            return sinkerInfoKey;
        }

        @Nullable
        @Override
        @BehaviorAnalyse
        public SectionBinding getSectionBinding(LongIdKey sectionKey) throws Exception {
            return sectionBindingLocalCacheHandler.get(sectionKey);
        }

        @Nullable
        @Override
        @BehaviorAnalyse
        public SinkerBinding getSinkerBinding(LongIdKey sinkerKey) throws Exception {
            return sinkerBindingLocalCacheHandler.get(sinkerKey);
        }

        @Nullable
        @Override
        @BehaviorAnalyse
        public SinkerVariableInspectResult inspectVariable(SinkerVariableInspectInfo info) throws Exception {
            return sinkerVariableOperateHandler.inspect(info);
        }

        @Override
        @BehaviorAnalyse
        public void upsertVariable(SinkerVariableUpsertInfo info) throws Exception {
            sinkerVariableOperateHandler.upsert(info);
        }

        @Override
        @BehaviorAnalyse
        public void removeVariable(SinkerVariableRemoveInfo info) throws Exception {
            sinkerVariableOperateHandler.remove(info);
        }
    }
}

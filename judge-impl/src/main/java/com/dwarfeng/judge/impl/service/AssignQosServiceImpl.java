package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.AssignInfo;
import com.dwarfeng.judge.stack.handler.AssignHandler;
import com.dwarfeng.judge.stack.handler.AssignLocalCacheHandler;
import com.dwarfeng.judge.stack.service.AssignQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class AssignQosServiceImpl implements AssignQosService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssignQosServiceImpl.class);

    @Autowired
    private AssignHandler assignHandler;
    @Autowired
    private AssignLocalCacheHandler assignLocalCacheHandler;

    @Autowired
    private ServiceExceptionMapper sem;

    private final Lock lock = new ReentrantLock();
    private boolean startFlag = false;

    @PreDestroy
    private void dispose() throws ServiceException {
        stop();
    }

    @Override
    public void start() throws ServiceException {
        lock.lock();
        try {
            if (!startFlag) {
                LOGGER.info("开启指派服务...");
                assignHandler.online();
                startFlag = true;
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("开启指派服务时发生异常",
                    LogLevel.WARN, sem, e
            );
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void stop() throws ServiceException {
        lock.lock();
        try {
            if (startFlag) {
                LOGGER.info("关闭指派服务...");
                assignHandler.offline();
                try {
                    Thread.sleep(500);
                } catch (Exception ignored) {
                }
                startFlag = false;
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("关闭指派服务时发生异常",
                    LogLevel.WARN, sem, e
            );
        } finally {
            lock.unlock();
        }
    }

    @Override
    public AssignInfo getContext(LongIdKey sectionKey) throws ServiceException {
        lock.lock();
        try {
            return assignLocalCacheHandler.getAssignInfo(sectionKey);
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logAndThrow("从本地缓存中获取指派信息时发生异常",
                    LogLevel.WARN, sem, e
            );
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        lock.lock();
        try {
            assignLocalCacheHandler.clear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("清除本地缓存时发生异常",
                    LogLevel.WARN, sem, e
            );
        } finally {
            lock.unlock();
        }
    }
}

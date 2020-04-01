package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.handler.ConsumeHandler;
import com.dwarfeng.judge.stack.handler.EvaluateHandler;
import com.dwarfeng.judge.stack.handler.EvaluateLocalCacheHandler;
import com.dwarfeng.judge.stack.service.EvaluateQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
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
public class EvaluateQosServiceImpl implements EvaluateQosService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluateQosServiceImpl.class);

    @Autowired
    private ConsumeHandler consumeHandler;
    @Autowired
    private EvaluateHandler evaluateHandler;
    @Autowired
    private EvaluateLocalCacheHandler evaluateLocalCacheHandler;

    @Autowired
    private ServiceExceptionMapper sem;

    private final Lock lock = new ReentrantLock();
    private boolean startFlag = false;

    @PreDestroy
    private void dispose() throws ServiceException {
        stopEvaluate();
    }

    @Override
    public void startEvaluate() throws ServiceException {
        lock.lock();
        try {
            if (!startFlag) {
                LOGGER.info("开启评估服务...");
                consumeHandler.start();
                evaluateHandler.enable();
                startFlag = true;
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("开启评估服务时发生异常",
                    LogLevel.WARN, sem, e
            );
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void stopEvaluate() throws ServiceException {
        lock.lock();
        try {
            if (startFlag) {
                LOGGER.info("关闭判断服务...");
                evaluateHandler.disable();
                try {
                    Thread.sleep(1000);
                } catch (Exception ignored) {
                }
                consumeHandler.stop();
                startFlag = false;
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("关闭评估服务时发生异常",
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
            evaluateLocalCacheHandler.clear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("清除本地缓存时发生异常",
                    LogLevel.WARN, sem, e
            );
        } finally {
            lock.unlock();
        }
    }
}
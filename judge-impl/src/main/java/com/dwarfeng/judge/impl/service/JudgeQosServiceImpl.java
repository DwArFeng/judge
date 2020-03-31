package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.handler.*;
import com.dwarfeng.judge.stack.service.JudgeQosService;
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
public class JudgeQosServiceImpl implements JudgeQosService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JudgeQosServiceImpl.class);

    @Autowired
    private ConsumeHandler consumeHandler;
    @Autowired
    private EvaluateHandler evaluateHandler;
    @Autowired
    private AssignHandler assignHandler;
    @Autowired
    private AssignLocalCacheHandler assignLocalCacheHandler;
    @Autowired
    private EvaluateLocalCacheHandler evaluateLocalCacheHandler;

    @Autowired
    private ServiceExceptionMapper sem;

    private final Lock lock = new ReentrantLock();

    @PreDestroy
    private void dispose() throws ServiceException {
        stopJudge();
    }

    @Override
    public void startJudge() throws ServiceException {
        lock.lock();
        try {
            LOGGER.info("开启判断服务...");
            consumeHandler.start();
            evaluateHandler.enable();
            assignHandler.online();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("开启判断服务时发生异常",
                    LogLevel.WARN, sem, e
            );
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void stopJudge() throws ServiceException {
        lock.lock();
        try {
            LOGGER.info("关闭判断服务...");
            assignHandler.offline();
            try {
                Thread.sleep(500);
            } catch (Exception ignored) {
            }
            evaluateHandler.disable();
            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {
            }
            consumeHandler.stop();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("关闭判断服务时发生异常",
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

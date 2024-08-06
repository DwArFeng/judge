package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.ConsumerStatus;
import com.dwarfeng.judge.stack.bean.EvaluateInfo;
import com.dwarfeng.judge.stack.handler.ConsumeHandler;
import com.dwarfeng.judge.stack.handler.EvaluateHandler;
import com.dwarfeng.judge.stack.handler.EvaluateLocalCacheHandler;
import com.dwarfeng.judge.stack.service.EvaluateQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class EvaluateQosServiceImpl implements EvaluateQosService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluateQosServiceImpl.class);

    private final ConsumeHandler consumeHandler;
    private final EvaluateHandler evaluateHandler;
    private final EvaluateLocalCacheHandler evaluateLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    private final Lock lock = new ReentrantLock();
    private boolean startFlag = false;

    public EvaluateQosServiceImpl(
            ConsumeHandler consumeHandler,
            EvaluateHandler evaluateHandler,
            EvaluateLocalCacheHandler evaluateLocalCacheHandler,
            ServiceExceptionMapper sem
    ) {
        this.consumeHandler = consumeHandler;
        this.evaluateHandler = evaluateHandler;
        this.evaluateLocalCacheHandler = evaluateLocalCacheHandler;
        this.sem = sem;
    }

    @PreDestroy
    private void dispose() throws ServiceException {
        stop();
    }

    @Override
    public void start() throws ServiceException {
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
    public void stop() throws ServiceException {
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
    public EvaluateInfo getContext(LongIdKey sectionKey) throws ServiceException {
        lock.lock();
        try {
            return evaluateLocalCacheHandler.getEvaluateInfo(sectionKey);
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logAndThrow("从本地缓存中获取评估信息时发生异常",
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

    @SuppressWarnings("DuplicatedCode")
    @Override
    public ConsumerStatus getConsumerStatus() throws ServiceException {
        lock.lock();
        try {
            return new ConsumerStatus(
                    consumeHandler.bufferedSize(),
                    consumeHandler.getBufferSize(),
                    consumeHandler.getThread(),
                    consumeHandler.isIdle()
            );
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("获取消费者状态时发生异常",
                    LogLevel.WARN, sem, e
            );
        } finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void setConsumerParameters(Integer bufferSize, Integer thread) throws ServiceException {
        lock.lock();
        try {
            consumeHandler.setBufferSize(
                    Objects.isNull(bufferSize) ? consumeHandler.getBufferSize() : bufferSize
            );
            consumeHandler.setThread(
                    Objects.isNull(thread) ? consumeHandler.getThread() : thread
            );
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("设置消费者参数时发生异常",
                    LogLevel.WARN, sem, e
            );
        } finally {
            lock.unlock();
        }
    }
}

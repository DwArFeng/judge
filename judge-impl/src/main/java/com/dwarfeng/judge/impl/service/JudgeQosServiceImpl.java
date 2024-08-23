package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.AssignInfo;
import com.dwarfeng.judge.stack.bean.ConsumerStatus;
import com.dwarfeng.judge.stack.bean.EvaluateInfo;
import com.dwarfeng.judge.stack.bean.JudgeInfo;
import com.dwarfeng.judge.stack.handler.*;
import com.dwarfeng.judge.stack.service.JudgeQosService;
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
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class JudgeQosServiceImpl implements JudgeQosService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JudgeQosServiceImpl.class);

    private final ConsumeHandler consumeHandler;
    private final EvaluateHandler evaluateHandler;
    private final AssignHandler assignHandler;
    private final AssignLocalCacheHandler assignLocalCacheHandler;
    private final EvaluateLocalCacheHandler evaluateLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    private final Lock lock = new ReentrantLock();
    private boolean startFlag = false;

    public JudgeQosServiceImpl(
            ConsumeHandler consumeHandler,
            EvaluateHandler evaluateHandler,
            AssignHandler assignHandler,
            AssignLocalCacheHandler assignLocalCacheHandler,
            EvaluateLocalCacheHandler evaluateLocalCacheHandler,
            ServiceExceptionMapper sem
    ) {
        this.consumeHandler = consumeHandler;
        this.evaluateHandler = evaluateHandler;
        this.assignHandler = assignHandler;
        this.assignLocalCacheHandler = assignLocalCacheHandler;
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
                LOGGER.info("开启判断服务...");
                consumeHandler.start();
                evaluateHandler.enable();
                assignHandler.online();
                startFlag = true;
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("开启判断服务时发生异常", LogLevel.WARN, e, sem);
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
                startFlag = false;
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("关闭判断服务时发生异常", LogLevel.WARN, e, sem);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public JudgeInfo getContext(LongIdKey sectionKey) throws ServiceException {
        lock.lock();
        try {
            AssignInfo assignInfo = assignLocalCacheHandler.getAssignInfo(sectionKey);
            EvaluateInfo evaluateInfo = evaluateLocalCacheHandler.getEvaluateInfo(sectionKey);
            if (Objects.isNull(assignInfo) && Objects.isNull(evaluateInfo)) {
                return null;
            }
            JudgeInfo judgeInfo = new JudgeInfo();
            judgeInfo.setSection(
                    Optional.ofNullable(assignInfo).map(AssignInfo::getSection).orElse(evaluateInfo.getSection()));
            judgeInfo.setDriverMap(
                    Optional.of(assignInfo).map(AssignInfo::getDriverMap).orElse(Collections.emptyMap()));
            judgeInfo.setJudgerMap(
                    Optional.of(evaluateInfo).map(EvaluateInfo::getJudgerMap).orElse(Collections.emptyMap()));
            return judgeInfo;
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse("从本地缓存中获取判断信息时发生异常", LogLevel.WARN, e, sem);
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
            throw ServiceExceptionHelper.logParse("清除本地缓存时发生异常", LogLevel.WARN, e, sem);
        } finally {
            lock.unlock();
        }
    }

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
            throw ServiceExceptionHelper.logParse("获取消费者状态时发生异常", LogLevel.WARN, e, sem);
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
            throw ServiceExceptionHelper.logParse("设置消费者参数时发生异常", LogLevel.WARN, e, sem);
        } finally {
            lock.unlock();
        }
    }
}

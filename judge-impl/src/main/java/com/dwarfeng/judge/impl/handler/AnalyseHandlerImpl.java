package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dutil.basic.str.UUIDUtil;
import com.dwarfeng.judge.stack.exception.JudgeWorkDisabledException;
import com.dwarfeng.judge.stack.exception.SectionNotExistsException;
import com.dwarfeng.judge.stack.handler.AnalyseHandler;
import com.dwarfeng.judge.stack.handler.ConsumeHandler;
import com.dwarfeng.judge.stack.handler.JudgeLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.JudgeLocalCacheHandler.JudgeContext;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class AnalyseHandlerImpl implements AnalyseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyseHandlerImpl.class);

    @Autowired
    private JudgeLocalCacheHandler localCacheHandler;
    @Autowired
    private ConsumeHandler consumeHandler;

    private final Lock lock = new ReentrantLock();
    private boolean enabledFlag = false;

    @Override
    public boolean isEnabled() {
        lock.lock();
        try {
            return enabledFlag;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void enable() {
        lock.lock();
        try {
            if (!enabledFlag) {
                LOGGER.info("启用 record handler...");
                enabledFlag = true;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void disable() {
        lock.lock();
        try {
            if (enabledFlag) {
                LOGGER.info("禁用 record handler...");
                enabledFlag = false;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    @BehaviorAnalyse
    public void analyse(LongIdKey sectionKey) throws HandlerException {
        String uuid = UUIDUtil.toDenseString(UUID.randomUUID());
        try {
            // 判断是否允许判断，如果不允许，直接报错。
            if (!isEnabled()) {
                throw new JudgeWorkDisabledException();
            }

            // 0. 记录日志，准备工作。
            LOGGER.debug("驱动器使能, 驱动判断指定部件: " + sectionKey);

            // 1. 获取 JudgeContext。
            JudgeContext judgeContext = localCacheHandler.getJudgeContext(sectionKey);
            if (Objects.isNull(judgeContext)) {
                throw new SectionNotExistsException(sectionKey);
            }

            // 2. 在 JudgeContext 中取出所有Judger，并按照判断的逻辑执行任务。
            for (Judger judger : judgeContext.getJudgers()) {
                consumeHandler.accept(judger);
            }
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}

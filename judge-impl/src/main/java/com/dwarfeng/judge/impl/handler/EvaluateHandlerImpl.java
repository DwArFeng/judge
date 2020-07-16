package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.EvaluateInfo;
import com.dwarfeng.judge.stack.exception.JudgeWorkDisabledException;
import com.dwarfeng.judge.stack.handler.ConsumeHandler;
import com.dwarfeng.judge.stack.handler.EvaluateHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class EvaluateHandlerImpl implements EvaluateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluateHandlerImpl.class);

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
    public void evaluate(EvaluateInfo evaluateInfo) throws HandlerException {
        try {
            // 判断是否允许判断，如果不允许，直接报错。
            if (!isEnabled()) {
                throw new JudgeWorkDisabledException();
            }
            // 记录日志并将判断信息提交到消费处理器中。
            LOGGER.debug("驱动器使能, 驱动判断指定部件: " + evaluateInfo.getSection().getKey());
            consumeHandler.accept(evaluateInfo);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}

package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.StartableHandler;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
public interface ResetHandler extends StartableHandler {

    /**
     * 重置作业功能。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.0.0-beta
     */
    void resetJob() throws HandlerException;

    /**
     * 重置主管功能。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.0.0-beta
     */
    void resetSupervise() throws HandlerException;

    /**
     * 重置下沉功能。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.1.0-beta
     */
    void resetSink() throws HandlerException;
}

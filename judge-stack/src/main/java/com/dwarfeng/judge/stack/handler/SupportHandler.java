package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 支持处理器。
 *
 * @author DwArFeng
 * @since 1.7.0
 */
public interface SupportHandler extends Handler {

    /**
     * 重置分析器。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.0.0-beta
     */
    void resetAnalyser() throws HandlerException;

    /**
     * 重置驱动器。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.0.0-beta
     */
    void resetDriver() throws HandlerException;

    /**
     * 重置判断器。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.0.0-beta
     */
    void resetJudger() throws HandlerException;
}

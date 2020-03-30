package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 判断工作处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface JudgeWorkHandler extends Handler {

    /**
     * 判断工作处理器是否启动。
     *
     * @return 判断工作处理器是否启动。
     * @throws HandlerException 处理器异常。
     */
    boolean isStart() throws HandlerException;

    /**
     * 开启判断工作处理器。
     *
     * @throws HandlerException 处理器异常。
     */
    void start() throws HandlerException;

    /**
     * 关闭判断工作处理器。
     *
     * @throws HandlerException 处理器异常。
     */
    void stop() throws HandlerException;

    /**
     * 使判断工作处理器接受指定的判断器。
     *
     * @param judger 指定的判断器。
     * @throws HandlerException 处理器异常。
     */
    void accept(Judger judger) throws HandlerException;

    /**
     * 获取缓冲器的容量。
     *
     * @return 缓冲器的容量。
     * @throws HandlerException 处理器异常。
     */
    int getBufferSize() throws HandlerException;

    /**
     * 获取判断工作者的线程数量。
     *
     * @return 判断工作者的线程数量。
     * @throws HandlerException 处理器异常。
     */
    int getThread() throws HandlerException;

    /**
     * 设置判断工作者的线程数量。
     *
     * @param thread 判断工作者的线程数量。
     * @throws HandlerException 处理器异常。
     */
    void setThread(int thread) throws HandlerException;

    /**
     * 获取判断工作者是否空闲。
     *
     * @return 判断工作者是否空闲。
     * @throws HandlerException 处理器异常。
     */
    boolean isIdle() throws HandlerException;
}

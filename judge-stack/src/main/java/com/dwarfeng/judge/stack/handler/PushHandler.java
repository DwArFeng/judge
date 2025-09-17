package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 推送器处理器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
public interface PushHandler extends Handler {

    /**
     * 任务完成时执行的推送操作。
     *
     * @param section 相关的部件。
     * @throws HandlerException 处理器异常。
     * @since 2.0.0-beta
     */
    void taskFinished(Section section) throws HandlerException;

    /**
     * 任务失败时执行的推送操作。
     *
     * @param section 相关的部件。
     * @throws HandlerException 处理器异常。
     * @since 2.0.0-beta
     */
    void taskFailed(Section section) throws HandlerException;

    /**
     * 任务过期时执行的推送操作。
     *
     * @param section 相关的部件。
     * @throws HandlerException 处理器异常。
     * @since 2.0.0-beta
     */
    void taskExpired(Section section) throws HandlerException;

    /**
     * 任务死亡时执行的推送操作。
     *
     * @param section 相关的部件。
     * @throws HandlerException 处理器异常。
     * @since 2.0.0-beta
     */
    void taskDied(Section section) throws HandlerException;

    /**
     * 作业功能重置时作业的广播操作。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.0.0-beta
     */
    void jobReset() throws HandlerException;

    /**
     * 主管功能重置时执行的广播操作。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.0.0-beta
     */
    void superviseReset() throws HandlerException;

    /**
     * 下沉功能重置时执行的广播操作。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.1.0-beta
     */
    void sinkReset() throws HandlerException;
}

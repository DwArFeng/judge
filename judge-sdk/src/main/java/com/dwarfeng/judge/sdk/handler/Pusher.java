package com.dwarfeng.judge.sdk.handler;

import com.dwarfeng.judge.stack.bean.dto.PurgeFinishedResult;
import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 事件推送器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
public interface Pusher {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);

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
     * 主管功能重置时执行的广播操作。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.0.0-beta
     */
    void superviseReset() throws HandlerException;

    /**
     * 作业功能重置时作业的广播操作。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.0.0-beta
     */
    void jobReset() throws HandlerException;

    /**
     * 适配器功能重置时执行的广播操作。
     *
     * @throws HandlerException 处理器异常。
     * @since 3.1.0
     */
    void adaptReset() throws HandlerException;

    /**
     * 提供功能重置时执行的广播操作。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.1.0-beta
     */
    void provideReset() throws HandlerException;

    /**
     * 过滤器功能重置时执行的广播操作。
     *
     * @throws HandlerException 处理器异常。
     * @since 3.1.0
     */
    void filterReset() throws HandlerException;

    /**
     * 下沉功能重置时执行的广播操作。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.1.0-beta
     */
    void sinkReset() throws HandlerException;

    /**
     * 清除完成时执行的调度。
     *
     * @param result 清除结束结果。
     * @throws HandlerException 处理器异常。
     * @since 2.2.0
     */
    void purgeFinished(PurgeFinishedResult result) throws HandlerException;

    /**
     * 清除失败时执行的调度。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.2.0
     */
    void purgeFailed() throws HandlerException;

    /**
     * 报警发生时执行的推送操作。
     *
     * <p>
     * 事件负载为本次更新后的报警模态，订阅方通过方法或 topic 区分事件类型。
     *
     * @param alarmModal 本次更新后的报警模态。
     * @throws HandlerException 处理器异常。
     * @since 3.2.0
     */
    void alarmAppeared(AlarmModal alarmModal) throws HandlerException;

    /**
     * 报警等级提升时执行的推送操作。
     *
     * <p>
     * 事件负载为本次更新后的报警模态，订阅方通过方法或 topic 区分事件类型。
     *
     * @param alarmModal 本次更新后的报警模态。
     * @throws HandlerException 处理器异常。
     * @since 3.2.0
     */
    void alarmLevelIncreased(AlarmModal alarmModal) throws HandlerException;

    /**
     * 报警等级下降时执行的推送操作。
     *
     * <p>
     * 事件负载为本次更新后的报警模态，订阅方通过方法或 topic 区分事件类型。
     *
     * @param alarmModal 本次更新后的报警模态。
     * @throws HandlerException 处理器异常。
     * @since 3.2.0
     */
    void alarmLevelDecreased(AlarmModal alarmModal) throws HandlerException;

    /**
     * 报警消失时执行的推送操作。
     *
     * <p>
     * 事件负载为本次更新后的报警模态，其中 {@code alarming=false}。
     *
     * @param alarmModal 本次更新后的报警模态。
     * @throws HandlerException 处理器异常。
     * @since 3.2.0
     */
    void alarmDisappeared(AlarmModal alarmModal) throws HandlerException;
}

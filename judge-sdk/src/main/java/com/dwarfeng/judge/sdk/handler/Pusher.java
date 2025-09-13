package com.dwarfeng.judge.sdk.handler;

import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.judge.stack.bean.entity.JudgementModal;
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
     * @since 2.0.0
     */
    void taskFinished(Section section) throws HandlerException;

    /**
     * 任务失败时执行的推送操作。
     *
     * @param section 相关的部件。
     * @throws HandlerException 处理器异常。
     * @since 2.0.0
     */
    void taskFailed(Section section) throws HandlerException;

    /**
     * 任务过期时执行的推送操作。
     *
     * @param section 相关的部件。
     * @throws HandlerException 处理器异常。
     * @since 2.0.0
     */
    void taskExpired(Section section) throws HandlerException;

    /**
     * 任务死亡时执行的推送操作。
     *
     * @param section 相关的部件。
     * @throws HandlerException 处理器异常。
     * @since 2.0.0
     */
    void taskDied(Section section) throws HandlerException;

    /**
     * 判断结果模态更新时执行的推送操作。
     *
     * @param judgementModal 相关的判断结果模态。
     * @throws HandlerException 处理器异常。
     * @since 2.0.0
     */
    void judgementModalUpdated(JudgementModal judgementModal) throws HandlerException;

    /**
     * 报警模态更新时执行的推送操作。
     *
     * @param alarmModal 相关的报警模态。
     * @throws HandlerException 处理器异常。
     * @since 2.0.0
     */
    void alarmModalUpdated(AlarmModal alarmModal) throws HandlerException;

    /**
     * 作业功能重置时作业的广播操作。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.0.0
     */
    void jobReset() throws HandlerException;

    /**
     * 主管功能重置时执行的广播操作。
     *
     * @throws HandlerException 处理器异常。
     * @since 2.0.0
     */
    void superviseReset() throws HandlerException;
}

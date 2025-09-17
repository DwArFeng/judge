package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.JudgementInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgementInspectResult;
import com.dwarfeng.judge.stack.bean.dto.JudgementRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgementUpsertInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import javax.annotation.Nullable;

/**
 * 判断结果处理器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface JudgementOperateHandler extends Handler {

    /**
     * 查看判断结果。
     *
     * <p>
     * 当 {@link JudgementInspectInfo#getTaskKey()} 不存在时，该方法抛出异常。<br>
     * 当 {@link JudgementInspectInfo#getTaskKey()} 存在，
     * 但 {@link JudgementInspectInfo#getDataStringId()} 不存在时，该方法返回 <code>null</code>。
     *
     * @param info 判断结果查看信息。
     * @return 查看结果。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    JudgementInspectResult inspect(JudgementInspectInfo info) throws HandlerException;

    /**
     * 插入或更新判断结果。
     *
     * @param info 判断结果插入或更新信息。
     * @throws HandlerException 处理器异常。
     */
    void upsert(JudgementUpsertInfo info) throws HandlerException;

    /**
     * 删除判断结果。
     *
     * @param info 判断结果删除信息。
     * @throws HandlerException 处理器异常。
     */
    void remove(JudgementRemoveInfo info) throws HandlerException;
}

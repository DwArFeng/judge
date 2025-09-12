package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.AnalysisInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalysisInspectResult;
import com.dwarfeng.judge.stack.bean.dto.AnalysisRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalysisUpsertInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import javax.annotation.Nullable;

/**
 * 分析结果处理器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalysisOperateHandler extends Handler {

    /**
     * 查看分析结果。
     *
     * <p>
     * 当 {@link AnalysisInspectInfo#getTaskKey()} 不存在时，该方法抛出异常。<br>
     * 当 {@link AnalysisInspectInfo#getTaskKey()} 存在，
     * 但 {@link AnalysisInspectInfo#getDataStringId()} 不存在时，该方法返回 <code>null</code>。
     *
     * @param info 分析结果查看信息。
     * @return 查看结果。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    AnalysisInspectResult inspect(AnalysisInspectInfo info) throws HandlerException;

    /**
     * 插入或更新分析结果。
     *
     * @param info 分析结果插入或更新信息。
     * @throws HandlerException 处理器异常。
     */
    void upsert(AnalysisUpsertInfo info) throws HandlerException;

    /**
     * 删除分析结果。
     *
     * @param info 分析结果删除信息。
     * @throws HandlerException 处理器异常。
     */
    void remove(AnalysisRemoveInfo info) throws HandlerException;
}

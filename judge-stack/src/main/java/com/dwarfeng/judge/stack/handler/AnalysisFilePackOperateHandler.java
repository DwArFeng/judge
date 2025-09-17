package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackClearInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackInsertInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 分析结果文件包操作处理器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalysisFilePackOperateHandler extends Handler {

    /**
     * 插入分析结果文件包。
     *
     * <p>
     * 如果指定的分析结果文件包已经存在，则首先删除旧的分析结果文件包，然后插入新的分析结果文件包。
     *
     * @param info 插入信息。
     * @throws HandlerException 处理器异常。
     */
    void insert(AnalysisFilePackInsertInfo info) throws HandlerException;

    /**
     * 清除分析结果文件包。
     *
     * <p>
     * 该方法会清除分析结果文件包中的所有文件，并重置分析结果文件包的锚点索引。
     *
     * @param info 清除信息。
     * @throws HandlerException 处理器异常。
     */
    void clear(AnalysisFilePackClearInfo info) throws HandlerException;
}

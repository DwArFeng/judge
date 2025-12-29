package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableInspectResult;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableUpsertInfo;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

import javax.annotation.Nullable;

/**
 * 分析器变量操作服务。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public interface AnalyserVariableOperateService extends Service {

    /**
     * 查看分析器变量。
     *
     * <p>
     * 该方法返回指定的分析器变量查看信息对应的分析器变量的查看结果。<br>
     * 如果指定的分析器变量不存在，则返回 <code>null</code>。
     *
     * @param info 分析器变量查看信息。
     * @return 分析器变量的查看结果。
     * @throws ServiceException 服务异常。
     */
    @Nullable
    AnalyserVariableInspectResult inspect(AnalyserVariableInspectInfo info) throws ServiceException;

    /**
     * 插入/更新分析器变量。
     *
     * @param info 分析器变量插入/更新信息。
     * @throws ServiceException 服务异常。
     */
    void upsert(AnalyserVariableUpsertInfo info) throws ServiceException;

    /**
     * 删除分析器变量。
     *
     * @param info 分析器变量删除信息。
     * @throws ServiceException 服务异常。
     */
    void remove(AnalyserVariableRemoveInfo info) throws ServiceException;
}

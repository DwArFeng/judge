package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.dto.SinkerVariableInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableInspectResult;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableUpsertInfo;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

import javax.annotation.Nullable;

/**
 * 下沉器变量操作服务。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public interface SinkerVariableOperateService extends Service {

    /**
     * 查看下沉器变量。
     *
     * <p>
     * 该方法返回指定的下沉器变量查看信息对应的下沉器变量的查看结果。<br>
     * 如果指定的下沉器变量不存在，则返回 <code>null</code>。
     *
     * @param info 下沉器变量查看信息。
     * @return 下沉器变量的查看结果。
     * @throws ServiceException 服务异常。
     */
    @Nullable
    SinkerVariableInspectResult inspect(SinkerVariableInspectInfo info) throws ServiceException;

    /**
     * 插入/更新下沉器变量。
     *
     * @param info 下沉器变量插入/更新信息。
     * @throws ServiceException 服务异常。
     */
    void upsert(SinkerVariableUpsertInfo info) throws ServiceException;

    /**
     * 删除下沉器变量。
     *
     * @param info 下沉器变量删除信息。
     * @throws ServiceException 服务异常。
     */
    void remove(SinkerVariableRemoveInfo info) throws ServiceException;
}

package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.dto.JudgerVariableInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableInspectResult;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableUpsertInfo;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

import javax.annotation.Nullable;

/**
 * 判断器变量操作服务。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public interface JudgerVariableOperateService extends Service {

    /**
     * 查看判断器变量。
     *
     * <p>
     * 该方法返回指定的判断器变量查看信息对应的判断器变量的查看结果。<br>
     * 如果指定的判断器变量不存在，则返回 <code>null</code>。
     *
     * @param info 判断器变量查看信息。
     * @return 判断器变量的查看结果。
     * @throws ServiceException 服务异常。
     */
    @Nullable
    JudgerVariableInspectResult inspect(JudgerVariableInspectInfo info) throws ServiceException;

    /**
     * 插入/更新判断器变量。
     *
     * @param info 判断器变量插入/更新信息。
     * @throws ServiceException 服务异常。
     */
    void upsert(JudgerVariableUpsertInfo info) throws ServiceException;

    /**
     * 删除判断器变量。
     *
     * @param info 判断器变量删除信息。
     * @throws ServiceException 服务异常。
     */
    void remove(JudgerVariableRemoveInfo info) throws ServiceException;
}

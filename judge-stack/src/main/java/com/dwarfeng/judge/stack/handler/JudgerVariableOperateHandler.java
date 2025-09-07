package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.JudgerVariableInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableInspectResult;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableUpsertInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import javax.annotation.Nullable;

/**
 * 判断器变量操作处理器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface JudgerVariableOperateHandler extends Handler {

    /**
     * 查看判断器变量。
     *
     * <p>
     * 该方法返回指定的判断器变量查看信息对应的判断器变量的查看结果。<br>
     * 如果指定的判断器变量不存在，则返回 <code>null</code>。
     *
     * @param info 判断器变量查看信息。
     * @return 判断器变量的查看结果。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    JudgerVariableInspectResult inspect(JudgerVariableInspectInfo info) throws HandlerException;

    /**
     * 插入/更新判断器变量。
     *
     * @param info 判断器变量插入/更新信息。
     * @throws HandlerException 处理器异常。
     */
    void upsert(JudgerVariableUpsertInfo info) throws HandlerException;

    /**
     * 删除判断器变量。
     *
     * @param info 判断器变量删除信息。
     * @throws HandlerException 处理器异常。
     */
    void remove(JudgerVariableRemoveInfo info) throws HandlerException;
}

package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 判断指派处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface JudgeAssignHandler extends Handler {

    /**
     * 判断指派处理器是否被启用。
     *
     * @return 判断指派处理器是否被启用。
     * @throws HandlerException 处理器异常。
     */
    boolean isEnabled() throws HandlerException;

    /**
     * 启用判断指派处理器。
     *
     * @throws HandlerException 处理器异常。
     */
    void enable() throws HandlerException;

    /**
     * 禁用判断指派处理器。
     *
     * @throws HandlerException 处理器异常。
     */
    void disable() throws HandlerException;

    /**
     * 对指定的部件进行判断。
     *
     * @param sectionKey 部件的主键。
     * @throws HandlerException 处理器异常。
     */
    void judge(LongIdKey sectionKey) throws HandlerException;
}

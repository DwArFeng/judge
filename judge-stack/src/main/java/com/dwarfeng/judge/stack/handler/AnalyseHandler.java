package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 分析指派处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface AnalyseHandler extends Handler {

    /**
     * 分析指派处理器是否被启用。
     *
     * @return 分析指派处理器是否被启用。
     * @throws HandlerException 处理器异常。
     */
    boolean isEnabled() throws HandlerException;

    /**
     * 启用分析指派处理器。
     *
     * @throws HandlerException 处理器异常。
     */
    void enable() throws HandlerException;

    /**
     * 禁用分析指派处理器。
     *
     * @throws HandlerException 处理器异常。
     */
    void disable() throws HandlerException;

    /**
     * 对指定的部件进行分析。
     *
     * @param sectionKey 部件的主键。
     * @throws HandlerException 处理器异常。
     */
    void analyse(LongIdKey sectionKey) throws HandlerException;
}

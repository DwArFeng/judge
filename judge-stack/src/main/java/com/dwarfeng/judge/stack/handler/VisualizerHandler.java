package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 可视化器处理器。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface VisualizerHandler extends Handler {

    /**
     * 根据指定的可视化器信息构造一个可视化器。
     *
     * @param type    可视化器类型。
     * @param content 可视化器内容。
     * @return 构造的可视化器。
     * @throws HandlerException 处理器异常。
     */
    Visualizer make(String type, String content) throws HandlerException;
}

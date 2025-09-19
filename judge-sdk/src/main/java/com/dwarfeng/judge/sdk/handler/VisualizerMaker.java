package com.dwarfeng.judge.sdk.handler;

import com.dwarfeng.judge.stack.exception.VisualizerException;
import com.dwarfeng.judge.stack.handler.Visualizer;

/**
 * 可视化器构造器。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface VisualizerMaker {

    /**
     * 返回构造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 构造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 根据指定的可视化器信息生成一个可视化器对象。
     *
     * <p>
     * 可以保证传入的可视化器信息中的类型是支持的。
     *
     * @param type  可视化器类型。
     * @param param 可视化器参数。
     * @return 构造的可视化器。
     * @throws VisualizerException 可视化器异常。
     */
    Visualizer makeVisualizer(String type, String param) throws VisualizerException;
}

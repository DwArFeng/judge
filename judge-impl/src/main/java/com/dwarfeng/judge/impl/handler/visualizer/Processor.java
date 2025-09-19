package com.dwarfeng.judge.impl.handler.visualizer;

import com.dwarfeng.judge.stack.handler.Visualizer;

/**
 * Groovy 处理器。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface Processor {

    /**
     * 可视化数据。
     *
     * <p>
     * 该方法被调用时，需要按照预定的逻辑对数据进行可视化，并把可视化的结果或中间值以可视化结果的形式，
     * 通过 {@link Visualizer.Context} 进行持久化。
     *
     * @param context 执行器的可视化器上下文。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    void analyse(Visualizer.Context context) throws Exception;
}

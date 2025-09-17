package com.dwarfeng.judge.impl.handler.analyser.groovy;

import com.dwarfeng.judge.stack.handler.Analyser;

/**
 * Groovy 处理器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface Processor {

    /**
     * 分析数据。
     *
     * <p>
     * 该方法被调用时，需要按照预定的逻辑对数据进行分析，并把分析的结果或中间值以分析结果的形式，
     * 通过 {@link Analyser.Context} 进行持久化。
     *
     * @param context 执行器的分析器上下文。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    void analyse(Analyser.Context context) throws Exception;
}

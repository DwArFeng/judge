package com.dwarfeng.judge.impl.handler.judger.groovy;

import com.dwarfeng.judge.stack.handler.Judger;

/**
 * Groovy 处理器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface Processor {

    /**
     * 判断数据。
     *
     * <p>
     * 该方法被调用时，需要按照预定的逻辑对数据进行判断，并把判断的结果或中间值以判断结果的形式，
     * 通过 {@link Judger.Context} 进行持久化。
     *
     * @param context 执行器的判断器上下文。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    void judge(Judger.Context context) throws Exception;
}

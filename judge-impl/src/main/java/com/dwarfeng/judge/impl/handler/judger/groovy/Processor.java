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
     * @param context 执行器的判断器上下文。
     * @return 判断结果。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    Judger.JudgeResult judge(Judger.Context context) throws Exception;
}

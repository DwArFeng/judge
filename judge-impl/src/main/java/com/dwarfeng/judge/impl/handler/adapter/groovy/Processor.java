package com.dwarfeng.judge.impl.handler.adapter.groovy;

/**
 * Groovy 处理器。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface Processor {

    /**
     * 适配器参数适配。
     *
     * @return 适配后的参数。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    Object[] adapt(Object[] args) throws Exception;
}

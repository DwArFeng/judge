package com.dwarfeng.judge.impl.handler.filter.groovy;

import com.dwarfeng.judge.stack.bean.dto.LookupResult;

/**
 * Groovy 处理器。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface Processor {

    /**
     * 过滤器参数过滤。
     *
     * @return 过滤后的参数。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    LookupResult filt(LookupResult lookupResult) throws Exception;
}

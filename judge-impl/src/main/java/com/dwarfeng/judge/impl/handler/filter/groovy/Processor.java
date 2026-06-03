package com.dwarfeng.judge.impl.handler.filter.groovy;

import com.dwarfeng.judge.stack.handler.Filter.FilterInfo;
import com.dwarfeng.judge.stack.handler.Filter.FilterResult;

/**
 * Groovy 处理器。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.3.0
 */
public interface Processor {

    /**
     * 过滤。
     *
     * <p>
     * 该方法被调用时，会按照预定的逻辑对提供的数据进行过滤，并把过滤的结果进行返回。
     *
     * @param info 过滤信息。
     * @return 过滤结果。
     * @throws Exception 方法执行过程中发生的任何异常。
     * @since 2.6.0
     */
    FilterResult filter(FilterInfo info) throws Exception;
}

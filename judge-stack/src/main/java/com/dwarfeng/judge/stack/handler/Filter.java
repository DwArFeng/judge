package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.LookupResult;
import com.dwarfeng.judge.stack.exception.FilterException;

/**
 * 过滤器。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.3.0
 */
public interface Filter {

    /**
     * 过滤器参数过滤。
     * <p>
     * 该方法被调用时，会按照预定的逻辑对提供的数据进行过滤，并把过滤的结果进行返回。
     *
     * @return 过滤后的参数。
     * @throws FilterException 过滤器异常。
     * @author DwArFeng
     * @since 2.4.0
     */
    LookupResult filter(LookupResult lookupResult) throws FilterException;
}

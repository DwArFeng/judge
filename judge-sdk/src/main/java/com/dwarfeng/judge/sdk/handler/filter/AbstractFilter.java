package com.dwarfeng.judge.sdk.handler.filter;

import com.dwarfeng.judge.stack.exception.FilterException;
import com.dwarfeng.judge.stack.exception.FilterExecutionException;
import com.dwarfeng.judge.stack.handler.Filter;

/**
 * 过滤器的抽象实现。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.3.0
 */
public abstract class AbstractFilter implements Filter {

    /**
     * {@inheritDoc}
     *
     * @author DwArFeng
     * @since 2.4.0
     */
    @Override
    public FilterResult filter(FilterInfo info) throws FilterException {
        try {
            return doFilter(info);
        } catch (FilterException e) {
            throw e;
        } catch (Exception e) {
            throw new FilterExecutionException(e);
        }
    }

    /**
     * 过滤。
     *
     * <p>
     * 该方法被调用时，会按照预定的逻辑对提供的数据进行过滤，并把过滤的结果进行返回。
     *
     * @param info 过滤信息。
     * @return 过滤结果。
     * @throws FilterException 过滤器异常。
     * @see #filter(FilterInfo)
     * @since 2.6.0
     */
    protected abstract FilterResult doFilter(FilterInfo info) throws Exception;

    @Override
    public String toString() {
        return "AbstractFilter{}";
    }
}

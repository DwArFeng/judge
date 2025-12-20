package com.dwarfeng.judge.sdk.handler.filter;

import com.dwarfeng.judge.stack.bean.dto.LookupResult;
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
    public LookupResult filter(LookupResult lookupResult) throws FilterException {
        try {
            return doFilter(lookupResult);
        } catch (FilterException e) {
            throw e;
        } catch (Exception e) {
            throw new FilterExecutionException(e);
        }
    }

    /**
     * 新建过滤器会话。
     *
     * @return 新建生成的过滤器会话。
     * @throws Exception 任何可能的异常。
     * @author DwArFeng
     * @since 2.4.0
     */
    protected abstract LookupResult doFilter(LookupResult lookupResult) throws Exception;

    @Override
    public String toString() {
        return "AbstractFilter{}";
    }
}

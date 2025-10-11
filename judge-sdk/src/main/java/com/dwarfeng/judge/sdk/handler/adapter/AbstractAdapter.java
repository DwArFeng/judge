package com.dwarfeng.judge.sdk.handler.adapter;

import com.dwarfeng.judge.stack.exception.AdapterException;
import com.dwarfeng.judge.stack.exception.AdapterExecutionException;
import com.dwarfeng.judge.stack.handler.Adapter;

/**
 * 适配器的抽象实现。
 *
 * @author wangyc
 * @since 2.3.0
 */
public abstract class AbstractAdapter implements Adapter {

    @Override
    public Object[] adapt(Object[] args) throws AdapterException {
        try {
            return doAdapt(args);
        } catch (AdapterException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterExecutionException(e);
        }
    }

    /**
     * 新建适配器会话。
     *
     * @return 新建生成的适配器会话。
     * @throws Exception 任何可能的异常。
     */
    protected abstract Object[] doAdapt(Object[] args) throws Exception;

    @Override
    public String toString() {
        return "AbstractAdapter{}";
    }
}

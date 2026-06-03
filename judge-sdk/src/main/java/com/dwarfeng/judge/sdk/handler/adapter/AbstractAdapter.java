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
    public AdaptResult adapt(AdaptInfo info) throws AdapterException {
        try {
            return doAdapt(info);
        } catch (AdapterException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterExecutionException(e);
        }
    }

    /**
     * 适配。
     *
     * <p>
     * 该方法被调用时，会按照预定的逻辑对提供的数据进行适配，并把适配的结果进行返回，
     *
     * @param info 适配信息。
     * @return 适配结果。
     * @throws AdapterException 适配器异常。
     * @see #adapt(AdaptInfo)
     * @since 2.6.0
     */
    protected abstract AdaptResult doAdapt(AdaptInfo info) throws Exception;

    @Override
    public String toString() {
        return "AbstractAdapter{}";
    }
}

package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.exception.AdapterException;

/**
 * 适配器。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface Adapter {

    /**
     * 适配器参数适配。
     * <p>
     * 该方法被调用时，会按照预定的逻辑对提供的数据进行适配，并把适配的结果进行返回，
     *
     * @return 适配后的参数。
     * @throws AdapterException 适配器异常。
     */
    Object[] adapt(Object[] args) throws AdapterException;
}

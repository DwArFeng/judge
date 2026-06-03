package com.dwarfeng.judge.impl.handler.adapter.groovy;

import com.dwarfeng.judge.stack.handler.Adapter.AdaptInfo;
import com.dwarfeng.judge.stack.handler.Adapter.AdaptResult;

/**
 * Groovy 处理器。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.3.0
 */
public interface Processor {

    /**
     * 适配。
     *
     * <p>
     * 该方法被调用时，会按照预定的逻辑对提供的数据进行适配，并把适配的结果进行返回，
     *
     * @param info 适配信息。
     * @return 适配结果。
     * @throws Exception 方法执行过程中发生的任何异常。
     * @since 2.6.0
     */
    AdaptResult adapt(AdaptInfo info) throws Exception;
}

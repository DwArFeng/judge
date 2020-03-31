package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.JudgedValue;
import com.dwarfeng.judge.stack.exception.SinkException;

/**
 * 水槽
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface Sink {

    /**
     * 返回水槽是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 水槽是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 下沉数据。
     *
     * @param judgedValue 被判断数据。
     * @throws SinkException 水槽异常。
     */
    void sinkData(JudgedValue judgedValue) throws SinkException;
}

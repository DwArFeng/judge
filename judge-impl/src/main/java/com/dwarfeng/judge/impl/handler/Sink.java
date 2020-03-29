package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dcti.stack.bean.dto.DataInfo;
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
     * @param dataInfo 指定的数据。
     * @throws SinkException 水槽异常。
     */
    void sinkData(DataInfo dataInfo) throws SinkException;

    /**
     * 下沉数据。
     *
     * @param message 指定数据的文本形式。
     * @throws SinkException 水槽异常。
     */
    void sinkData(String message) throws SinkException;
}

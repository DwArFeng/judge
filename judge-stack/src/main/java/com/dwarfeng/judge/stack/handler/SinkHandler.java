package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.dcti.stack.bean.dto.DataInfo;
import com.dwarfeng.judge.stack.exception.SinkException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 水槽处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface SinkHandler extends Handler {

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

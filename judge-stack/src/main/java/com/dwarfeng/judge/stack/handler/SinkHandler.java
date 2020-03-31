package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.dcti.stack.bean.dto.DataInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
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
     * @throws HandlerException 处理器异常。
     */
    void sinkData(DataInfo dataInfo) throws HandlerException;

    /**
     * 下沉数据。
     *
     * @param message 指定数据的文本形式。
     * @throws HandlerException 处理器异常。
     */
    void sinkData(String message) throws HandlerException;
}

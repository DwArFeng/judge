package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.JudgedValue;
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
     * 下沉被判断数据。
     *
     * @param judgedValue 指定的被判断数据。
     * @throws HandlerException 处理器异常。
     */
    void sinkData(JudgedValue judgedValue) throws HandlerException;
}

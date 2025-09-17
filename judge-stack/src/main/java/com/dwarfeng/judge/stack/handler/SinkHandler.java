package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.SinkInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 下沉处理器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkHandler extends Handler {

    /**
     * 下沉。
     *
     * @param info 下沉信息。
     * @throws HandlerException 处理器异常。
     */
    void sink(SinkInfo info) throws HandlerException;
}

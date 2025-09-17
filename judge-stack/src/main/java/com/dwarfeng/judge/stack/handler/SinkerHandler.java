package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 下沉器处理器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerHandler extends Handler {

    /**
     * 根据指定的下沉器信息构造一个下沉器。
     *
     * @param type  下沉器类型。
     * @param param 下沉器参数。
     * @return 构造的下沉器。
     * @throws HandlerException 处理器异常。
     */
    Sinker make(String type, String param) throws HandlerException;
}

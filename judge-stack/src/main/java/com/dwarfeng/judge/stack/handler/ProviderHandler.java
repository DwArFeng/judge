package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 提供器处理器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface ProviderHandler extends Handler {

    /**
     * 根据指定的提供器信息构造一个提供器。
     *
     * @param type  提供器类型。
     * @param param 提供器参数。
     * @return 构造的提供器。
     * @throws HandlerException 处理器异常。
     */
    Provider make(String type, String param) throws HandlerException;
}

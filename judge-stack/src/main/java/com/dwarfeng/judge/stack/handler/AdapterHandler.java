package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 适配器处理器。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface AdapterHandler extends Handler {

    /**
     * 根据指定的适配器信息构造一个适配器。
     *
     * @param type  适配器类型。
     * @param param 适配器参数。
     * @return 构造的适配器。
     * @throws HandlerException 处理器异常。
     */
    Adapter make(String type, String param) throws HandlerException;
}

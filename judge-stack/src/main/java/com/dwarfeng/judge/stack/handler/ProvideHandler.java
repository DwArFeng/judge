package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.ProvideInfo;
import com.dwarfeng.judge.stack.bean.dto.ProvideResult;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 提供处理器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface ProvideHandler extends Handler {

    /**
     * 提供。
     *
     * @param info 提供信息。
     * @throws HandlerException 处理器异常。
     */
    ProvideResult provide(ProvideInfo info) throws HandlerException;
}

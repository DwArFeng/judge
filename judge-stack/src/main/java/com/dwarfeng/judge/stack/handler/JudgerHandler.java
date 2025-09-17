package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 判断器处理器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface JudgerHandler extends Handler {

    /**
     * 根据指定的判断器信息构造一个判断器。
     *
     * @param type    判断器类型。
     * @param content 判断器内容。
     * @return 构造的判断器。
     * @throws HandlerException 处理器异常。
     */
    Judger make(String type, String content) throws HandlerException;
}

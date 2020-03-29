package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 判断器处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface JudgerHandler extends Handler {

    /**
     * 根据指定的判断器信息构造一个判断器。
     *
     * @param judgerInfo 判断器信息。
     * @return 构造的判断器。
     * @throws HandlerException 处理器异常。
     */
    Judger make(JudgerInfo judgerInfo) throws HandlerException;
}

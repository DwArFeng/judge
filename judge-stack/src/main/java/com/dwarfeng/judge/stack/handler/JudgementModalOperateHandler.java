package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.JudgementModalUpdateInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 判断结果模态操作处理器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface JudgementModalOperateHandler extends Handler {

    /**
     * 更新判断结果模态。
     *
     * @param info 更新信息。
     * @throws HandlerException 处理器异常。
     */
    void update(JudgementModalUpdateInfo info) throws HandlerException;
}

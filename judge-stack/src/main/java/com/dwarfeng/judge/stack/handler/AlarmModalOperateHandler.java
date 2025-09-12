package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.AlarmModalUpdateInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 报警模态操作处理器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AlarmModalOperateHandler extends Handler {

    /**
     * 更新报警模态。
     *
     * @param info 更新信息。
     * @throws HandlerException 处理器异常。
     */
    void update(AlarmModalUpdateInfo info) throws HandlerException;
}

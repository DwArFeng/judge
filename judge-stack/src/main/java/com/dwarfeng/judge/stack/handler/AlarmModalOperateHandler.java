package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.AlarmModalUpdateInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 报警模态操作处理器。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface AlarmModalOperateHandler extends Handler {

    /**
     * 根据最新报警模态信息更新单条报警模态，并维护当前报警与报警历史。
     *
     * @param info 报警模态更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateModal(AlarmModalUpdateInfo info) throws HandlerException;
}

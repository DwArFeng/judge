package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.VisualizeDataUpsertInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 可视化数据操作处理器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface VisualizeDataOperateHandler extends Handler {

    /**
     * 插入/更新可视化数据。
     *
     * @param info 可视化数据插入/更新信息。
     * @throws HandlerException 处理器异常。
     */
    void upsert(VisualizeDataUpsertInfo info) throws HandlerException;
}

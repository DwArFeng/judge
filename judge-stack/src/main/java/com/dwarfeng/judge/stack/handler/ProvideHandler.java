package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.DataLookupInfo;
import com.dwarfeng.judge.stack.bean.dto.DataLookupResult;
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
     * 查询数据。
     *
     * @param info 数据查询信息。
     * @return 数据查询结果。
     * @throws HandlerException 处理器异常。
     */
    DataLookupResult lookupData(DataLookupInfo info) throws HandlerException;
}

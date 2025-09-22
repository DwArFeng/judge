package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.LookupInfo;
import com.dwarfeng.judge.stack.bean.dto.LookupResult;
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
     * 查询。
     *
     * <p>
     * 有关参数和返回值的详细描述，请参阅相关对象的文档注释。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws HandlerException 处理器异常。
     * @see LookupInfo
     * @see LookupResult
     * @since 2.3.0
     */
    LookupResult lookup(LookupInfo info) throws HandlerException;
}

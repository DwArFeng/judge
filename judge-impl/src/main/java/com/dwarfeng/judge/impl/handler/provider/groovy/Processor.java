package com.dwarfeng.judge.impl.handler.provider.groovy;

import com.dwarfeng.judge.stack.bean.dto.LookupInfo;
import com.dwarfeng.judge.stack.bean.dto.LookupResult;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * Groovy 处理器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface Processor {

    /**
     * 打开提供器会话。
     *
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    void openSession() throws Exception;

    /**
     * 关闭提供器会话。
     *
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    void closeSession() throws Exception;

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
    LookupResult lookup(LookupInfo info) throws Exception;
}

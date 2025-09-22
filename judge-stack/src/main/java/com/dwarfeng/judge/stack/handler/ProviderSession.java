package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.LookupInfo;
import com.dwarfeng.judge.stack.bean.dto.LookupResult;
import com.dwarfeng.judge.stack.exception.ProviderSessionException;

/**
 * 提供器会话。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface ProviderSession {

    /**
     * 打开提供器会话。
     *
     * @throws ProviderSessionException 提供器会话异常。
     */
    void openSession() throws ProviderSessionException;

    /**
     * 关闭提供器会话。
     *
     * @throws ProviderSessionException 提供器会话异常。
     */
    void closeSession() throws ProviderSessionException;

    /**
     * 查询。
     *
     * <p>
     * 有关参数和返回值的详细描述，请参阅相关对象的文档注释。
     *
     * <p>
     * <code>LookupInfo.providerInfoKey</code> 字段为本提供器会话对应的提供器信息的主键。<br>
     * <code>LookupInfo.providerInfoKey</code> 字段是通过提供处理器的相关方法传递到此处的，本方法在实现时不得使用这个字段。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws ProviderSessionException 提供器会话异常。
     * @see LookupInfo
     * @see LookupResult
     * @since 2.3.0
     */
    LookupResult lookup(LookupInfo info) throws ProviderSessionException;
}

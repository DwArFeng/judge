package com.dwarfeng.judge.sdk.handler.provider;

import com.dwarfeng.judge.stack.bean.dto.LookupInfo;
import com.dwarfeng.judge.stack.bean.dto.LookupResult;
import com.dwarfeng.judge.stack.exception.ProviderSessionException;
import com.dwarfeng.judge.stack.handler.ProviderSession;

/**
 * 提供器会话的抽象实现。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public abstract class AbstractProviderSession implements ProviderSession {

    @Override
    public void openSession() throws ProviderSessionException {
        try {
            doOpenSession();
        } catch (Exception e) {
            throw ExceptionHelper.parseProviderSessionException(e);
        }
    }

    /**
     * 打开提供器会话。
     *
     * @throws Exception 任何可能的异常。
     * @see #openSession()
     */
    protected abstract void doOpenSession() throws Exception;

    @Override
    public void closeSession() throws ProviderSessionException {
        try {
            doCloseSession();
        } catch (Exception e) {
            throw ExceptionHelper.parseProviderSessionException(e);
        }
    }

    /**
     * 关闭提供器会话。
     *
     * @throws Exception 任何可能的异常。
     * @see #closeSession()
     */
    protected abstract void doCloseSession() throws Exception;

    @Override
    public LookupResult lookup(LookupInfo info) throws ProviderSessionException {
        try {
            return doLookupData(info);
        } catch (Exception e) {
            throw ExceptionHelper.parseProviderSessionException(e);
        }
    }

    /**
     * 查询数据。
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
     * @throws Exception 任何可能的异常。
     * @see #lookup(LookupInfo)
     * @see LookupInfo
     * @see LookupResult
     * @since 2.3.0
     */
    protected abstract LookupResult doLookupData(LookupInfo info) throws Exception;
}

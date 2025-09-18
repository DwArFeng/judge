package com.dwarfeng.judge.sdk.handler.provider;

import com.dwarfeng.judge.stack.exception.ProviderException;
import com.dwarfeng.judge.stack.handler.Provider;
import com.dwarfeng.judge.stack.handler.ProviderSession;

/**
 * 提供器的抽象实现。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public abstract class AbstractProvider implements Provider {

    @Override
    public ProviderSession newSession() throws ProviderException {
        try {
            return doNewSession();
        } catch (Exception e) {
            throw ExceptionHelper.parseProviderExecutionException(e);
        }
    }

    /**
     * 新建提供器会话。
     *
     * @return 新建生成的提供器会话。
     * @throws Exception 任何可能的异常。
     * @see #newSession()
     */
    protected abstract ProviderSession doNewSession() throws Exception;

    @Override
    public String toString() {
        return "AbstractProvider{}";
    }
}

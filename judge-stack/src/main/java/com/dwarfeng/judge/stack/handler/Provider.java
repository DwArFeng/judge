package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.exception.ProviderException;

/**
 * 提供器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface Provider {

    /**
     * 新建提供器会话。
     *
     * @return 新建生成的提供起会话。
     * @throws ProviderException 提供器异常。
     */
    ProviderSession newSession() throws ProviderException;
}

package com.dwarfeng.judge.sdk.handler.provider;

import com.dwarfeng.judge.stack.exception.ProviderException;
import com.dwarfeng.judge.stack.exception.ProviderExecutionException;
import com.dwarfeng.judge.stack.exception.ProviderSessionException;

import javax.annotation.Nonnull;

/**
 * 提供器异常帮助类。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
final class ExceptionHelper {

    public static ProviderException parseProviderExecutionException(@Nonnull Exception e) {
        if (e instanceof ProviderExecutionException) {
            return (ProviderExecutionException) e;
        }
        return new ProviderExecutionException(e);
    }

    public static ProviderSessionException parseProviderSessionException(@Nonnull Exception e) {
        if (e instanceof ProviderSessionException) {
            return (ProviderSessionException) e;
        }
        return new ProviderSessionException(e);
    }

    private ExceptionHelper() {
        throw new IllegalStateException("禁止实例化");
    }
}

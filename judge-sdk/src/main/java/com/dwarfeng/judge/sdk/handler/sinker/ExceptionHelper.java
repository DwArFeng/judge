package com.dwarfeng.judge.sdk.handler.sinker;

import com.dwarfeng.judge.stack.exception.SinkerException;
import com.dwarfeng.judge.stack.exception.SinkerExecutionException;
import com.dwarfeng.judge.stack.exception.SinkerSessionException;

import javax.annotation.Nonnull;

/**
 * 下沉器异常帮助类。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
final class ExceptionHelper {

    public static SinkerException parseSinkerExecutionException(@Nonnull Exception e) {
        if (e instanceof SinkerExecutionException) {
            return (SinkerExecutionException) e;
        }
        return new SinkerExecutionException(e);
    }

    public static SinkerSessionException parseSinkerSessionException(@Nonnull Exception e) {
        if (e instanceof SinkerSessionException) {
            return (SinkerSessionException) e;
        }
        return new SinkerSessionException(e);
    }

    private ExceptionHelper() {
        throw new IllegalStateException("禁止实例化");
    }
}

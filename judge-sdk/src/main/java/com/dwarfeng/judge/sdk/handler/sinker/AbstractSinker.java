package com.dwarfeng.judge.sdk.handler.sinker;

import com.dwarfeng.judge.stack.exception.SinkerException;
import com.dwarfeng.judge.stack.handler.Sinker;
import com.dwarfeng.judge.stack.handler.SinkerSession;

/**
 * 下沉器的抽象实现。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public abstract class AbstractSinker implements Sinker {

    @Override
    public SinkerSession newSession() throws SinkerException {
        try {
            return doNewSession();
        } catch (Exception e) {
            throw ExceptionHelper.parseSinkerExecutionException(e);
        }
    }

    /**
     * 新建下沉器会话。
     *
     * @return 新建生成的下沉器会话。
     * @throws Exception 任何可能的异常。
     * @see #newSession()
     */
    protected abstract SinkerSession doNewSession() throws Exception;

    @Override
    public String toString() {
        return "AbstractSinker{}";
    }
}

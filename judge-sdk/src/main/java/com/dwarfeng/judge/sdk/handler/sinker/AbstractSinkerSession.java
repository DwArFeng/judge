package com.dwarfeng.judge.sdk.handler.sinker;

import com.dwarfeng.judge.stack.bean.dto.SinkInfo;
import com.dwarfeng.judge.stack.exception.SinkerSessionException;
import com.dwarfeng.judge.stack.handler.SinkerSession;

/**
 * 下沉器会话的抽象实现。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public abstract class AbstractSinkerSession implements SinkerSession {

    protected Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public void openSession() throws SinkerSessionException {
        try {
            doOpenSession();
        } catch (Exception e) {
            throw ExceptionHelper.parseSinkerSessionException(e);
        }
    }

    /**
     * 打开下沉器会话。
     *
     * @throws Exception 任何可能的异常。
     * @see #openSession()
     */
    protected abstract void doOpenSession() throws Exception;

    @Override
    public void closeSession() throws SinkerSessionException {
        try {
            doCloseSession();
        } catch (Exception e) {
            throw ExceptionHelper.parseSinkerSessionException(e);
        }
    }

    /**
     * 关闭下沉器会话。
     *
     * @throws Exception 任何可能的异常。
     * @see #closeSession()
     */
    protected abstract void doCloseSession() throws Exception;

    @Override
    public void sink(SinkInfo info) throws SinkerSessionException {
        try {
            doSink(info);
        } catch (Exception e) {
            throw ExceptionHelper.parseSinkerSessionException(e);
        }
    }

    /**
     * 下沉。
     *
     * @param info 下沉信息。
     * @throws Exception 任何可能的异常。
     * @see #sink(SinkInfo)
     */
    protected abstract void doSink(SinkInfo info) throws Exception;
}

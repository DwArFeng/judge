package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.exception.SinkerException;

/**
 * 下沉器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface Sinker {

    /**
     * 新建下沉器会话。
     *
     * @return 新建生成的下沉起会话。
     * @throws SinkerException 下沉器异常。
     */
    SinkerSession newSession() throws SinkerException;
}

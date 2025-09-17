package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 下沉器会话持有处理器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerSessionHoldHandler extends Handler {

    /**
     * 获取指定的下沉器会话。
     *
     * <p>
     * 如果指定的下沉器会话不存在，则尝试基于下沉器信息进行创建；如果下沉器信息不存在，则抛出异常。
     *
     * @param sinkerInfoKey 指定的下沉器信息主键。
     * @return 指定的下沉器会话。
     * @throws HandlerException 处理器异常。
     */
    SinkerSession get(LongIdKey sinkerInfoKey) throws HandlerException;

    /**
     * 关闭并清除所有的下沉器会话。
     *
     * <p>
     * 该方法会关闭所有当前持有的下沉器会话，并清除所有的下沉器会话。
     *
     * @throws HandlerException 处理器异常。
     */
    void closeAndClear() throws HandlerException;
}

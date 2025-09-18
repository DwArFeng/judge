package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 提供器不存在异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class ProviderNotExistsException extends HandlerException {

    private static final long serialVersionUID = -2030949612780910171L;

    private final LongIdKey providerKey;

    public ProviderNotExistsException(LongIdKey providerKey) {
        this.providerKey = providerKey;
    }

    public ProviderNotExistsException(Throwable cause, LongIdKey providerKey) {
        super(cause);
        this.providerKey = providerKey;
    }

    @Override
    public String getMessage() {
        return "提供器 " + providerKey + " 不存在";
    }
}

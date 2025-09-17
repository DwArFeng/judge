package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 无效的任务状态异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class InvalidTaskStatusException extends HandlerException {

    private static final long serialVersionUID = -7556037820490088539L;

    private final int status;

    public InvalidTaskStatusException(int status) {
        this.status = status;
    }

    public InvalidTaskStatusException(Throwable cause, int status) {
        super(cause);
        this.status = status;
    }

    @Override
    public String getMessage() {
        return "无效的任务状态: " + status;
    }
}

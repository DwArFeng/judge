package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 部件不存在异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class SectionNotExistsException extends HandlerException {

    private static final long serialVersionUID = 3214014915306989579L;

    private final LongIdKey sectionKey;

    public SectionNotExistsException(LongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public SectionNotExistsException(String message, Throwable cause, LongIdKey sectionKey) {
        super(message, cause);
        this.sectionKey = sectionKey;
    }

    public SectionNotExistsException(String message, LongIdKey sectionKey) {
        super(message);
        this.sectionKey = sectionKey;
    }

    public SectionNotExistsException(Throwable cause, LongIdKey sectionKey) {
        super(cause);
        this.sectionKey = sectionKey;
    }

    @Override
    public String getMessage() {
        return "部件信息 " + sectionKey + " 不存在";
    }
}

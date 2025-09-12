package com.dwarfeng.judge.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public final class ServiceExceptionCodes {

    private static int EXCEPTION_CODE_OFFSET = 11000;

    public static final ServiceException.Code ANALYSER_INFO_NOT_EXISTS =
            new ServiceException.Code(offset(0), "analyser info not exists");
    public static final ServiceException.Code JUDGER_INFO_NOT_EXISTS =
            new ServiceException.Code(offset(10), "judger info not exists");
    public static final ServiceException.Code ANALYSER_VARIABLE_NOT_EXISTS =
            new ServiceException.Code(offset(20), "analyser variable not exists");
    public static final ServiceException.Code JUDGER_VARIABLE_NOT_EXISTS =
            new ServiceException.Code(offset(30), "judger variable not exists");
    public static final ServiceException.Code SECTION_NOT_EXISTS =
            new ServiceException.Code(offset(40), "section not exists");
    public static final ServiceException.Code TASK_NOT_EXISTS =
            new ServiceException.Code(offset(50), "task not exists");
    public static final ServiceException.Code TASK_STATUS_MISMATCH =
            new ServiceException.Code(offset(60), "task status mismatch");
    public static final ServiceException.Code INVALID_TASK_STATUS =
            new ServiceException.Code(offset(70), "invalid task status");

    private static int offset(int i) {
        return EXCEPTION_CODE_OFFSET + i;
    }

    /**
     * 获取异常代号的偏移量。
     *
     * @return 异常代号的偏移量。
     */
    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    /**
     * 设置异常代号的偏移量。
     *
     * @param exceptionCodeOffset 指定的异常代号的偏移量。
     */
    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        // 设置 EXCEPTION_CODE_OFFSET 的值。
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;

        // 以新的 EXCEPTION_CODE_OFFSET 为基准，更新异常代码的值。
        ANALYSER_INFO_NOT_EXISTS.setCode(offset(0));
        JUDGER_INFO_NOT_EXISTS.setCode(offset(10));
        ANALYSER_VARIABLE_NOT_EXISTS.setCode(offset(20));
        JUDGER_VARIABLE_NOT_EXISTS.setCode(offset(30));
        SECTION_NOT_EXISTS.setCode(offset(40));
        TASK_NOT_EXISTS.setCode(offset(50));
        TASK_STATUS_MISMATCH.setCode(offset(60));
        INVALID_TASK_STATUS.setCode(offset(70));
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}

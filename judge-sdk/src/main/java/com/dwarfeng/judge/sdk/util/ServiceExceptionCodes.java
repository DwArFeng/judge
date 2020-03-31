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

    public static final ServiceException.Code JUDGER_FAILED =
            new ServiceException.Code(EXCEPTION_CODE_OFFSET, "judger failed");
    public static final ServiceException.Code JUDGER_MAKE_FAILED =
            new ServiceException.Code(EXCEPTION_CODE_OFFSET + 1, "judger make failed");
    public static final ServiceException.Code JUDGER_TYPE_UNSUPPORTED =
            new ServiceException.Code(EXCEPTION_CODE_OFFSET + 2, "judger type unsupported");
    public static final ServiceException.Code REPOSITORY_FAILED =
            new ServiceException.Code(EXCEPTION_CODE_OFFSET + 10, "repository failed");
    public static final ServiceException.Code SINK_FAILED =
            new ServiceException.Code(EXCEPTION_CODE_OFFSET + 20, "sink failed");
    public static final ServiceException.Code DRIVER_FAILED =
            new ServiceException.Code(EXCEPTION_CODE_OFFSET + 30, "driver failed");
    public static final ServiceException.Code DRIVER_TYPE_UNSUPPORTED =
            new ServiceException.Code(EXCEPTION_CODE_OFFSET + 31, "driver type unsupported");
    public static final ServiceException.Code JUDGE_WORK_FAILED =
            new ServiceException.Code(EXCEPTION_CODE_OFFSET + 40, "judge work failed");
    public static final ServiceException.Code JUDGE_WORK_DISABLED =
            new ServiceException.Code(EXCEPTION_CODE_OFFSET + 41, "judge work disabled");
    public static final ServiceException.Code SECTION_NOT_EXISTS =
            new ServiceException.Code(EXCEPTION_CODE_OFFSET + 50, "section not exists");

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
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}

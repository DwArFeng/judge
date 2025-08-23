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
            new ServiceException.Code(offset(0), "judger failed");
    public static final ServiceException.Code JUDGER_MAKE_FAILED =
            new ServiceException.Code(offset(1), "judger make failed");
    public static final ServiceException.Code JUDGER_TYPE_UNSUPPORTED =
            new ServiceException.Code(offset(2), "judger type unsupported");
    public static final ServiceException.Code SINK_FAILED =
            new ServiceException.Code(offset(10), "sink failed");
    public static final ServiceException.Code DRIVER_FAILED =
            new ServiceException.Code(offset(20), "driver failed");
    public static final ServiceException.Code DRIVER_TYPE_UNSUPPORTED =
            new ServiceException.Code(offset(21), "driver type unsupported");
    public static final ServiceException.Code JUDGE_WORK_FAILED =
            new ServiceException.Code(offset(30), "judge work failed");
    public static final ServiceException.Code JUDGE_WORK_DISABLED =
            new ServiceException.Code(offset(31), "judge work disabled");
    public static final ServiceException.Code SECTION_NOT_EXISTS =
            new ServiceException.Code(offset(40), "section not exists");

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
        JUDGER_FAILED.setCode(offset(0));
        JUDGER_MAKE_FAILED.setCode(offset(1));
        JUDGER_TYPE_UNSUPPORTED.setCode(offset(2));
        SINK_FAILED.setCode(offset(10));
        DRIVER_FAILED.setCode(offset(20));
        DRIVER_TYPE_UNSUPPORTED.setCode(offset(21));
        JUDGE_WORK_FAILED.setCode(offset(30));
        JUDGE_WORK_DISABLED.setCode(offset(31));
        SECTION_NOT_EXISTS.setCode(offset(40));
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}

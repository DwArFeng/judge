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
    public static final ServiceException.Code ANALYSIS_FILE_NOT_EXISTS =
            new ServiceException.Code(offset(80), "analysis file not exists");
    public static final ServiceException.Code ANALYSIS_FILE_PACK_ITEM_NOT_EXISTS =
            new ServiceException.Code(offset(90), "analysis file pack item not exists");
    public static final ServiceException.Code ANALYSIS_FILE_PACK_NOT_EXISTS =
            new ServiceException.Code(offset(100), "analysis file pack not exists");
    public static final ServiceException.Code ANALYSIS_PICTURE_NOT_EXISTS =
            new ServiceException.Code(offset(110), "analysis picture not exists");
    public static final ServiceException.Code ANALYSIS_PICTURE_PACK_ITEM_NOT_EXISTS =
            new ServiceException.Code(offset(120), "analysis picture pack item not exists");
    public static final ServiceException.Code ANALYSIS_PICTURE_PACK_NOT_EXISTS =
            new ServiceException.Code(offset(130), "analysis picture pack not exists");
    public static final ServiceException.Code ANALYSE_DATA_TYPE_ANALYSIS_VALUE_MISMATCH =
            new ServiceException.Code(offset(140), "analyse data type analysis value mismatch");
    public static final ServiceException.Code ANALYSIS_NOT_EXISTS =
            new ServiceException.Code(offset(150), "analysis not exists");
    public static final ServiceException.Code INVALID_ANALYSIS_DATA_TYPE =
            new ServiceException.Code(offset(160), "invalid analysis data type");
    public static final ServiceException.Code INVALID_ANALYSIS_FILE_PACK_UPSERT_TYPE =
            new ServiceException.Code(offset(170), "invalid analysis file pack upsert type");
    public static final ServiceException.Code INVALID_ANALYSIS_PICTURE_PACK_UPSERT_TYPE =
            new ServiceException.Code(offset(180), "invalid analysis picture pack upsert type");
    public static final ServiceException.Code ANALYSER_FAILED =
            new ServiceException.Code(offset(190), "analyser failed");
    public static final ServiceException.Code ANALYSER_MAKE_FAILED =
            new ServiceException.Code(offset(191), "analyser make failed");
    public static final ServiceException.Code ANALYSER_EXECUTION_FAILED =
            new ServiceException.Code(offset(192), "analyser execution failed");
    public static final ServiceException.Code ANALYSER_TYPE_UNSUPPORTED =
            new ServiceException.Code(offset(193), "analyser type unsupported");

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
        ANALYSIS_FILE_NOT_EXISTS.setCode(offset(80));
        ANALYSIS_FILE_PACK_ITEM_NOT_EXISTS.setCode(offset(90));
        ANALYSIS_FILE_PACK_NOT_EXISTS.setCode(offset(100));
        ANALYSIS_PICTURE_NOT_EXISTS.setCode(offset(110));
        ANALYSIS_PICTURE_PACK_ITEM_NOT_EXISTS.setCode(offset(120));
        ANALYSIS_PICTURE_PACK_NOT_EXISTS.setCode(offset(130));
        ANALYSE_DATA_TYPE_ANALYSIS_VALUE_MISMATCH.setCode(offset(140));
        ANALYSIS_NOT_EXISTS.setCode(offset(150));
        INVALID_ANALYSIS_DATA_TYPE.setCode(offset(160));
        INVALID_ANALYSIS_FILE_PACK_UPSERT_TYPE.setCode(offset(170));
        INVALID_ANALYSIS_PICTURE_PACK_UPSERT_TYPE.setCode(offset(180));
        ANALYSER_FAILED.setCode(offset(190));
        ANALYSER_MAKE_FAILED.setCode(offset(191));
        ANALYSER_EXECUTION_FAILED.setCode(offset(192));
        ANALYSER_TYPE_UNSUPPORTED.setCode(offset(193));
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}

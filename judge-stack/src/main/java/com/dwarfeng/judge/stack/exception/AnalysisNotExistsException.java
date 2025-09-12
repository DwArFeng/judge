package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 分析结果不存在异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisNotExistsException extends HandlerException {

    private static final long serialVersionUID = -3545850123125906111L;

    private final AnalysisKey analysisKey;

    public AnalysisNotExistsException(AnalysisKey analysisKey) {
        this.analysisKey = analysisKey;
    }

    public AnalysisNotExistsException(Throwable cause, AnalysisKey analysisKey) {
        super(cause);
        this.analysisKey = analysisKey;
    }

    @Override
    public String getMessage() {
        return "分析结果 " + analysisKey + " 不存在";
    }
}

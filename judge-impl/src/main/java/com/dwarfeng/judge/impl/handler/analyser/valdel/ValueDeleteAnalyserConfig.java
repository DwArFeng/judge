package com.dwarfeng.judge.impl.handler.analyser.valdel;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * 值删除分析器配置。
 *
 * <p>
 * 该配置用于描述待删除 <code>Analysis</code> 的 <code>dataStringId</code>。
 * 分析器运行时仅删除当前任务下对应键的分析结果。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
public class ValueDeleteAnalyserConfig implements Bean {

    private static final long serialVersionUID = -7033642000208260459L;

    @JSONField(name = "#delete_analysis_data_id", ordinal = 1)
    private String deleteAnalysisDataIdRem = "待删除 Analysis 的 dataStringId，必填。";

    @JSONField(name = "delete_analysis_data_id", ordinal = 2)
    private String deleteAnalysisDataId;

    public ValueDeleteAnalyserConfig() {
    }

    public ValueDeleteAnalyserConfig(String deleteAnalysisDataId) {
        this.deleteAnalysisDataId = deleteAnalysisDataId;
    }

    public String getDeleteAnalysisDataIdRem() {
        return deleteAnalysisDataIdRem;
    }

    public void setDeleteAnalysisDataIdRem(String deleteAnalysisDataIdRem) {
        this.deleteAnalysisDataIdRem = deleteAnalysisDataIdRem;
    }

    public String getDeleteAnalysisDataId() {
        return deleteAnalysisDataId;
    }

    public void setDeleteAnalysisDataId(String deleteAnalysisDataId) {
        this.deleteAnalysisDataId = deleteAnalysisDataId;
    }

    @Override
    public String toString() {
        return "ValueDeleteAnalyserConfig{" +
                "deleteAnalysisDataId='" + deleteAnalysisDataId + '\'' +
                '}';
    }
}

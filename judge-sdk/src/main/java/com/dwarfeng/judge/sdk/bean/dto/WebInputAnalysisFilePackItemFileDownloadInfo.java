package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackItemFileDownloadInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 分析结果文件包条目文件下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputAnalysisFilePackItemFileDownloadInfo implements Bean {

    private static final long serialVersionUID = -4030271226355183080L;

    public static AnalysisFilePackItemFileDownloadInfo toStackBean(
            WebInputAnalysisFilePackItemFileDownloadInfo webInput
    ) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AnalysisFilePackItemFileDownloadInfo(
                    WebInputLongIdKey.toStackBean(webInput.getAnalysisFilePackItemKey())
            );
        }
    }

    @JSONField(name = "analysis_file_pack_item_key")
    @Valid
    @NotNull
    private WebInputLongIdKey analysisFilePackItemKey;

    public WebInputAnalysisFilePackItemFileDownloadInfo() {
    }

    public WebInputLongIdKey getAnalysisFilePackItemKey() {
        return analysisFilePackItemKey;
    }

    public void setAnalysisFilePackItemKey(WebInputLongIdKey analysisFilePackItemKey) {
        this.analysisFilePackItemKey = analysisFilePackItemKey;
    }

    @Override
    public String toString() {
        return "WebInputAnalysisFilePackItemFileDownloadInfo{" +
                "analysisFilePackItemKey=" + analysisFilePackItemKey +
                '}';
    }
}

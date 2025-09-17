package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFileFileDownloadInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 分析结果文件文件下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class WebInputAnalysisFileFileDownloadInfo implements Bean {

    private static final long serialVersionUID = -4323194308399057063L;

    public static AnalysisFileFileDownloadInfo toStackBean(WebInputAnalysisFileFileDownloadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AnalysisFileFileDownloadInfo(
                    WebInputLongIdKey.toStackBean(webInput.getAnalysisFileInfoKey())
            );
        }
    }

    @JSONField(name = "analysis_file_info_key")
    @Valid
    @NotNull
    private WebInputLongIdKey analysisFileInfoKey;

    public WebInputAnalysisFileFileDownloadInfo() {
    }

    public WebInputLongIdKey getAnalysisFileInfoKey() {
        return analysisFileInfoKey;
    }

    public void setAnalysisFileInfoKey(WebInputLongIdKey analysisFileInfoKey) {
        this.analysisFileInfoKey = analysisFileInfoKey;
    }

    @Override
    public String toString() {
        return "WebInputAnalysisFileFileDownloadInfo{" +
                "analysisFileInfoKey=" + analysisFileInfoKey +
                '}';
    }
}

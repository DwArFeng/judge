package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.AnalysisPictureFileDownloadInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 分析结果图片文件下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputAnalysisPictureFileDownloadInfo implements Bean {

    private static final long serialVersionUID = 7111748621036898424L;

    public static AnalysisPictureFileDownloadInfo toStackBean(WebInputAnalysisPictureFileDownloadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AnalysisPictureFileDownloadInfo(
                    WebInputLongIdKey.toStackBean(webInput.getAnalysisPictureInfoKey())
            );
        }
    }

    @JSONField(name = "analysis_picture_info_key")
    @Valid
    @NotNull
    private WebInputLongIdKey analysisPictureInfoKey;

    public WebInputAnalysisPictureFileDownloadInfo() {
    }

    public WebInputLongIdKey getAnalysisPictureInfoKey() {
        return analysisPictureInfoKey;
    }

    public void setAnalysisPictureInfoKey(WebInputLongIdKey analysisPictureInfoKey) {
        this.analysisPictureInfoKey = analysisPictureInfoKey;
    }

    @Override
    public String toString() {
        return "WebInputAnalysisPictureFileDownloadInfo{" +
                "analysisPictureInfoKey=" + analysisPictureInfoKey +
                '}';
    }
}

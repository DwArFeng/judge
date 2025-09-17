package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.AnalysisPictureThumbnailDownloadInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 分析结果缩略图文件下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class WebInputAnalysisPictureThumbnailDownloadInfo implements Bean {

    private static final long serialVersionUID = 2586193470656709936L;

    public static AnalysisPictureThumbnailDownloadInfo toStackBean(
            WebInputAnalysisPictureThumbnailDownloadInfo webInput
    ) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AnalysisPictureThumbnailDownloadInfo(
                    WebInputLongIdKey.toStackBean(webInput.getAnalysisPictureInfoKey())
            );
        }
    }

    @JSONField(name = "analysis_picture_info_key")
    @Valid
    @NotNull
    private WebInputLongIdKey analysisPictureInfoKey;

    public WebInputAnalysisPictureThumbnailDownloadInfo() {
    }

    public WebInputLongIdKey getAnalysisPictureInfoKey() {
        return analysisPictureInfoKey;
    }

    public void setAnalysisPictureInfoKey(WebInputLongIdKey analysisPictureInfoKey) {
        this.analysisPictureInfoKey = analysisPictureInfoKey;
    }

    @Override
    public String toString() {
        return "WebInputAnalysisPictureThumbnailDownloadInfo{" +
                "analysisPictureInfoKey=" + analysisPictureInfoKey +
                '}';
    }
}

package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.AnalysisPicturePackItemThumbnailDownloadInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 分析结果图片包条目缩略图下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputAnalysisPicturePackItemThumbnailDownloadInfo implements Bean {

    private static final long serialVersionUID = -6903321277022645259L;

    public static AnalysisPicturePackItemThumbnailDownloadInfo toStackBean(
            WebInputAnalysisPicturePackItemThumbnailDownloadInfo webInput
    ) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AnalysisPicturePackItemThumbnailDownloadInfo(
                    WebInputLongIdKey.toStackBean(webInput.getAnalysisPicturePackItemKey())
            );
        }
    }

    @JSONField(name = "analysis_picture_pack_item_key")
    @Valid
    @NotNull
    private WebInputLongIdKey analysisPicturePackItemKey;

    public WebInputAnalysisPicturePackItemThumbnailDownloadInfo() {
    }

    public WebInputLongIdKey getAnalysisPicturePackItemKey() {
        return analysisPicturePackItemKey;
    }

    public void setAnalysisPicturePackItemKey(WebInputLongIdKey analysisPicturePackItemKey) {
        this.analysisPicturePackItemKey = analysisPicturePackItemKey;
    }

    @Override
    public String toString() {
        return "WebInputAnalysisPicturePackItemThumbnailDownloadInfo{" +
                "analysisPicturePackItemKey=" + analysisPicturePackItemKey +
                '}';
    }
}

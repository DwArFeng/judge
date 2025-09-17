package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.AnalysisPicturePackItemFileDownloadInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 分析结果图片包条目文件下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class WebInputAnalysisPicturePackItemFileDownloadInfo implements Bean {

    private static final long serialVersionUID = -5771971170229925563L;

    public static AnalysisPicturePackItemFileDownloadInfo toStackBean(
            WebInputAnalysisPicturePackItemFileDownloadInfo webInput
    ) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AnalysisPicturePackItemFileDownloadInfo(
                    WebInputLongIdKey.toStackBean(webInput.getAnalysisPicturePackItemKey())
            );
        }
    }

    @JSONField(name = "analysis_picture_pack_item_key")
    @Valid
    @NotNull
    private WebInputLongIdKey analysisPicturePackItemKey;

    public WebInputAnalysisPicturePackItemFileDownloadInfo() {
    }

    public WebInputLongIdKey getAnalysisPicturePackItemKey() {
        return analysisPicturePackItemKey;
    }

    public void setAnalysisPicturePackItemKey(WebInputLongIdKey analysisPicturePackItemKey) {
        this.analysisPicturePackItemKey = analysisPicturePackItemKey;
    }

    @Override
    public String toString() {
        return "WebInputAnalysisPicturePackItemFileDownloadInfo{" +
                "analysisPicturePackItemKey=" + analysisPicturePackItemKey +
                '}';
    }
}

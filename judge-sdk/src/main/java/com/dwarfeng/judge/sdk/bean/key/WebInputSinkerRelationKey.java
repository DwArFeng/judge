package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.key.SinkerRelationKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * WebInput 下沉关联主键。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class WebInputSinkerRelationKey implements Key {

    private static final long serialVersionUID = -6025514279778228501L;

    public static SinkerRelationKey toStackBean(WebInputSinkerRelationKey webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SinkerRelationKey(
                    webInput.getSectionLongId(),
                    webInput.getSinkerLongId()
            );
        }
    }

    @JSONField(name = "section_id")
    private Long sectionLongId;

    @JSONField(name = "sinker_id")
    private Long sinkerLongId;

    public WebInputSinkerRelationKey() {
    }

    public Long getSectionLongId() {
        return sectionLongId;
    }

    public void setSectionLongId(Long sectionLongId) {
        this.sectionLongId = sectionLongId;
    }

    public Long getSinkerLongId() {
        return sinkerLongId;
    }

    public void setSinkerLongId(Long sinkerLongId) {
        this.sinkerLongId = sinkerLongId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebInputSinkerRelationKey that = (WebInputSinkerRelationKey) o;

        if (!Objects.equals(sectionLongId, that.sectionLongId))
            return false;
        return Objects.equals(sinkerLongId, that.sinkerLongId);
    }

    @Override
    public int hashCode() {
        int result = sectionLongId != null ? sectionLongId.hashCode() : 0;
        result = 31 * result + (sinkerLongId != null ? sinkerLongId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WebInputSinkerRelationKey{" +
                "sectionLongId=" + sectionLongId +
                ", sinkerLongId=" + sinkerLongId +
                '}';
    }
} 

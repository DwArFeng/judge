package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.WebInputSinkerMetaKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.entity.SinkerMeta;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 下沉关联元数据。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class WebInputSinkerMeta implements Bean {

    private static final long serialVersionUID = -1408205750426644588L;

    public static SinkerMeta toStackBean(WebInputSinkerMeta webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SinkerMeta(
                    WebInputSinkerMetaKey.toStackBean(webInput.getKey()),
                    webInput.getValue(),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputSinkerMetaKey key;

    @JSONField(name = "value")
    private String value;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputSinkerMeta() {
    }

    public WebInputSinkerMetaKey getKey() {
        return key;
    }

    public void setKey(WebInputSinkerMetaKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputSinkerMeta{" +
                "key=" + key +
                ", value=" + value + '\'' +
                ", remark=" + remark + '\'' +
                '}';
    }
} 

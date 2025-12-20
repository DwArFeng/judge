package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.WebInputSinkerRelationKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.entity.SinkerRelation;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 下沉器关联信息。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class WebInputSinkerRelation implements Bean {

    private static final long serialVersionUID = -4975359994411467384L;

    public static SinkerRelation toStackBean(WebInputSinkerRelation webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SinkerRelation(
                    WebInputSinkerRelationKey.toStackBean(webInput.getKey()),
                    webInput.getEnable(),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputSinkerRelationKey key;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputSinkerRelation() {
    }

    public WebInputSinkerRelationKey getKey() {
        return key;
    }

    public void setKey(WebInputSinkerRelationKey key) {
        this.key = key;
    }

    public boolean getEnable() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputSinkerRelation{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", remark=" + remark + '\'' +
                '}';
    }
} 

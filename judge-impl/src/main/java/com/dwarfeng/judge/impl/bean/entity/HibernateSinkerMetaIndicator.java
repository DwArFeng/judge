package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.impl.bean.key.HibernateSinkerMetaIndicatorKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateSinkerMetaIndicatorKey.class)
@Table(name = "tbl_sinker_meta_indicator")
public class HibernateSinkerMetaIndicator implements Bean {

    private static final long serialVersionUID = -2625419652823981827L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "sinker_type_id", length = Constraints.LENGTH_TYPE, nullable = false)
    private String sinkerTypeStringId;

    @Id
    @Column(name = "meta_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String metaStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "label", length = Constraints.LENGTH_LABEL, nullable = false)
    private String label;

    @Column(name = "default_value", columnDefinition = "TEXT")
    private String defaultValue;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public HibernateSinkerMetaIndicator() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateSinkerMetaIndicatorKey getKey() {
        if (Objects.isNull(sinkerTypeStringId) || Objects.isNull(metaStringId)) {
            return null;
        }
        return new HibernateSinkerMetaIndicatorKey(sinkerTypeStringId, metaStringId);
    }

    public void setKey(HibernateSinkerMetaIndicatorKey key) {
        if (Objects.isNull(key)) {
            this.sinkerTypeStringId = null;
            this.metaStringId = null;
        } else {
            this.sinkerTypeStringId = key.getSinkerTypeStringId();
            this.metaStringId = key.getMetaStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public String getSinkerTypeStringId() {
        return sinkerTypeStringId;
    }

    public void setSinkerTypeStringId(String sinkerTypeStringId) {
        this.sinkerTypeStringId = sinkerTypeStringId;
    }

    public String getMetaStringId() {
        return metaStringId;
    }

    public void setMetaStringId(String metaStringId) {
        this.metaStringId = metaStringId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "sinkerTypeStringId = " + sinkerTypeStringId + ", " +
                "metaStringId = " + metaStringId + ", " +
                "label = " + label + ", " +
                "defaultValue = " + defaultValue + ", " +
                "description = " + description + ")";
    }
}

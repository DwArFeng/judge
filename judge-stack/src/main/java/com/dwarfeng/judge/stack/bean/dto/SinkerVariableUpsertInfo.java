package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 下沉器变量插入/更新信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerVariableUpsertInfo implements Dto {

    private static final long serialVersionUID = -7603490907344104854L;

    private LongIdKey sinkerInfoKey;
    private String sinkerVariableId;
    private String value;

    public SinkerVariableUpsertInfo() {
    }

    public SinkerVariableUpsertInfo(LongIdKey sinkerInfoKey, String sinkerVariableId, String value) {
        this.sinkerInfoKey = sinkerInfoKey;
        this.sinkerVariableId = sinkerVariableId;
        this.value = value;
    }

    public LongIdKey getSinkerInfoKey() {
        return sinkerInfoKey;
    }

    public void setSinkerInfoKey(LongIdKey sinkerInfoKey) {
        this.sinkerInfoKey = sinkerInfoKey;
    }

    public String getSinkerVariableId() {
        return sinkerVariableId;
    }

    public void setSinkerVariableId(String sinkerVariableId) {
        this.sinkerVariableId = sinkerVariableId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SinkerVariableUpsertInfo{" +
                "sinkerInfoKey=" + sinkerInfoKey +
                ", sinkerVariableId='" + sinkerVariableId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

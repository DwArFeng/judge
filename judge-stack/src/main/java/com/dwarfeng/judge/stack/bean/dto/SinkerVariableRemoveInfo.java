package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 下沉器变量删除信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerVariableRemoveInfo implements Dto {

    private static final long serialVersionUID = -1539680982106645294L;
    
    private LongIdKey sinkerInfoKey;
    private String sinkerVariableId;

    public SinkerVariableRemoveInfo() {
    }

    public SinkerVariableRemoveInfo(LongIdKey sinkerInfoKey, String sinkerVariableId) {
        this.sinkerInfoKey = sinkerInfoKey;
        this.sinkerVariableId = sinkerVariableId;
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

    @Override
    public String toString() {
        return "SinkerVariableRemoveInfo{" +
                "sinkerInfoKey=" + sinkerInfoKey +
                ", sinkerVariableId='" + sinkerVariableId + '\'' +
                '}';
    }
}

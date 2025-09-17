package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 下沉器变量查看信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerVariableInspectInfo implements Dto {

    private static final long serialVersionUID = -7038769785175675795L;
    
    private LongIdKey sinkerInfoKey;
    private String sinkerVariableId;

    public SinkerVariableInspectInfo() {
    }

    public SinkerVariableInspectInfo(LongIdKey sinkerInfoKey, String sinkerVariableId) {
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
        return "SinkerVariableInspectInfo{" +
                "sinkerInfoKey=" + sinkerInfoKey +
                ", sinkerVariableId='" + sinkerVariableId + '\'' +
                '}';
    }
}

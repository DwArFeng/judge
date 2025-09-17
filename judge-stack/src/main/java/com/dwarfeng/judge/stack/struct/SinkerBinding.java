package com.dwarfeng.judge.stack.struct;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Map;

/**
 * 下沉器绑定本地缓存。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public final class SinkerBinding {

    private final Map<LongIdKey, Map<String, SinkerMetaInfo>> map;

    public SinkerBinding(Map<LongIdKey, Map<String, SinkerMetaInfo>> map) {
        this.map = map;
    }

    public Map<LongIdKey, Map<String, SinkerMetaInfo>> getMap() {
        return map;
    }

    @Override
    public String toString() {
        return "SinkerBinding{" +
                "map=" + map +
                '}';
    }
}

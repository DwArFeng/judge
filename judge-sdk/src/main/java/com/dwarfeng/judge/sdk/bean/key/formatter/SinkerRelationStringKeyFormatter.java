package com.dwarfeng.judge.sdk.bean.key.formatter;

import com.dwarfeng.judge.stack.bean.key.SinkerRelationKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * SinkerRelationKey 的文本格式化转换器。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class SinkerRelationStringKeyFormatter implements StringKeyFormatter<SinkerRelationKey> {

    private String prefix;

    public SinkerRelationStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(SinkerRelationKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getSectionLongId() + "_" + key.getSinkerLongId();
    }

    @Override
    public String generalFormat() {
        return prefix + Constants.REDIS_KEY_WILDCARD_CHARACTER;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "SinkerRelationStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

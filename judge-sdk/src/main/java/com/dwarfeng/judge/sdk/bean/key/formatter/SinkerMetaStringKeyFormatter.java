package com.dwarfeng.judge.sdk.bean.key.formatter;

import com.dwarfeng.judge.stack.bean.key.SinkerMetaKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * SinkerMetaKey 的文本格式化转换器。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class SinkerMetaStringKeyFormatter implements StringKeyFormatter<SinkerMetaKey> {

    private String prefix;

    public SinkerMetaStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(SinkerMetaKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getSectionLongId() + "_" + key.getSinkerLongId() + "_" + key.getMetaStringId();
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
        return "SinkerMetaStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

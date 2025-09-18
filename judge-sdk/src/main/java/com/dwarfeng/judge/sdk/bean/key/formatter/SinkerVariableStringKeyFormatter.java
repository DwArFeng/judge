package com.dwarfeng.judge.sdk.bean.key.formatter;

import com.dwarfeng.judge.stack.bean.key.SinkerVariableKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * SinkerVariableKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerVariableStringKeyFormatter implements StringKeyFormatter<SinkerVariableKey> {

    private String prefix;

    public SinkerVariableStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(SinkerVariableKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getSinkerLongId() + "_" + key.getVariableStringId();
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
        return "SinkerVariableStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

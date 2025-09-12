package com.dwarfeng.judge.sdk.bean.key.formatter;

import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * AnalyserVariableKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalyserVariableStringKeyFormatter implements StringKeyFormatter<AnalyserVariableKey> {

    private String prefix;

    public AnalyserVariableStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(AnalyserVariableKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getAnalyserInfoLongId() + "_" + key.getVariableStringId();
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
        return "AnalyserVariableStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

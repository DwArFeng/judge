package com.dwarfeng.judge.sdk.bean.key.formatter;

import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * AnalysisKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisStringKeyFormatter implements StringKeyFormatter<AnalysisKey> {

    private String prefix;

    public AnalysisStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(AnalysisKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getTaskLongId() + "_" + key.getDataStringId();
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
        return "AnalysisStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

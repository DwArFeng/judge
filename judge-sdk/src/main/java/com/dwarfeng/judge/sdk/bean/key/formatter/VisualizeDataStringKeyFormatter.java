package com.dwarfeng.judge.sdk.bean.key.formatter;

import com.dwarfeng.judge.stack.bean.key.VisualizeDataKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * VisualizeDataKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class VisualizeDataStringKeyFormatter implements StringKeyFormatter<VisualizeDataKey> {

    private String prefix;

    public VisualizeDataStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(VisualizeDataKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getTaskLongId() + "_" + key.getPerspectiveStringId();
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
        return "VisualizeDataStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

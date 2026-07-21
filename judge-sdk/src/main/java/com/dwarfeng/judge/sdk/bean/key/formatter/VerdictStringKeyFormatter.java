package com.dwarfeng.judge.sdk.bean.key.formatter;

import com.dwarfeng.judge.stack.bean.key.VerdictKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * VerdictKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class VerdictStringKeyFormatter implements StringKeyFormatter<VerdictKey> {

    private String prefix;

    public VerdictStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(VerdictKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getSectionLongId() + "_" + key.getDataStringId();
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
        return "VerdictStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

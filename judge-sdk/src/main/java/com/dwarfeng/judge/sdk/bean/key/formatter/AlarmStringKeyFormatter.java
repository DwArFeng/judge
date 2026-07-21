package com.dwarfeng.judge.sdk.bean.key.formatter;

import com.dwarfeng.judge.stack.bean.key.AlarmKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * AlarmKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class AlarmStringKeyFormatter implements StringKeyFormatter<AlarmKey> {

    private String prefix;

    public AlarmStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(AlarmKey key) {
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
        return "AlarmStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

package com.dwarfeng.judge.sdk.bean.key.formatter;

import com.dwarfeng.judge.stack.bean.key.JudgementKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * JudgementKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class JudgementStringKeyFormatter implements StringKeyFormatter<JudgementKey> {

    private String prefix;

    public JudgementStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(JudgementKey key) {
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
        return "JudgementStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

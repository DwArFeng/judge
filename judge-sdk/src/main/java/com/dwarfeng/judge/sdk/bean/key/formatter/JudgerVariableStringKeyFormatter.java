package com.dwarfeng.judge.sdk.bean.key.formatter;

import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * JudgerVariableKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JudgerVariableStringKeyFormatter implements StringKeyFormatter<JudgerVariableKey> {

    private String prefix;

    public JudgerVariableStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(JudgerVariableKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getJudgerInfoLongId() + "_" + key.getVariableStringId();
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
        return "JudgerVariableStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

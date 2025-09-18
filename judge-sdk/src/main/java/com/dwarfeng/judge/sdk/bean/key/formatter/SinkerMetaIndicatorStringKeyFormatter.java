package com.dwarfeng.judge.sdk.bean.key.formatter;

import com.dwarfeng.judge.stack.bean.key.SinkerMetaIndicatorKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * SinkerMetaIndicatorKey 的文本格式化转换器。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerMetaIndicatorStringKeyFormatter implements StringKeyFormatter<SinkerMetaIndicatorKey> {

    private String prefix;

    public SinkerMetaIndicatorStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(SinkerMetaIndicatorKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getSinkerTypeStringId() + "_" + key.getMetaStringId();
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
        return "SinkerMetaIndicatorStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

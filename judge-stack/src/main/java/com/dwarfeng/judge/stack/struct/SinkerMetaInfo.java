package com.dwarfeng.judge.stack.struct;

/**
 * 下沉器元数据信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public final class SinkerMetaInfo {

    /**
     * 元数据值。
     *
     * <p>
     * 元数据中的值，如果不存在，则为 <code>null</code>。
     */
    private final String metaValue;

    /**
     * 默认值。
     *
     * <p>
     * 元数据指示器中的值，如果不存在，则为 <code>null</code>。
     */
    private final String defaultValue;

    /**
     * 等效值。
     *
     * <p>
     * 如果元数据值不为 <code>null</code>，则等效值为元数据值；否则等效值为默认值。
     */
    private final String equivalentValue;

    public SinkerMetaInfo(String metaValue, String defaultValue, String equivalentValue) {
        this.metaValue = metaValue;
        this.defaultValue = defaultValue;
        this.equivalentValue = equivalentValue;
    }

    public String getMetaValue() {
        return metaValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getEquivalentValue() {
        return equivalentValue;
    }

    @Override
    public String toString() {
        return "SinkerMetaInfo{" +
                "metaValue='" + metaValue + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", equivalentValue='" + equivalentValue + '\'' +
                '}';
    }
}

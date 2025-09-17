package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Map;

/**
 * 下沉器元数据查询结果。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerMetaInspectResult implements Dto {

    private static final long serialVersionUID = 4957894345844594529L;

    private Map<String, MetaInfo> metaInfoMap;

    public SinkerMetaInspectResult() {
    }

    public SinkerMetaInspectResult(Map<String, MetaInfo> metaInfoMap) {
        this.metaInfoMap = metaInfoMap;
    }

    public Map<String, MetaInfo> getMetaInfoMap() {
        return metaInfoMap;
    }

    public void setMetaInfoMap(Map<String, MetaInfo> metaInfoMap) {
        this.metaInfoMap = metaInfoMap;
    }

    @Override
    public String toString() {
        return "SinkerMetaInspectResult{" +
                "metaInfoMap=" + metaInfoMap +
                '}';
    }

    /**
     * 元数据信息。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class MetaInfo implements Dto {

        private static final long serialVersionUID = -1440047482803018655L;

        /**
         * 元数据值。
         *
         * <p>
         * 元数据中的值，如果不存在，则为 <code>null</code>。
         */
        private String metaValue;

        /**
         * 默认值。
         *
         * <p>
         * 元数据指示器中的值，如果不存在，则为 <code>null</code>。
         */
        private String defaultValue;

        /**
         * 等效值。
         *
         * <p>
         * 如果元数据值不为 <code>null</code>，则等效值为元数据值；否则等效值为默认值。
         */
        private String equivalentValue;

        public MetaInfo() {
        }

        public MetaInfo(String metaValue, String defaultValue, String equivalentValue) {
            this.metaValue = metaValue;
            this.defaultValue = defaultValue;
            this.equivalentValue = equivalentValue;
        }

        public String getMetaValue() {
            return metaValue;
        }

        public void setMetaValue(String metaValue) {
            this.metaValue = metaValue;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getEquivalentValue() {
            return equivalentValue;
        }

        public void setEquivalentValue(String equivalentValue) {
            this.equivalentValue = equivalentValue;
        }

        @Override
        public String toString() {
            return "MetaInfo{" +
                    "metaValue='" + metaValue + '\'' +
                    ", defaultValue='" + defaultValue + '\'' +
                    ", equivalentValue='" + equivalentValue + '\'' +
                    '}';
        }
    }
}

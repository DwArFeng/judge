package com.dwarfeng.judge.sdk.handler;

import java.util.Map;

/**
 * 下沉器支持器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerSupporter {

    /**
     * 提供类型。
     *
     * @return 类型。
     */
    String provideType();

    /**
     * 提供标签。
     *
     * @return 标签。
     */
    String provideLabel();

    /**
     * 提供描述。
     *
     * @return 描述。
     */
    String provideDescription();

    /**
     * 提供示例参数。
     *
     * @return 示例参数。
     */
    String provideExampleParam();

    /**
     * 提供指示器信息映射。
     *
     * @return 指示器信息映射。
     */
    Map<String, IndicatorProvideInfo> provideIndicatorMap();

    /**
     * 指示器提供信息。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    final class IndicatorProvideInfo {

        private final String label;
        private final String initialValue;

        /**
         * 描述。
         *
         * <p>
         * 描述是对指示器的详细说明，通常用于解释指示器的用途、使用方法或其他相关信息。
         *
         * @since 2.1.0-beta
         */
        private final String description;

        public IndicatorProvideInfo(String label, String initialValue, String description) {
            this.label = label;
            this.initialValue = initialValue;
            this.description = description;
        }

        public String getLabel() {
            return label;
        }

        public String getInitialValue() {
            return initialValue;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "IndicatorProvideInfo{" +
                    "label='" + label + '\'' +
                    ", initialValue='" + initialValue + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}

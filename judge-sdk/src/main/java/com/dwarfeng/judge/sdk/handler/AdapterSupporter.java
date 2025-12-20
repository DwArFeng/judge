package com.dwarfeng.judge.sdk.handler;

/**
 * 适配器支持器。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.3.0
 */
public interface AdapterSupporter {

    /**
     * 提供类型。
     *
     * @return 类型。
     * @author DwArFeng
     * @since 2.4.0
     */
    String provideType();

    /**
     * 提供标签。
     *
     * @return 标签。
     * @author DwArFeng
     * @since 2.4.0
     */
    String provideLabel();

    /**
     * 提供描述。
     *
     * @return 描述。
     * @author DwArFeng
     * @since 2.4.0
     */
    String provideDescription();

    /**
     * 提供示例参数。
     *
     * @return 示例参数。
     * @author DwArFeng
     * @since 2.4.0
     */
    String provideExampleParam();
}

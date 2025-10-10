package com.dwarfeng.judge.sdk.handler;

/**
 * 适配器支持器。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface AdapterSupporter {

    /**
     * 适配类型。
     *
     * @return 类型。
     */
    String adaptType();

    /**
     * 适配标签。
     *
     * @return 标签。
     */
    String adaptLabel();

    /**
     * 适配描述。
     *
     * @return 描述。
     */
    String adaptDescription();

    /**
     * 适配示例参数。
     *
     * @return 示例参数。
     */
    String adaptExampleParam();
}

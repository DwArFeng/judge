package com.dwarfeng.judge.sdk.handler;

/**
 * 过滤器支持器。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface FilterSupporter {

    /**
     * 过滤类型。
     *
     * @return 类型。
     */
    String filtType();

    /**
     * 过滤标签。
     *
     * @return 标签。
     */
    String filtLabel();

    /**
     * 过滤描述。
     *
     * @return 描述。
     */
    String filtDescription();

    /**
     * 过滤示例参数。
     *
     * @return 示例参数。
     */
    String filtExampleParam();
}

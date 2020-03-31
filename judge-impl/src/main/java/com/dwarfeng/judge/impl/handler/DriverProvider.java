package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.handler.Driver;

/**
 * 驱动器构造器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface DriverProvider {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 提供驱动器。
     *
     * @return 被提供的驱动器。
     */
    Driver provide();

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
     * 提供示例内容。
     *
     * @return 示例内容。
     */
    String provideExampleContent();
}

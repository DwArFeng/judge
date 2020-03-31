package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.exception.DriverException;

/**
 * 驱动器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface Driver {

    /**
     * 返回驱动器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 驱动器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 注册指定的驱动器信息。
     *
     * @param driverInfo 指定的驱动器信息。
     * @throws DriverException 驱动异常。
     */
    void register(DriverInfo driverInfo) throws DriverException;

    /**
     * 解除注册所有的驱动器信息。
     *
     * @throws DriverException 驱动异常。
     */
    void unregisterAll() throws DriverException;

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

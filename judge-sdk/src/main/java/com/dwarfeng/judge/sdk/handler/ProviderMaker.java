package com.dwarfeng.judge.sdk.handler;

import com.dwarfeng.judge.stack.exception.ProviderException;
import com.dwarfeng.judge.stack.handler.Provider;

/**
 * 提供器制造器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface ProviderMaker {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 根据指定的提供器信息生成一个提供器。
     *
     * <p>
     * 可以保证传入的提供器信息中的类型是支持的。
     *
     * @param type  提供器类型。
     * @param param 提供器参数。
     * @return 生成的提供器。
     * @throws ProviderException 提供器异常。
     */
    Provider makeProvider(String type, String param) throws ProviderException;
}

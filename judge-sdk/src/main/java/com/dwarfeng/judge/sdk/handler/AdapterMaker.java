package com.dwarfeng.judge.sdk.handler;

import com.dwarfeng.judge.stack.exception.AdapterException;
import com.dwarfeng.judge.stack.handler.Adapter;

/**
 * 适配器制造器。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface AdapterMaker {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 根据指定的适配器信息生成一个适配器。
     *
     * <p>
     * 可以保证传入的适配器信息中的类型是支持的。
     *
     * @param type  适配器类型。
     * @param param 适配器参数。
     * @return 生成的适配器。
     * @throws AdapterException 适配器异常。
     */
    Adapter makeAdapter(String type, String param) throws AdapterException;
}

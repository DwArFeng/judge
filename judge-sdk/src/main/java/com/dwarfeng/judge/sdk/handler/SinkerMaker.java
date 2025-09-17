package com.dwarfeng.judge.sdk.handler;

import com.dwarfeng.judge.stack.exception.SinkerException;
import com.dwarfeng.judge.stack.handler.Sinker;

/**
 * 下沉器制造器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerMaker {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 根据指定的下沉器信息生成一个下沉器。
     *
     * <p>
     * 可以保证传入的下沉器信息中的类型是支持的。
     *
     * @param type  下沉器类型。
     * @param param 下沉器参数。
     * @return 生成的下沉器。
     * @throws SinkerException 下沉器异常。
     */
    Sinker makeSinker(String type, String param) throws SinkerException;
}

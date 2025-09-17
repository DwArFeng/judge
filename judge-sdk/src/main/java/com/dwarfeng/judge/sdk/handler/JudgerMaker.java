package com.dwarfeng.judge.sdk.handler;

import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.judge.stack.handler.Judger;

/**
 * 判断器构造器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface JudgerMaker {

    /**
     * 返回构造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 构造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 根据指定的判断器信息生成一个判断器对象。
     *
     * <p>
     * 可以保证传入的判断器信息中的类型是支持的。
     *
     * @param type  判断器类型。
     * @param param 判断器参数。
     * @return 构造的判断器。
     * @throws JudgerException 判断器异常。
     */
    Judger makeJudger(String type, String param) throws JudgerException;
}

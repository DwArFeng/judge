package com.dwarfeng.judge.sdk.handler;

import com.dwarfeng.judge.stack.exception.AnalyserException;
import com.dwarfeng.judge.stack.handler.Analyser;

/**
 * 分析器构造器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalyserMaker {

    /**
     * 返回构造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 构造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 根据指定的分析器信息生成一个分析器对象。
     *
     * <p>
     * 可以保证传入的分析器信息中的类型是支持的。
     *
     * @param type  分析器类型。
     * @param param 分析器参数。
     * @return 构造的分析器。
     * @throws AnalyserException 分析器异常。
     */
    Analyser makeAnalyser(String type, String param) throws AnalyserException;
}

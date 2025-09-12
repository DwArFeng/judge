package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 分析器处理器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalyserHandler extends Handler {

    /**
     * 根据指定的分析器信息构造一个分析器。
     *
     * @param type    分析器类型。
     * @param content 分析器内容。
     * @return 构造的分析器。
     * @throws HandlerException 处理器异常。
     */
    Analyser make(String type, String content) throws HandlerException;
}

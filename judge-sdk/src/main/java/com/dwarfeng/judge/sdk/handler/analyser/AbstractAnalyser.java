package com.dwarfeng.judge.sdk.handler.analyser;

import com.dwarfeng.judge.stack.exception.AnalyserException;
import com.dwarfeng.judge.stack.handler.Analyser;

/**
 * 分析器的抽象实现。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public abstract class AbstractAnalyser implements Analyser {

    @Override
    public Executor newExecutor() throws AnalyserException {
        try {
            return doNewExecutor();
        } catch (AnalyserException e) {
            throw e;
        } catch (Exception e) {
            throw new AnalyserException(e);
        }
    }

    protected abstract Executor doNewExecutor() throws Exception;

    @Override
    public String toString() {
        return "AbstractAnalyser{}";
    }
}

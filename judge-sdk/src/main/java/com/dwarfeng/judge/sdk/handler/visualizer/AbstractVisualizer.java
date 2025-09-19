package com.dwarfeng.judge.sdk.handler.visualizer;

import com.dwarfeng.judge.stack.exception.VisualizerException;
import com.dwarfeng.judge.stack.handler.Visualizer;

/**
 * 可视化器的抽象实现。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public abstract class AbstractVisualizer implements Visualizer {

    @Override
    public Executor newExecutor() throws VisualizerException {
        try {
            return doNewExecutor();
        } catch (VisualizerException e) {
            throw e;
        } catch (Exception e) {
            throw new VisualizerException(e);
        }
    }

    protected abstract Executor doNewExecutor() throws Exception;

    @Override
    public String toString() {
        return "AbstractVisualizer{}";
    }
}

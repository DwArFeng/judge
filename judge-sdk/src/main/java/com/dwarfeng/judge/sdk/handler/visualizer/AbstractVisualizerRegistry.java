package com.dwarfeng.judge.sdk.handler.visualizer;

import com.dwarfeng.judge.sdk.handler.VisualizerMaker;
import com.dwarfeng.judge.sdk.handler.VisualizerSupporter;

import java.util.Objects;

/**
 * 抽象可视化器注册。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public abstract class AbstractVisualizerRegistry implements VisualizerMaker, VisualizerSupporter {

    protected String visualizerType;

    public AbstractVisualizerRegistry() {
    }

    public AbstractVisualizerRegistry(String visualizerType) {
        this.visualizerType = visualizerType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(visualizerType, type);
    }

    @Override
    public String provideType() {
        return visualizerType;
    }

    public String getVisualizerType() {
        return visualizerType;
    }

    public void setVisualizerType(String visualizerType) {
        this.visualizerType = visualizerType;
    }

    @Override
    public String toString() {
        return "AbstractVisualizerRegistry{" +
                "visualizerType='" + visualizerType + '\'' +
                '}';
    }
}

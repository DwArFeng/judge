package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.handler.VisualizerMaker;
import com.dwarfeng.judge.stack.exception.UnsupportedVisualizerTypeException;
import com.dwarfeng.judge.stack.exception.VisualizerException;
import com.dwarfeng.judge.stack.handler.Visualizer;
import com.dwarfeng.judge.stack.handler.VisualizerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class VisualizerHandlerImpl implements VisualizerHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisualizerHandlerImpl.class);

    private final List<VisualizerMaker> visualizerMakers;

    public VisualizerHandlerImpl(List<VisualizerMaker> visualizerMakers) {
        this.visualizerMakers = Optional.ofNullable(visualizerMakers).orElse(Collections.emptyList());
    }

    @Override
    public Visualizer make(String type, String param) throws VisualizerException {
        try {
            // 生成可视化器。
            LOGGER.debug("通过可视化器信息构建新的的可视化器...");
            VisualizerMaker visualizerMaker = visualizerMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedVisualizerTypeException(type));
            Visualizer visualizer = visualizerMaker.makeVisualizer(type, param);
            LOGGER.debug("可视化器构建成功!");
            LOGGER.debug("可视化器: {}", visualizer);
            return visualizer;
        } catch (VisualizerException e) {
            throw e;
        } catch (Exception e) {
            throw new VisualizerException(e);
        }
    }
}

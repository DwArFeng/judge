package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.handler.AnalyserMaker;
import com.dwarfeng.judge.stack.exception.AnalyserException;
import com.dwarfeng.judge.stack.exception.UnsupportedAnalyserTypeException;
import com.dwarfeng.judge.stack.handler.Analyser;
import com.dwarfeng.judge.stack.handler.AnalyserHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class AnalyserHandlerImpl implements AnalyserHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyserHandlerImpl.class);

    private final List<AnalyserMaker> analyserMakers;

    public AnalyserHandlerImpl(List<AnalyserMaker> analyserMakers) {
        this.analyserMakers = Optional.ofNullable(analyserMakers).orElse(Collections.emptyList());
    }

    @Override
    public Analyser make(String type, String param) throws AnalyserException {
        try {
            // 生成分析器。
            LOGGER.debug("通过分析器信息构建新的的分析器...");
            AnalyserMaker analyserMaker = analyserMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedAnalyserTypeException(type));
            Analyser analyser = analyserMaker.makeAnalyser(type, param);
            LOGGER.debug("分析器构建成功!");
            LOGGER.debug("分析器: {}", analyser);
            return analyser;
        } catch (AnalyserException e) {
            throw e;
        } catch (Exception e) {
            throw new AnalyserException(e);
        }
    }
}

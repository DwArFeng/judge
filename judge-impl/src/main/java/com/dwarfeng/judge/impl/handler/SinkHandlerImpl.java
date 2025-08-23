package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.handler.Sink;
import com.dwarfeng.judge.stack.bean.dto.SectionReport;
import com.dwarfeng.judge.stack.handler.SinkHandler;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class SinkHandlerImpl implements SinkHandler {

    private final List<Sink> sinks;

    @Value("${sink.type}")
    private String sinkType;

    private Sink sink;

    public SinkHandlerImpl(List<Sink> sinks) {
        this.sinks = Optional.ofNullable(sinks).orElse(Collections.emptyList());
    }

    @PostConstruct
    public void init() throws HandlerException {
        this.sink = sinks.stream().filter(s -> s.supportType(sinkType)).findAny()
                .orElseThrow(() -> new HandlerException("未知的 sink 类型: " + sinkType));
    }

    @Override
    public void sinkData(SectionReport sectionReport) throws HandlerException {
        try {
            sink.sinkData(sectionReport);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}

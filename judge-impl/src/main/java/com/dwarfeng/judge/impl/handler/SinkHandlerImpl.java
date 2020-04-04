package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.JudgedValue;
import com.dwarfeng.judge.stack.handler.SinkHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class SinkHandlerImpl implements SinkHandler {

    @Autowired(required = false)
    private List<Sink> sinks = new ArrayList<>();

    @Value("${sink.type}")
    private String sinkType;

    private Sink sink;

    @PostConstruct
    public void init() throws HandlerException {
        this.sink = sinks.stream().filter(s -> s.supportType(sinkType)).findAny()
                .orElseThrow(() -> new HandlerException("未知的 sink 类型: " + sinkType));
    }

    @Override
    public void sinkData(JudgedValue judgedValue) throws HandlerException {
        try {
            sink.sinkData(judgedValue);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}

package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dcti.stack.bean.dto.DataInfo;
import com.dwarfeng.judge.stack.handler.SinkHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SinkHandlerImpl implements SinkHandler {

    @Autowired
    private List<Sink> sinks;

    @Value("${sink.type}")
    private String sinkType;

    private Sink sink;

    @PostConstruct
    public void init() throws HandlerException {
        this.sink = sinks.stream().filter(s -> s.supportType(sinkType)).findAny()
                .orElseThrow(() -> new HandlerException("未知的 sink 类型: " + sinkType));
    }

    @Override
    public void sinkData(DataInfo dataInfo) throws HandlerException {
        try {
            sink.sinkData(dataInfo);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void sinkData(String message) throws HandlerException {
        try {
            sink.sinkData(message);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}

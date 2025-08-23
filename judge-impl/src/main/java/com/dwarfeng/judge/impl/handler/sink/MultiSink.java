package com.dwarfeng.judge.impl.handler.sink;

import com.dwarfeng.judge.sdk.handler.Sink;
import com.dwarfeng.judge.sdk.handler.sink.AbstractSink;
import com.dwarfeng.judge.stack.bean.dto.SectionReport;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 同时将消息推送给所有代理的多重水槽。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class MultiSink extends AbstractSink {

    public static final String SINK_TYPE = "multi";
    private static final Logger LOGGER = LoggerFactory.getLogger(MultiSink.class);

    private final List<Sink> sinks;

    @Value("${sink.multi.delegate_types}")
    private String delegateTypes;

    private final List<Sink> delegates = new ArrayList<>();

    public MultiSink(@Lazy List<Sink> sinks) {
        super(SINK_TYPE);
        this.sinks = Optional.ofNullable(sinks).orElse(Collections.emptyList());
    }

    @PostConstruct
    public void init() throws HandlerException {
        StringTokenizer st = new StringTokenizer(delegateTypes, ",");
        while (st.hasMoreTokens()) {
            String delegateType = st.nextToken();
            delegates.add(sinks.stream().filter(p -> p.supportType(delegateType)).findAny()
                    .orElseThrow(() -> new HandlerException("未知的 sink 类型: " + delegateType)));
        }
    }

    @Override
    public void sinkData(SectionReport sectionReport) {
        for (Sink delegate : delegates) {
            try {
                delegate.sinkData(sectionReport);
            } catch (Exception e) {
                LOGGER.warn("代理水槽推送数据失败，异常信息如下: ", e);
            }
        }
    }

    @Override
    public String toString() {
        return "MultiSink{" +
                "delegateTypes='" + delegateTypes + '\'' +
                ", sinkType='" + sinkType + '\'' +
                '}';
    }
}

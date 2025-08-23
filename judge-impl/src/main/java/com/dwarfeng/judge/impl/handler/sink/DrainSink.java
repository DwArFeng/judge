package com.dwarfeng.judge.impl.handler.sink;

import com.dwarfeng.judge.sdk.handler.sink.AbstractSink;
import com.dwarfeng.judge.stack.bean.dto.SectionReport;
import org.springframework.stereotype.Component;

/**
 * 简单的丢弃掉所有信息的水槽。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class DrainSink extends AbstractSink {

    public static final String SINK_TYPE = "drain";

    public DrainSink() {
        super(SINK_TYPE);
    }

    @Override
    public void sinkData(SectionReport sectionReport) {
    }

    @Override
    public String toString() {
        return "DrainSink{" +
                "sinkType='" + sinkType + '\'' +
                '}';
    }
}

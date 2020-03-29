package com.dwarfeng.judge.impl.handler.sink;

import com.dwarfeng.dcti.stack.bean.dto.DataInfo;
import com.dwarfeng.judge.impl.handler.Sink;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 简单的丢弃掉所有信息的水槽。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class DrainSink implements Sink {

    public static final String SUPPORT_TYPE = "drain";

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public void sinkData(DataInfo dataInfo) {
    }

    @Override
    public void sinkData(String message) {
    }
}

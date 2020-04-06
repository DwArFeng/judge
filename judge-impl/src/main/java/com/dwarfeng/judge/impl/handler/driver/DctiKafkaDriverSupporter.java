package com.dwarfeng.judge.impl.handler.driver;

import com.dwarfeng.judge.impl.handler.DriverSupporter;
import org.springframework.stereotype.Component;

/**
 * Dcti标准数据采集接口Kafka驱动支持器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Component
public class DctiKafkaDriverSupporter implements DriverSupporter {

    public static final String SUPPORT_TYPE = "dcti_kafka_driver";

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "Dcti标准数据采集接口Kafka驱动器";
    }

    @Override
    public String provideDescription() {
        return "从Kafka中接收到标准Dcti数据，并根据接收到的数据的主键对不同的部件进行触发。";
    }

    @Override
    public String provideExampleContent() {
        return "692653993448435712";
    }
}

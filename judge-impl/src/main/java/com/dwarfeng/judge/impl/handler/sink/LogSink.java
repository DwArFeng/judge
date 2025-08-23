package com.dwarfeng.judge.impl.handler.sink;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.bean.dto.FastJsonSectionReport;
import com.dwarfeng.judge.sdk.handler.sink.AbstractSink;
import com.dwarfeng.judge.stack.bean.dto.SectionReport;
import com.dwarfeng.judge.stack.exception.SinkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 将信息输出至日志的水槽。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
@Component
public class LogSink extends AbstractSink {

    public static final String SINK_TYPE = "log";

    private static final Logger LOGGER = LoggerFactory.getLogger(LogSink.class);

    private static final String LEVEL_TRACE = "TRACE";
    private static final String LEVEL_DEBUG = "DEBUG";
    private static final String LEVEL_INFO = "INFO";
    private static final String LEVEL_WARN = "WARN";
    private static final String LEVEL_ERROR = "ERROR";

    @Value("${sink.log.log_level}")
    private String logLevel;

    public LogSink() {
        super(SINK_TYPE);
    }

    @Override
    public void sinkData(SectionReport sectionReport) throws SinkException {
        String logLevel = this.logLevel.toUpperCase();
        String title = "下沉部件报告:";
        String message = JSON.toJSONString(FastJsonSectionReport.of(sectionReport), true);
        switch (logLevel) {
            case LEVEL_TRACE:
                LOGGER.trace(title);
                LOGGER.trace(message);
                return;
            case LEVEL_DEBUG:
                LOGGER.debug(title);
                LOGGER.debug(message);
                return;
            case LEVEL_INFO:
                LOGGER.info(title);
                LOGGER.info(message);
                return;
            case LEVEL_WARN:
                LOGGER.warn(title);
                LOGGER.warn(message);
                return;
            case LEVEL_ERROR:
                LOGGER.error(title);
                LOGGER.error(message);
                return;
            default:
                throw new SinkException("未知的日志等级: " + logLevel);
        }
    }
}

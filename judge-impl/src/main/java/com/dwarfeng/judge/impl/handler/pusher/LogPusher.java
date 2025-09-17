package com.dwarfeng.judge.impl.handler.pusher;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.bean.entity.FastJsonJudgementModal;
import com.dwarfeng.judge.sdk.bean.entity.FastJsonSection;
import com.dwarfeng.judge.sdk.handler.pusher.AbstractPusher;
import com.dwarfeng.judge.stack.bean.entity.JudgementModal;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 将信息输出至日志的推送器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
@Component
public class LogPusher extends AbstractPusher {

    public static final String PUSHER_TYPE = "log";

    private static final Logger LOGGER = LoggerFactory.getLogger(LogPusher.class);

    private static final String LEVEL_TRACE = "TRACE";
    private static final String LEVEL_DEBUG = "DEBUG";
    private static final String LEVEL_INFO = "INFO";
    private static final String LEVEL_WARN = "WARN";
    private static final String LEVEL_ERROR = "ERROR";

    @Value("${pusher.log.log_level}")
    private String logLevel;

    public LogPusher() {
        super(PUSHER_TYPE);
    }

    @Override
    public void taskFinished(Section section) throws HandlerException {
        String title = "推送任务完成消息:";
        String message = String.format(
                "部件:\n%s",
                JSON.toJSONString(FastJsonSection.of(section), true)
        );
        logData(title, message);
    }

    @Override
    public void taskFailed(Section section) throws HandlerException {
        String title = "推送任务失败消息:";
        String message = String.format(
                "部件:\n%s",
                JSON.toJSONString(FastJsonSection.of(section), true)
        );
        logData(title, message);
    }

    @Override
    public void taskExpired(Section section) throws HandlerException {
        String title = "推送任务过期消息:";
        String message = String.format(
                "部件:\n%s",
                JSON.toJSONString(FastJsonSection.of(section), true)
        );
        logData(title, message);
    }

    @Override
    public void taskDied(Section section) throws HandlerException {
        String title = "推送任务死亡消息:";
        String message = String.format(
                "部件:\n%s",
                JSON.toJSONString(FastJsonSection.of(section), true)
        );
        logData(title, message);
    }

    @Override
    public void judgementModalUpdated(JudgementModal judgementModal) throws HandlerException {
        String title = "推送判断结果模态更新消息:";
        String message = String.format(
                "判断结果模态:\n%s",
                JSON.toJSONString(FastJsonJudgementModal.of(judgementModal), true)
        );
        logData(title, message);
    }

    @Override
    public void jobReset() throws HandlerException {
        String title = "作业功能重置:";
        String message = StringUtils.EMPTY;
        logData(title, message);
    }

    @Override
    public void superviseReset() throws HandlerException {
        String title = "主管功能重置:";
        String message = StringUtils.EMPTY;
        logData(title, message);
    }

    private void logData(String title, String message) throws HandlerException {
        String logLevel = this.logLevel.toUpperCase();
        logString(title, logLevel);
        if (StringUtils.isNotEmpty(message)) {
            logString(message, logLevel);
        }
    }

    private void logString(String title, String logLevel) throws HandlerException {
        switch (logLevel) {
            case LEVEL_TRACE:
                LOGGER.trace(title);
                return;
            case LEVEL_DEBUG:
                LOGGER.debug(title);
                return;
            case LEVEL_INFO:
                LOGGER.info(title);
                return;
            case LEVEL_WARN:
                LOGGER.warn(title);
                return;
            case LEVEL_ERROR:
                LOGGER.error(title);
                return;
            default:
                throw new HandlerException("未知的日志等级: " + logLevel);
        }
    }

    @Override
    public String toString() {
        return "LogPusher{" +
                "logLevel='" + logLevel + '\'' +
                ", pusherType='" + pusherType + '\'' +
                '}';
    }
}

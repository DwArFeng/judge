package com.dwarfeng.judge.impl.handler.pusher;

import com.dwarfeng.judge.sdk.handler.Pusher;
import com.dwarfeng.judge.sdk.handler.pusher.AbstractPusher;
import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.judge.stack.bean.entity.JudgementModal;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 同时将消息推送给所有代理的多重推送器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
@Component
public class MultiPusher extends AbstractPusher {

    public static final String PUSHER_TYPE = "multi";

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiPusher.class);

    private final List<Pusher> pushers;

    @Value("${pusher.multi.delegate_types}")
    private String delegateTypes;

    private final List<Pusher> delegates = new ArrayList<>();

    public MultiPusher(List<Pusher> pushers) {
        super(PUSHER_TYPE);
        this.pushers = Optional.ofNullable(pushers).orElse(Collections.emptyList());
    }

    @PostConstruct
    public void init() throws HandlerException {
        StringTokenizer st = new StringTokenizer(delegateTypes, ",");
        while (st.hasMoreTokens()) {
            String delegateType = st.nextToken();
            delegates.add(pushers.stream().filter(p -> p.supportType(delegateType)).findAny()
                    .orElseThrow(() -> new HandlerException("未知的 pusher 类型: " + delegateType)));
        }
    }

    @Override
    public void taskFinished(Section section) {
        for (Pusher delegate : delegates) {
            try {
                delegate.taskFinished(section);
            } catch (Exception e) {
                LOGGER.warn("代理推送器推送消息失败，异常信息如下: ", e);
            }
        }
    }

    @Override
    public void taskFailed(Section section) {
        for (Pusher delegate : delegates) {
            try {
                delegate.taskFailed(section);
            } catch (Exception e) {
                LOGGER.warn("代理推送器推送消息失败，异常信息如下: ", e);
            }
        }
    }

    @Override
    public void taskExpired(Section section) {
        for (Pusher delegate : delegates) {
            try {
                delegate.taskExpired(section);
            } catch (Exception e) {
                LOGGER.warn("代理推送器推送消息失败，异常信息如下: ", e);
            }
        }
    }

    @Override
    public void taskDied(Section section) {
        for (Pusher delegate : delegates) {
            try {
                delegate.taskDied(section);
            } catch (Exception e) {
                LOGGER.warn("代理推送器推送消息失败，异常信息如下: ", e);
            }
        }
    }

    @Override
    public void judgementModalUpdated(JudgementModal judgementModal) {
        for (Pusher delegate : delegates) {
            try {
                delegate.judgementModalUpdated(judgementModal);
            } catch (Exception e) {
                LOGGER.warn("代理推送器推送消息失败，异常信息如下: ", e);
            }
        }
    }

    @Override
    public void alarmModalUpdated(AlarmModal alarmModal) {
        for (Pusher delegate : delegates) {
            try {
                delegate.alarmModalUpdated(alarmModal);
            } catch (Exception e) {
                LOGGER.warn("代理推送器推送消息失败，异常信息如下: ", e);
            }
        }
    }

    @Override
    public String toString() {
        return "MultiPusher{" +
                "delegateTypes='" + delegateTypes + '\'' +
                ", pusherType='" + pusherType + '\'' +
                '}';
    }
}

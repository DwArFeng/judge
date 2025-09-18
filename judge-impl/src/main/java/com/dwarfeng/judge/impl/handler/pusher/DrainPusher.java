package com.dwarfeng.judge.impl.handler.pusher;

import com.dwarfeng.judge.sdk.handler.pusher.AbstractPusher;
import com.dwarfeng.judge.stack.bean.entity.Section;
import org.springframework.stereotype.Component;

/**
 * 简单的丢弃掉所有信息的推送器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
@Component
public class DrainPusher extends AbstractPusher {

    public static final String PUSHER_TYPE = "drain";

    public DrainPusher() {
        super(PUSHER_TYPE);
    }

    @Override
    public void taskFinished(Section section) {
    }

    @Override
    public void taskFailed(Section section) {
    }

    @Override
    public void taskExpired(Section section) {
    }

    @Override
    public void taskDied(Section section) {
    }

    @Override
    public void jobReset() {
    }

    @Override
    public void superviseReset() {
    }

    @Override
    public void sinkReset() {
    }

    @Override
    public void provideReset() {
    }

    @Override
    public String toString() {
        return "DrainPusher{" +
                "pusherType='" + pusherType + '\'' +
                '}';
    }
}

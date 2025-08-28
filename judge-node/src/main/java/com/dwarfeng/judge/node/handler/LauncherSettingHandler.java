package com.dwarfeng.judge.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.start_reset_delay}")
    private long startResetDelay;

    public long getStartResetDelay() {
        return startResetDelay;
    }

    @Override
    public String toString() {
        return "LauncherSettingHandler{" +
                "startResetDelay=" + startResetDelay +
                '}';
    }
}

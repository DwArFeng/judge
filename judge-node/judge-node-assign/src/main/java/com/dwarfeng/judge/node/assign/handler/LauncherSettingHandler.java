package com.dwarfeng.judge.node.assign.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.start_assign_delay}")
    private long startAssignDelay;

    public long getStartAssignDelay() {
        return startAssignDelay;
    }
}

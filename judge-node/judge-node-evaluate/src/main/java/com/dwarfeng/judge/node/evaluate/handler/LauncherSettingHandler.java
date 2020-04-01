package com.dwarfeng.judge.node.evaluate.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.start_evaluate_delay}")
    private long startEvaluateDelay;

    public long getStartEvaluateDelay() {
        return startEvaluateDelay;
    }
}

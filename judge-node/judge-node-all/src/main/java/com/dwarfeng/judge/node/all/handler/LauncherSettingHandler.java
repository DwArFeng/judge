package com.dwarfeng.judge.node.all.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    //    @Value("${launcher.reset_filter_support}")
    private boolean resetFilterSupport;
    //    @Value("${launcher.reset_trigger_support}")
    private boolean resetTriggerSupport;
    @Value("${launcher.start_judge_delay}")
    private long startJudgeDelay;

    public boolean isResetFilterSupport() {
        return resetFilterSupport;
    }

    public boolean isResetTriggerSupport() {
        return resetTriggerSupport;
    }

    public long getStartJudgeDelay() {
        return startJudgeDelay;
    }
}

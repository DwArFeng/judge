package com.dwarfeng.judge.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.reset_driver_support}")
    private boolean resetDriverSupport;
    @Value("${launcher.reset_judger_support}")
    private boolean resetJudgerSupport;
    @Value("${launcher.start_judge_delay}")
    private long startJudgeDelay;

    public boolean isResetDriverSupport() {
        return resetDriverSupport;
    }

    public boolean isResetJudgerSupport() {
        return resetJudgerSupport;
    }

    public long getStartJudgeDelay() {
        return startJudgeDelay;
    }
}

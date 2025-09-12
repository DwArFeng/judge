package com.dwarfeng.judge.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.reset_analyser_support}")
    private boolean resetAnalyserSupport;

    @Value("${launcher.reset_driver_support}")
    private boolean resetDriverSupport;

    @Value("${launcher.reset_judger_support}")
    private boolean resetJudgerSupport;

    @Value("${launcher.start_reset_delay}")
    private long startResetDelay;

    @Value("${launcher.online_task_check_delay}")
    private long onlineTaskCheckDelay;
    @Value("${launcher.enable_task_check_delay}")
    private long enableTaskCheckDelay;

    public boolean isResetAnalyserSupport() {
        return resetAnalyserSupport;
    }

    public boolean isResetDriverSupport() {
        return resetDriverSupport;
    }

    public boolean isResetJudgerSupport() {
        return resetJudgerSupport;
    }

    public long getStartResetDelay() {
        return startResetDelay;
    }

    public long getOnlineTaskCheckDelay() {
        return onlineTaskCheckDelay;
    }

    public long getEnableTaskCheckDelay() {
        return enableTaskCheckDelay;
    }

    @Override
    public String toString() {
        return "LauncherSettingHandler{" +
                "resetAnalyserSupport=" + resetAnalyserSupport +
                ", resetDriverSupport=" + resetDriverSupport +
                ", resetJudgerSupport=" + resetJudgerSupport +
                ", startResetDelay=" + startResetDelay +
                ", onlineTaskCheckDelay=" + onlineTaskCheckDelay +
                ", enableTaskCheckDelay=" + enableTaskCheckDelay +
                '}';
    }
}

package com.dwarfeng.judge.node.all.he.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${com.dwarfeng.judge.launcher.reset_analyser_support}")
    private boolean resetAnalyserSupport;

    @Value("${com.dwarfeng.judge.launcher.reset_driver_support}")
    private boolean resetDriverSupport;

    @Value("${com.dwarfeng.judge.launcher.reset_judger_support}")
    private boolean resetJudgerSupport;

    @Value("${com.dwarfeng.judge.launcher.reset_sinker_support}")
    private boolean resetSinkerSupport;

    @Value("${com.dwarfeng.judge.launcher.reset_provider_support}")
    private boolean resetProviderSupport;

    @Value("${com.dwarfeng.judge.launcher.reset_visualizer_support}")
    private boolean resetVisualizerSupport;

    @Value("${com.dwarfeng.judge.launcher.reset_adapter_support}")
    private boolean resetAdapterSupport;

    @Value("${com.dwarfeng.judge.launcher.reset_filter_support}")
    private boolean resetFilterSupport;

    @Value("${com.dwarfeng.judge.launcher.start_reset_delay}")
    private long startResetDelay;

    @Value("${com.dwarfeng.judge.launcher.online_task_check_delay}")
    private long onlineTaskCheckDelay;
    @Value("${com.dwarfeng.judge.launcher.enable_task_check_delay}")
    private long enableTaskCheckDelay;

    @Value("${com.dwarfeng.judge.launcher.start_receive_delay}")
    private long startReceiveDelay;

    @Value("${com.dwarfeng.judge.launcher.online_supervise_delay}")
    private long onlineSuperviseDelay;
    @Value("${com.dwarfeng.judge.launcher.enable_supervise_delay}")
    private long enableSuperviseDelay;

    @Value("${com.dwarfeng.judge.launcher.online_purge_delay}")
    private long onlinePurgeDelay;
    @Value("${com.dwarfeng.judge.launcher.enable_purge_delay}")
    private long enablePurgeDelay;

    public boolean isResetAnalyserSupport() {
        return resetAnalyserSupport;
    }

    public boolean isResetDriverSupport() {
        return resetDriverSupport;
    }

    public boolean isResetJudgerSupport() {
        return resetJudgerSupport;
    }

    public boolean isResetSinkerSupport() {
        return resetSinkerSupport;
    }

    public boolean isResetProviderSupport() {
        return resetProviderSupport;
    }

    public boolean isResetVisualizerSupport() {
        return resetVisualizerSupport;
    }

    public boolean isResetAdapterSupport() {
        return resetAdapterSupport;
    }

    public boolean isResetFilterSupport() {
        return resetFilterSupport;
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

    public long getStartReceiveDelay() {
        return startReceiveDelay;
    }

    public long getOnlineSuperviseDelay() {
        return onlineSuperviseDelay;
    }

    public long getEnableSuperviseDelay() {
        return enableSuperviseDelay;
    }

    public long getOnlinePurgeDelay() {
        return onlinePurgeDelay;
    }

    public long getEnablePurgeDelay() {
        return enablePurgeDelay;
    }

    @Override
    public String toString() {
        return "LauncherSettingHandler{" +
                "resetAnalyserSupport=" + resetAnalyserSupport +
                ", resetDriverSupport=" + resetDriverSupport +
                ", resetJudgerSupport=" + resetJudgerSupport +
                ", resetSinkerSupport=" + resetSinkerSupport +
                ", resetProviderSupport=" + resetProviderSupport +
                ", resetVisualizerSupport=" + resetVisualizerSupport +
                ", resetAdapterSupport=" + resetAdapterSupport +
                ", resetFilterSupport=" + resetFilterSupport +
                ", startResetDelay=" + startResetDelay +
                ", onlineTaskCheckDelay=" + onlineTaskCheckDelay +
                ", enableTaskCheckDelay=" + enableTaskCheckDelay +
                ", startReceiveDelay=" + startReceiveDelay +
                ", onlineSuperviseDelay=" + onlineSuperviseDelay +
                ", enableSuperviseDelay=" + enableSuperviseDelay +
                ", onlinePurgeDelay=" + onlinePurgeDelay +
                ", enablePurgeDelay=" + enablePurgeDelay +
                '}';
    }
}

package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.DriverSupport;
import com.dwarfeng.judge.stack.bean.entity.JudgerSupport;
import com.dwarfeng.judge.stack.handler.SupportHandler;
import com.dwarfeng.judge.stack.service.DriverSupportMaintainService;
import com.dwarfeng.judge.stack.service.JudgerSupportMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupportHandlerImpl implements SupportHandler {

    private final DriverSupportMaintainService driverSupportMaintainService;
    private final JudgerSupportMaintainService judgerSupportMaintainService;

    private final List<DriverSupporter> driverSupporters;
    private final List<JudgerSupporter> judgerSupporters;

    public SupportHandlerImpl(
            DriverSupportMaintainService driverSupportMaintainService,
            JudgerSupportMaintainService judgerSupportMaintainService,
            List<DriverSupporter> driverSupporters,
            List<JudgerSupporter> judgerSupporters
    ) {
        this.driverSupportMaintainService = driverSupportMaintainService;
        this.judgerSupportMaintainService = judgerSupportMaintainService;
        this.driverSupporters = driverSupporters;
        this.judgerSupporters = judgerSupporters;
    }

    @Override
    @BehaviorAnalyse
    public void resetDriver() throws HandlerException {
        try {
            doResetDriver();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doResetDriver() throws Exception {
        List<StringIdKey> driverKeys = driverSupportMaintainService.lookupAsList().stream()
                .map(DriverSupport::getKey).collect(Collectors.toList());
        driverSupportMaintainService.batchDelete(driverKeys);
        List<DriverSupport> driverSupports = driverSupporters.stream().map(supporter -> new DriverSupport(
                new StringIdKey(supporter.provideType()),
                supporter.provideLabel(),
                supporter.provideDescription(),
                supporter.provideExampleContent()
        )).collect(Collectors.toList());
        driverSupportMaintainService.batchInsert(driverSupports);
    }

    @Override
    @BehaviorAnalyse
    public void resetJudger() throws HandlerException {
        try {
            doResetJudger();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doResetJudger() throws Exception {
        List<StringIdKey> judgerKeys = judgerSupportMaintainService.lookupAsList().stream()
                .map(JudgerSupport::getKey).collect(Collectors.toList());
        judgerSupportMaintainService.batchDelete(judgerKeys);
        List<JudgerSupport> judgerSupports = judgerSupporters.stream().map(supporter -> new JudgerSupport(
                new StringIdKey(supporter.provideType()),
                supporter.provideLabel(),
                supporter.provideDescription(),
                supporter.provideExampleContent()
        )).collect(Collectors.toList());
        judgerSupportMaintainService.batchInsert(judgerSupports);
    }
}

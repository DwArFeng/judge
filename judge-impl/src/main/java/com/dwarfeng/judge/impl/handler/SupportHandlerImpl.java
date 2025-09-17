package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.handler.AnalyserSupporter;
import com.dwarfeng.judge.sdk.handler.DriverSupporter;
import com.dwarfeng.judge.sdk.handler.JudgerSupporter;
import com.dwarfeng.judge.sdk.handler.SinkerSupporter;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaIndicatorKey;
import com.dwarfeng.judge.stack.handler.SupportHandler;
import com.dwarfeng.judge.stack.service.*;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SupportHandlerImpl implements SupportHandler {

    private final AnalyserSupportMaintainService analyserSupportMaintainService;
    private final DriverSupportMaintainService driverSupportMaintainService;
    private final JudgerSupportMaintainService judgerSupportMaintainService;
    private final SinkerSupportMaintainService sinkerSupportMaintainService;
    private final SinkerMetaIndicatorMaintainService sinkerMetaIndicatorMaintainService;

    private final List<AnalyserSupporter> analyserSupporters;
    private final List<DriverSupporter> driverSupporters;
    private final List<JudgerSupporter> judgerSupporters;
    private final List<SinkerSupporter> sinkerSupporters;

    public SupportHandlerImpl(
            AnalyserSupportMaintainService analyserSupportMaintainService,
            DriverSupportMaintainService driverSupportMaintainService,
            JudgerSupportMaintainService judgerSupportMaintainService,
            SinkerSupportMaintainService sinkerSupportMaintainService,
            SinkerMetaIndicatorMaintainService sinkerMetaIndicatorMaintainService,
            List<AnalyserSupporter> analyserSupporters,
            List<DriverSupporter> driverSupporters,
            List<JudgerSupporter> judgerSupporters,
            List<SinkerSupporter> sinkerSupporters
    ) {
        this.analyserSupportMaintainService = analyserSupportMaintainService;
        this.driverSupportMaintainService = driverSupportMaintainService;
        this.judgerSupportMaintainService = judgerSupportMaintainService;
        this.sinkerSupportMaintainService = sinkerSupportMaintainService;
        this.sinkerMetaIndicatorMaintainService = sinkerMetaIndicatorMaintainService;
        this.analyserSupporters = Optional.ofNullable(analyserSupporters).orElse(Collections.emptyList());
        this.driverSupporters = Optional.ofNullable(driverSupporters).orElse(Collections.emptyList());
        this.judgerSupporters = Optional.ofNullable(judgerSupporters).orElse(Collections.emptyList());
        this.sinkerSupporters = Optional.ofNullable(sinkerSupporters).orElse(Collections.emptyList());
    }

    @Override
    @BehaviorAnalyse
    public void resetAnalyser() throws HandlerException {
        try {
            doResetAnalyser();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doResetAnalyser() throws Exception {
        List<StringIdKey> analyserKeys = analyserSupportMaintainService.lookupAsList().stream()
                .map(AnalyserSupport::getKey).collect(Collectors.toList());
        analyserSupportMaintainService.batchDelete(analyserKeys);
        List<AnalyserSupport> analyserSupports = analyserSupporters.stream().map(
                supporter -> new AnalyserSupport(
                        new StringIdKey(supporter.provideType()),
                        supporter.provideLabel(),
                        supporter.provideDescription(),
                        supporter.provideExampleParam()
                )
        ).collect(Collectors.toList());
        analyserSupportMaintainService.batchInsert(analyserSupports);
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
        List<DriverSupport> driverSupports = driverSupporters.stream().map(
                supporter -> new DriverSupport(
                        new StringIdKey(supporter.provideType()),
                        supporter.provideLabel(),
                        supporter.provideDescription(),
                        supporter.provideExampleParam()
                )
        ).collect(Collectors.toList());
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
        List<JudgerSupport> judgerSupports = judgerSupporters.stream().map(
                supporter -> new JudgerSupport(
                        new StringIdKey(supporter.provideType()),
                        supporter.provideLabel(),
                        supporter.provideDescription(),
                        supporter.provideExampleParam()
                )
        ).collect(Collectors.toList());
        judgerSupportMaintainService.batchInsert(judgerSupports);
    }

    @Override
    @BehaviorAnalyse
    public void resetSinker() throws HandlerException {
        try {
            doResetSinker();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doResetSinker() throws Exception {
        List<SinkerMetaIndicatorKey> sinkerMetaIndicatorKeys = sinkerMetaIndicatorMaintainService.lookupAsList()
                .stream().map(SinkerMetaIndicator::getKey).collect(Collectors.toList());
        sinkerMetaIndicatorMaintainService.batchDelete(sinkerMetaIndicatorKeys);
        List<StringIdKey> sinkerKeys = sinkerSupportMaintainService.lookupAsList().stream()
                .map(SinkerSupport::getKey).collect(Collectors.toList());
        sinkerSupportMaintainService.batchDelete(sinkerKeys);
        List<SinkerSupport> sinkerSupports = sinkerSupporters.stream().map(supporter -> new SinkerSupport(
                new StringIdKey(supporter.provideType()),
                supporter.provideExampleParam(),
                supporter.provideLabel(),
                supporter.provideDescription()
        )).collect(Collectors.toList());
        sinkerSupportMaintainService.batchInsert(sinkerSupports);
        List<SinkerMetaIndicator> sinkerMetaIndicators = sinkerSupporters.stream()
                .flatMap(this::parseSinkerMetaIndicatorStream)
                .collect(Collectors.toList());
        sinkerMetaIndicatorMaintainService.batchInsert(sinkerMetaIndicators);
    }

    private Stream<SinkerMetaIndicator> parseSinkerMetaIndicatorStream(SinkerSupporter sinkerSupporter) {
        return sinkerSupporter.provideIndicatorMap().entrySet().stream().map(
                entry -> new SinkerMetaIndicator(
                        new SinkerMetaIndicatorKey(sinkerSupporter.provideType(), entry.getKey()),
                        entry.getValue().getLabel(),
                        entry.getValue().getInitialValue(),
                        entry.getValue().getDescription()
                )
        );
    }
}

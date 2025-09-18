package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.util.Constants;
import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.handler.*;
import com.dwarfeng.judge.stack.service.*;
import com.dwarfeng.judge.stack.struct.JobLocalCache;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Future;

@Component
public class JobHandlerImpl implements JobHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobHandlerImpl.class);

    private static final Set<Integer> VALID_TASK_STATUS_SET_EXECUTE;

    static {
        Set<Integer> VALID_TASK_STATUS_SET_EXECUTE_DEJA_VU = new HashSet<>();
        VALID_TASK_STATUS_SET_EXECUTE_DEJA_VU.add(Constants.TASK_STATUS_CREATED);
        VALID_TASK_STATUS_SET_EXECUTE = Collections.unmodifiableSet(
                VALID_TASK_STATUS_SET_EXECUTE_DEJA_VU
        );
    }

    private final ApplicationContext ctx;

    private final TaskMaintainService taskMaintainService;
    private final SectionMaintainService sectionMaintainService;
    private final TaskEventMaintainService taskEventMaintainService;
    private final AnalysisMaintainService analysisMaintainService;
    private final JudgementMaintainService judgementMaintainService;
    private final AnalysisPictureInfoMaintainService analysisPictureInfoMaintainService;
    private final AnalysisPicturePackMaintainService analysisPicturePackMaintainService;
    private final AnalysisPicturePackItemInfoMaintainService analysisPicturePackItemInfoMaintainService;
    private final AnalysisFileInfoMaintainService analysisFileInfoMaintainService;
    private final AnalysisFilePackMaintainService analysisFilePackMaintainService;
    private final AnalysisFilePackItemInfoMaintainService analysisFilePackItemInfoMaintainService;

    private final TaskOperateHandler taskOperateHandler;
    private final TaskEventOperateHandler taskEventOperateHandler;
    private final JobLocalCacheHandler jobLocalCacheHandler;
    private final AnalyserVariableOperateHandler analyserVariableOperateHandler;
    private final JudgerVariableOperateHandler judgerVariableOperateHandler;
    private final AnalysisOperateHandler analysisOperateHandler;
    private final AnalysisPictureFileOperateHandler analysisPictureFileOperateHandler;
    private final AnalysisPicturePackItemFileOperateHandler analysisPicturePackItemFileOperateHandler;
    private final AnalysisFileFileOperateHandler analysisFileFileOperateHandler;
    private final AnalysisFilePackItemFileOperateHandler analysisFilePackItemFileOperateHandler;
    private final JudgementOperateHandler judgementOperateHandler;
    private final SinkHandler sinkHandler;
    private final ProvideHandler provideHandler;

    private final ThreadPoolTaskExecutor executor;
    private final ThreadPoolTaskScheduler scheduler;

    private final HandlerValidator handlerValidator;

    @Value("${task.beat_interval}")
    private long beatInterval;

    public JobHandlerImpl(
            ApplicationContext ctx,
            TaskMaintainService taskMaintainService,
            SectionMaintainService sectionMaintainService,
            TaskEventMaintainService taskEventMaintainService,
            AnalysisMaintainService analysisMaintainService,
            JudgementMaintainService judgementMaintainService,
            AnalysisPictureInfoMaintainService analysisPictureInfoMaintainService,
            AnalysisPicturePackMaintainService analysisPicturePackMaintainService,
            AnalysisPicturePackItemInfoMaintainService analysisPicturePackItemInfoMaintainService,
            AnalysisFileInfoMaintainService analysisFileInfoMaintainService,
            AnalysisFilePackMaintainService analysisFilePackMaintainService,
            AnalysisFilePackItemInfoMaintainService analysisFilePackItemInfoMaintainService,
            TaskOperateHandler taskOperateHandler,
            TaskEventOperateHandler taskEventOperateHandler,
            JobLocalCacheHandler jobLocalCacheHandler,
            AnalyserVariableOperateHandler analyserVariableOperateHandler,
            JudgerVariableOperateHandler judgerVariableOperateHandler,
            AnalysisOperateHandler analysisOperateHandler,
            AnalysisPictureFileOperateHandler analysisPictureFileOperateHandler,
            AnalysisPicturePackItemFileOperateHandler analysisPicturePackItemFileOperateHandler,
            AnalysisFileFileOperateHandler analysisFileFileOperateHandler,
            AnalysisFilePackItemFileOperateHandler analysisFilePackItemFileOperateHandler,
            JudgementOperateHandler judgementOperateHandler,
            SinkHandler sinkHandler,
            ProvideHandler provideHandler,
            ThreadPoolTaskExecutor executor,
            ThreadPoolTaskScheduler scheduler,
            HandlerValidator handlerValidator
    ) {
        this.ctx = ctx;
        this.taskMaintainService = taskMaintainService;
        this.sectionMaintainService = sectionMaintainService;
        this.taskEventMaintainService = taskEventMaintainService;
        this.analysisMaintainService = analysisMaintainService;
        this.judgementMaintainService = judgementMaintainService;
        this.analysisPictureInfoMaintainService = analysisPictureInfoMaintainService;
        this.analysisPicturePackMaintainService = analysisPicturePackMaintainService;
        this.analysisPicturePackItemInfoMaintainService = analysisPicturePackItemInfoMaintainService;
        this.analysisFileInfoMaintainService = analysisFileInfoMaintainService;
        this.analysisFilePackMaintainService = analysisFilePackMaintainService;
        this.analysisFilePackItemInfoMaintainService = analysisFilePackItemInfoMaintainService;
        this.taskOperateHandler = taskOperateHandler;
        this.taskEventOperateHandler = taskEventOperateHandler;
        this.jobLocalCacheHandler = jobLocalCacheHandler;
        this.analyserVariableOperateHandler = analyserVariableOperateHandler;
        this.judgerVariableOperateHandler = judgerVariableOperateHandler;
        this.analysisOperateHandler = analysisOperateHandler;
        this.analysisPictureFileOperateHandler = analysisPictureFileOperateHandler;
        this.analysisPicturePackItemFileOperateHandler = analysisPicturePackItemFileOperateHandler;
        this.analysisFileFileOperateHandler = analysisFileFileOperateHandler;
        this.analysisFilePackItemFileOperateHandler = analysisFilePackItemFileOperateHandler;
        this.judgementOperateHandler = judgementOperateHandler;
        this.sinkHandler = sinkHandler;
        this.provideHandler = provideHandler;
        this.executor = executor;
        this.scheduler = scheduler;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public JobCreateResult create(JobCreateInfo info) throws HandlerException {
        try {
            return create0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private JobCreateResult create0(JobCreateInfo info) throws Exception {
        TaskCreateInfo taskCreateInfo = new TaskCreateInfo(info.getSectionKey());
        TaskCreateResult taskCreateResult = taskOperateHandler.create(taskCreateInfo);
        return new JobCreateResult(taskCreateResult.getTaskKey());
    }

    @Override
    public void execute(JobExecuteInfo info) throws HandlerException {
        try {
            execute0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public CompletableFuture<Void> executeAsync(JobExecuteInfo info) throws HandlerException {
        try {
            return executeAsync0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    public CompletableFuture<Void> executeAsync0(JobExecuteInfo info) {
        return CompletableFuture.supplyAsync(
                () -> {
                    wrappedDoExecute(info);
                    return null;
                },
                executor
        );
    }

    private void wrappedDoExecute(JobExecuteInfo info) throws CompletionException {
        try {
            execute0(info);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    private void execute0(JobExecuteInfo info) throws Exception {
        // 展开参数。
        LongIdKey taskKey = info.getTaskKey();

        // 确认任务存在。
        handlerValidator.makeSureTaskExists(taskKey);
        // 确认任务状态合法。
        handlerValidator.makeSureTaskStatusValid(taskKey, VALID_TASK_STATUS_SET_EXECUTE);

        // 调用处理器启动任务。
        taskOperateHandler.start(new TaskStartInfo(taskKey));

        // 启动心跳任务。
        Future<?> beatTaskFuture = startBeatTask(taskKey);

        // 获取部件主键。
        LongIdKey sectionKey;
        try {
            Task task = taskMaintainService.get(taskKey);
            sectionKey = task.getSectionKey();
        } catch (Exception e) {
            // 取消心跳任务。
            beatTaskFuture.cancel(true);
            // 记录日志。
            LOGGER.warn("作业执行失败, 任务主键: {}, 异常信息如下: ", taskKey, e);
            // 插入任务事件。
            taskEventOperateHandler.create(new TaskEventCreateInfo(taskKey, "作业执行失败, 请查看系统日志以了解详细原因"));
            // 将任务状态置为失败。
            taskOperateHandler.fail(new TaskFailInfo(taskKey));
            // 结束方法。
            return;
        }

        // 根据部件主键获取作业本地缓存，并展开参数。
        List<LongIdKey> analyserInfoKeys;
        Map<LongIdKey, Analyser> analyserMap;
        List<LongIdKey> judgerInfoKeys;
        Map<LongIdKey, Judger> judgerMap;
        try {
            JobLocalCache jobLocalCache = jobLocalCacheHandler.get(sectionKey);
            analyserInfoKeys = jobLocalCache.getAnalyserInfoKeys();
            analyserMap = jobLocalCache.getAnalyserMap();
            judgerInfoKeys = jobLocalCache.getJudgerInfoKeys();
            judgerMap = jobLocalCache.getJudgerMap();
        } catch (Exception e) {
            // 取消心跳任务。
            beatTaskFuture.cancel(true);
            // 记录日志。
            LOGGER.warn("作业执行失败, 任务主键: {}, 部件主键: {}, 异常信息如下: ", taskKey, sectionKey, e);
            // 插入任务事件。
            taskEventOperateHandler.create(new TaskEventCreateInfo(taskKey, "作业执行失败, 请查看系统日志以了解详细原因"));
            // 将任务状态置为失败。
            taskOperateHandler.fail(new TaskFailInfo(taskKey));
            // 结束方法。
            return;
        }

        // 进行分析。
        try {
            analyse(sectionKey, analyserInfoKeys, taskKey, analyserMap);
        } catch (Exception e) {
            // 取消心跳任务。
            beatTaskFuture.cancel(true);
            // 记录日志。
            LOGGER.warn("作业执行失败, 任务主键: {}, 部件主键: {}, 异常信息如下: ", taskKey, sectionKey, e);
            // 插入任务事件。
            taskEventOperateHandler.create(new TaskEventCreateInfo(taskKey, "作业执行失败, 请查看系统日志以了解详细原因"));
            // 将任务状态置为失败。
            taskOperateHandler.fail(new TaskFailInfo(taskKey));
            // 结束方法。
            return;
        }

        // 进行判断。
        try {
            judge(sectionKey, judgerInfoKeys, taskKey, judgerMap);
        } catch (Exception e) {
            // 取消心跳任务。
            beatTaskFuture.cancel(true);
            // 记录日志。
            LOGGER.warn("作业执行失败, 任务主键: {}, 部件主键: {}, 异常信息如下: ", taskKey, sectionKey, e);
            // 插入任务事件。
            taskEventOperateHandler.create(new TaskEventCreateInfo(taskKey, "作业执行失败, 请查看系统日志以了解详细原因"));
            // 将任务状态置为失败。
            taskOperateHandler.fail(new TaskFailInfo(taskKey));
            // 结束方法。
            return;
        }

        // 进行下沉。
        try {
            sink(sectionKey, taskKey);
        } catch (Exception e) {
            // 取消心跳任务。
            beatTaskFuture.cancel(true);
            // 记录日志。
            LOGGER.warn("作业执行失败, 任务主键: {}, 部件主键: {}, 异常信息如下: ", taskKey, sectionKey, e);
            // 插入任务事件。
            taskEventOperateHandler.create(new TaskEventCreateInfo(taskKey, "作业执行失败, 请查看系统日志以了解详细原因"));
            // 将任务状态置为失败。
            taskOperateHandler.fail(new TaskFailInfo(taskKey));
            // 结束方法。
            return;
        }

        // 将任务状态置为完成。
        taskOperateHandler.finish(new TaskFinishInfo(taskKey));
    }

    private Future<?> startBeatTask(LongIdKey taskKey) {
        BeatSchedulerTask beatSchedulerTask = ctx.getBean(BeatSchedulerTask.class, taskOperateHandler, taskKey);
        return scheduler.scheduleAtFixedRate(
                beatSchedulerTask,
                new Date(System.currentTimeMillis() + beatInterval),
                beatInterval
        );
    }

    private void analyse(
            LongIdKey sectionKey, List<LongIdKey> analyserInfoKeys, LongIdKey taskKey,
            Map<LongIdKey, Analyser> analyserMap
    ) throws Exception {
        // 遍历 analyserInfoKeys，按照先后顺序执行分析。
        for (LongIdKey analyserInfoKey : analyserInfoKeys) {
            // 构造分析器上下文。
            Analyser.Context analyserContext = ctx.getBean(
                    InternalAnalyserContext.class,
                    sectionKey, analyserInfoKey, taskKey,
                    taskOperateHandler, taskEventOperateHandler,
                    analyserVariableOperateHandler,
                    analysisOperateHandler, analysisPictureFileOperateHandler,
                    analysisPicturePackItemFileOperateHandler, analysisFileFileOperateHandler,
                    analysisFilePackItemFileOperateHandler, provideHandler
            );

            // 获取分析器。
            Analyser analyser = analyserMap.get(analyserInfoKey);

            // 创建分析器执行器，并初始化。
            Analyser.Executor analyserExecutor = analyser.newExecutor();
            analyserExecutor.init(analyserContext);

            // 调用分析器执行器的分析方法。
            analyserExecutor.analyse();
        }
    }

    private void judge(
            LongIdKey sectionKey, List<LongIdKey> judgerInfoKeys, LongIdKey taskKey,
            Map<LongIdKey, Judger> judgerMap
    ) throws Exception {
        // 遍历 judgerInfoKeys，按照先后顺序执行判断。
        for (LongIdKey judgerInfoKey : judgerInfoKeys) {
            // 构造判断器上下文。
            Judger.Context judgerContext = ctx.getBean(
                    InternalJudgerContext.class,
                    sectionKey, judgerInfoKey, taskKey,
                    taskOperateHandler, taskEventOperateHandler,
                    judgerVariableOperateHandler, analysisOperateHandler,
                    analysisPictureFileOperateHandler, analysisPicturePackItemFileOperateHandler,
                    analysisFileFileOperateHandler, analysisFilePackItemFileOperateHandler, judgementOperateHandler
            );

            // 获取判断器。
            Judger judger = judgerMap.get(judgerInfoKey);

            // 创建判断器执行器，并初始化。
            Judger.Executor judgerExecutor = judger.newExecutor();
            judgerExecutor.init(judgerContext);

            // 调用判断器执行器的判断方法。
            judgerExecutor.judge();
        }
    }

    private void sink(LongIdKey sectionKey, LongIdKey taskKey) throws Exception {
        Section section = sectionMaintainService.get(sectionKey);
        Task task = taskMaintainService.get(taskKey);
        List<SinkInfo.TaskEvent> taskEvents = lookupTaskEvents(taskKey);
        List<SinkInfo.Analysis> analyses = lookupAnalyses(taskKey);
        List<SinkInfo.Judgement> judgements = lookupJudgements(taskKey);
        SinkInfo sinkInfo = new SinkInfo(
                sectionKey, taskKey,
                section.getName(), section.getRemark(),
                task.getStatus(), task.getCreatedDate(), task.getStartedDate(), task.getEndedDate(), task.getDuration(),
                task.getShouldExpireDate(), task.getShouldDieDate(), task.getExpiredDate(), task.getDiedDate(),
                task.getAnchorMessage(),
                taskEvents, analyses, judgements
        );
        sinkHandler.sink(sinkInfo);
    }

    private List<SinkInfo.TaskEvent> lookupTaskEvents(LongIdKey taskKey) throws Exception {
        List<TaskEvent> taskEvents = taskEventMaintainService.lookupAsList(
                TaskEventMaintainService.CHILD_FOR_TASK, new Object[]{taskKey}
        );
        List<SinkInfo.TaskEvent> sinkInfoTaskEvent = new ArrayList<>();
        for (TaskEvent taskEvent : taskEvents) {
            sinkInfoTaskEvent.add(new SinkInfo.TaskEvent(taskEvent.getHappenedDate(), taskEvent.getMessage()));
        }
        return sinkInfoTaskEvent;
    }

    private List<SinkInfo.Analysis> lookupAnalyses(LongIdKey taskKey) throws Exception {
        List<Analysis> analyses = analysisMaintainService.lookupAsList(
                AnalysisMaintainService.CHILD_FOR_TASK, new Object[]{taskKey}
        );
        List<SinkInfo.Analysis> sinkInfoAnalyses = new ArrayList<>();
        for (Analysis analysis : analyses) {
            String dataId = analysis.getKey().getDataStringId();
            int dataType = analysis.getDataType();
            Object value;
            switch (dataType) {
                case Constants.ANALYSIS_TYPE_STRING:
                    value = analysis.getStringValue();
                    break;
                case Constants.ANALYSIS_TYPE_LONG:
                    value = analysis.getLongValue();
                    break;
                case Constants.ANALYSIS_TYPE_DOUBLE:
                    value = analysis.getDoubleValue();
                    break;
                case Constants.ANALYSIS_TYPE_BOOLEAN:
                    value = analysis.getBooleanValue();
                    break;
                case Constants.ANALYSIS_TYPE_DATE:
                    value = analysis.getDateValue();
                    break;
                case Constants.ANALYSIS_TYPE_PICTURE:
                    value = lookupAnalysisPicture(analysis.getPictureValue());
                    break;
                case Constants.ANALYSIS_TYPE_PICTURE_PACK:
                    value = lookupAnalysisPicturePack(analysis.getPicturePackValue());
                    break;
                case Constants.ANALYSIS_TYPE_FILE:
                    value = lookupAnalysisFile(analysis.getFileValue());
                    break;
                case Constants.ANALYSIS_TYPE_FILE_PACK:
                    value = lookupAnalysisFilePack(analysis.getFilePackValue());
                    break;
                default:
                    throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
            }
            sinkInfoAnalyses.add(new SinkInfo.Analysis(dataId, dataType, value));
        }
        return sinkInfoAnalyses;
    }

    private SinkInfo.AnalysisPicture lookupAnalysisPicture(Long pictureValue) throws Exception {
        LongIdKey analysisPictureInfoKey = new LongIdKey(pictureValue);
        AnalysisPictureInfo analysisPictureInfo = analysisPictureInfoMaintainService.get(analysisPictureInfoKey);
        return new SinkInfo.AnalysisPicture(
                analysisPictureInfoKey,
                analysisPictureInfo.getOriginName(), analysisPictureInfo.getLength(), analysisPictureInfo.getRemark()
        );
    }

    private SinkInfo.AnalysisPicturePack lookupAnalysisPicturePack(Long picturePackValue) throws Exception {
        LongIdKey analysisPicturePackKey = new LongIdKey(picturePackValue);
        AnalysisPicturePack analysisPicturePack = analysisPicturePackMaintainService.get(analysisPicturePackKey);
        List<AnalysisPicturePackItemInfo> analysisPicturePackItemInfos =
                analysisPicturePackItemInfoMaintainService.lookupAsList(
                        AnalysisPicturePackItemInfoMaintainService.CHILD_FOR_PACK, new Object[]{analysisPicturePackKey}
                );
        List<SinkInfo.AnalysisPicturePackItem> sinkInfoAnalysisPicturePackItems = new ArrayList<>();
        for (AnalysisPicturePackItemInfo analysisPicturePackItemInfo : analysisPicturePackItemInfos) {
            sinkInfoAnalysisPicturePackItems.add(new SinkInfo.AnalysisPicturePackItem(
                    analysisPicturePackItemInfo.getKey(), analysisPicturePackItemInfo.getIndex(),
                    analysisPicturePackItemInfo.getOriginName(), analysisPicturePackItemInfo.getLength(),
                    analysisPicturePackItemInfo.getRemark()
            ));
        }
        return new SinkInfo.AnalysisPicturePack(
                analysisPicturePackKey, analysisPicturePack.getItemAnchorIndex(), analysisPicturePack.getRemark(),
                sinkInfoAnalysisPicturePackItems
        );
    }

    private SinkInfo.AnalysisFile lookupAnalysisFile(Long fileValue) throws Exception {
        LongIdKey analysisFileInfoKey = new LongIdKey(fileValue);
        AnalysisFileInfo analysisFileInfo = analysisFileInfoMaintainService.get(analysisFileInfoKey);
        return new SinkInfo.AnalysisFile(
                analysisFileInfoKey,
                analysisFileInfo.getOriginName(), analysisFileInfo.getLength(), analysisFileInfo.getRemark()
        );
    }

    private SinkInfo.AnalysisFilePack lookupAnalysisFilePack(Long filePackValue) throws Exception {
        LongIdKey analysisFilePackKey = new LongIdKey(filePackValue);
        AnalysisFilePack analysisFilePack = analysisFilePackMaintainService.get(analysisFilePackKey);
        List<AnalysisFilePackItemInfo> analysisFilePackItemInfos =
                analysisFilePackItemInfoMaintainService.lookupAsList(
                        AnalysisFilePackItemInfoMaintainService.CHILD_FOR_PACK, new Object[]{analysisFilePackKey}
                );
        List<SinkInfo.AnalysisFilePackItem> sinkInfoAnalysisFilePackItems = new ArrayList<>();
        for (AnalysisFilePackItemInfo analysisFilePackItemInfo : analysisFilePackItemInfos) {
            sinkInfoAnalysisFilePackItems.add(new SinkInfo.AnalysisFilePackItem(
                    analysisFilePackItemInfo.getKey(), analysisFilePackItemInfo.getIndex(),
                    analysisFilePackItemInfo.getOriginName(), analysisFilePackItemInfo.getLength(),
                    analysisFilePackItemInfo.getRemark()
            ));
        }
        return new SinkInfo.AnalysisFilePack(
                analysisFilePackKey, analysisFilePack.getItemAnchorIndex(), analysisFilePack.getRemark(),
                sinkInfoAnalysisFilePackItems
        );
    }

    private List<SinkInfo.Judgement> lookupJudgements(LongIdKey taskKey) throws Exception {
        List<Judgement> judgements = judgementMaintainService.lookupAsList(
                JudgementMaintainService.CHILD_FOR_TASK, new Object[]{taskKey}
        );
        List<SinkInfo.Judgement> sinkInfoJudgements = new ArrayList<>();
        for (Judgement judgement : judgements) {
            sinkInfoJudgements.add(new SinkInfo.Judgement(
                    judgement.getKey().getDataStringId(), judgement.getValue(), judgement.getMessage()
            ));
        }
        return sinkInfoJudgements;
    }

    /**
     * 心跳定时器任务。
     *
     * @author DwArFeng
     * @since 2.0.0-beta
     */
    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class BeatSchedulerTask implements Runnable {

        private static final Logger LOGGER = LoggerFactory.getLogger(BeatSchedulerTask.class);

        private final TaskOperateHandler taskOperateHandler;

        private final LongIdKey taskKey;

        public BeatSchedulerTask(TaskOperateHandler taskOperateHandler, LongIdKey taskKey) {
            this.taskOperateHandler = taskOperateHandler;
            this.taskKey = taskKey;
        }

        @Override
        public void run() {
            try {
                taskOperateHandler.beat(new TaskBeatInfo(taskKey));
            } catch (Exception e) {
                LOGGER.warn("心跳任务执行失败, 任务主键为 {}, 异常信息如下: ", taskKey, e);
            }
        }

        @Override
        public String toString() {
            return "BeatSchedulerTask{" +
                    "taskOperateHandler=" + taskOperateHandler +
                    ", taskKey=" + taskKey +
                    '}';
        }
    }

    /**
     * 分析器上下文的内部实现。
     *
     * @author DwArFeng
     * @since 2.0.0-beta
     */
    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class InternalAnalyserContext implements Analyser.Context {

        private final LongIdKey sectionKey;
        private final LongIdKey analyserInfoKey;
        private final LongIdKey taskKey;

        private final TaskOperateHandler taskOperateHandler;
        private final TaskEventOperateHandler taskEventOperateHandler;
        private final AnalyserVariableOperateHandler analyserVariableOperateHandler;
        private final AnalysisOperateHandler analysisOperateHandler;
        private final AnalysisPictureFileOperateHandler analysisPictureFileOperateHandler;
        private final AnalysisPicturePackItemFileOperateHandler analysisPicturePackItemFileOperateHandler;
        private final AnalysisFileFileOperateHandler analysisFileFileOperateHandler;
        private final AnalysisFilePackItemFileOperateHandler analysisFilePackItemFileOperateHandler;
        private final ProvideHandler provideHandler;

        public InternalAnalyserContext(
                LongIdKey sectionKey,
                LongIdKey analyserInfoKey,
                LongIdKey taskKey,
                TaskOperateHandler taskOperateHandler,
                TaskEventOperateHandler taskEventOperateHandler,
                AnalyserVariableOperateHandler analyserVariableOperateHandler,
                AnalysisOperateHandler analysisOperateHandler,
                AnalysisPictureFileOperateHandler analysisPictureFileOperateHandler,
                AnalysisPicturePackItemFileOperateHandler analysisPicturePackItemFileOperateHandler,
                AnalysisFileFileOperateHandler analysisFileFileOperateHandler,
                AnalysisFilePackItemFileOperateHandler analysisFilePackItemFileOperateHandler,
                ProvideHandler provideHandler
        ) {
            this.sectionKey = sectionKey;
            this.analyserInfoKey = analyserInfoKey;
            this.taskKey = taskKey;
            this.taskOperateHandler = taskOperateHandler;
            this.taskEventOperateHandler = taskEventOperateHandler;
            this.analyserVariableOperateHandler = analyserVariableOperateHandler;
            this.analysisOperateHandler = analysisOperateHandler;
            this.analysisPictureFileOperateHandler = analysisPictureFileOperateHandler;
            this.analysisPicturePackItemFileOperateHandler = analysisPicturePackItemFileOperateHandler;
            this.analysisFileFileOperateHandler = analysisFileFileOperateHandler;
            this.analysisFilePackItemFileOperateHandler = analysisFilePackItemFileOperateHandler;
            this.provideHandler = provideHandler;
        }

        @Override
        public LongIdKey getSectionKey() {
            return sectionKey;
        }

        @Override
        public LongIdKey getAnalyserInfoKey() {
            return analyserInfoKey;
        }

        @Override
        public LongIdKey getTaskKey() {
            return taskKey;
        }

        @Override
        public void updateTaskModal(TaskUpdateModalInfo info) throws Exception {
            taskOperateHandler.updateModal(info);
        }

        @Override
        public void createTaskEvent(TaskEventCreateInfo info) throws Exception {
            taskEventOperateHandler.create(info);
        }

        @Nullable
        @Override
        public AnalyserVariableInspectResult inspectAnalyserVariable(AnalyserVariableInspectInfo info)
                throws Exception {
            return analyserVariableOperateHandler.inspect(info);
        }

        @Override
        public void upsertAnalyserVariable(AnalyserVariableUpsertInfo info) throws Exception {
            analyserVariableOperateHandler.upsert(info);
        }

        @Override
        public void removeAnalyserVariable(AnalyserVariableRemoveInfo info) throws Exception {
            analyserVariableOperateHandler.remove(info);
        }

        @Nullable
        @Override
        public AnalysisInspectResult inspectAnalysis(AnalysisInspectInfo info) throws HandlerException {
            return analysisOperateHandler.inspect(info);
        }

        @Override
        public void upsertAnalysis(AnalysisUpsertInfo info) throws Exception {
            analysisOperateHandler.upsert(info);
        }

        @Override
        public void removeAnalysis(AnalysisRemoveInfo info) throws Exception {
            analysisOperateHandler.remove(info);
        }

        @Override
        public AnalysisPictureFile downloadPictureFile(AnalysisPictureFileDownloadInfo info) throws HandlerException {
            return analysisPictureFileOperateHandler.downloadFile(info);
        }

        @Override
        public AnalysisPictureFileStream downloadPictureFileStream(AnalysisPictureFileStreamDownloadInfo info)
                throws HandlerException {
            return analysisPictureFileOperateHandler.downloadFileStream(info);
        }

        @Override
        public AnalysisPictureThumbnail downloadPictureThumbnail(AnalysisPictureThumbnailDownloadInfo info)
                throws HandlerException {
            return analysisPictureFileOperateHandler.downloadThumbnail(info);
        }

        @Override
        public AnalysisPicturePackItemFile downloadPicturePackItemFile(AnalysisPicturePackItemFileDownloadInfo info)
                throws HandlerException {
            return analysisPicturePackItemFileOperateHandler.downloadFile(info);
        }

        @Override
        public AnalysisPicturePackItemFileStream downloadPicturePackItemFileStream(
                AnalysisPicturePackItemFileStreamDownloadInfo info
        ) throws HandlerException {
            return analysisPicturePackItemFileOperateHandler.downloadFileStream(info);
        }

        @Override
        public AnalysisPicturePackItemThumbnail downloadPicturePackItemThumbnail(
                AnalysisPicturePackItemThumbnailDownloadInfo info
        ) throws HandlerException {
            return analysisPicturePackItemFileOperateHandler.downloadThumbnail(info);
        }

        @Override
        public AnalysisFileFile downloadFileFile(AnalysisFileFileDownloadInfo info) throws HandlerException {
            return analysisFileFileOperateHandler.downloadFile(info);
        }

        @Override
        public AnalysisFileFileStream downloadFileFileStream(AnalysisFileFileStreamDownloadInfo info)
                throws HandlerException {
            return analysisFileFileOperateHandler.downloadFileStream(info);
        }

        @Override
        public AnalysisFilePackItemFile downloadFilePackItemFile(AnalysisFilePackItemFileDownloadInfo info)
                throws HandlerException {
            return analysisFilePackItemFileOperateHandler.downloadFile(info);
        }

        @Override
        public AnalysisFilePackItemFileStream downloadFilePackItemFileStream(
                AnalysisFilePackItemFileStreamDownloadInfo info
        ) throws HandlerException {
            return analysisFilePackItemFileOperateHandler.downloadFileStream(info);
        }

        @Override
        public ProvideResult provide(ProvideInfo info) throws HandlerException {
            return provideHandler.provide(info);
        }

        @Override
        public String toString() {
            return "InternalAnalyserContext{" +
                    "sectionKey=" + sectionKey +
                    ", analyserInfoKey=" + analyserInfoKey +
                    ", taskKey=" + taskKey +
                    ", taskOperateHandler=" + taskOperateHandler +
                    ", taskEventOperateHandler=" + taskEventOperateHandler +
                    ", analyserVariableOperateHandler=" + analyserVariableOperateHandler +
                    ", analysisOperateHandler=" + analysisOperateHandler +
                    ", analysisPictureFileOperateHandler=" + analysisPictureFileOperateHandler +
                    ", analysisPicturePackItemFileOperateHandler=" + analysisPicturePackItemFileOperateHandler +
                    ", analysisFileFileOperateHandler=" + analysisFileFileOperateHandler +
                    ", analysisFilePackItemFileOperateHandler=" + analysisFilePackItemFileOperateHandler +
                    ", provideHandler=" + provideHandler +
                    '}';
        }
    }

    /**
     * 判断器上下文的内部实现。
     *
     * @author DwArFeng
     * @since 2.0.0-beta
     */
    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class InternalJudgerContext implements Judger.Context {

        private final LongIdKey sectionKey;
        private final LongIdKey judgerInfoKey;
        private final LongIdKey taskKey;

        private final TaskOperateHandler taskOperateHandler;
        private final TaskEventOperateHandler taskEventOperateHandler;
        private final JudgerVariableOperateHandler judgerVariableOperateHandler;
        private final AnalysisOperateHandler analysisOperateHandler;
        private final AnalysisPictureFileOperateHandler analysisPictureFileOperateHandler;
        private final AnalysisPicturePackItemFileOperateHandler analysisPicturePackItemFileOperateHandler;
        private final AnalysisFileFileOperateHandler analysisFileFileOperateHandler;
        private final AnalysisFilePackItemFileOperateHandler analysisFilePackItemFileOperateHandler;
        private final JudgementOperateHandler judgementOperateHandler;

        public InternalJudgerContext(
                LongIdKey sectionKey,
                LongIdKey judgerInfoKey,
                LongIdKey taskKey,
                TaskOperateHandler taskOperateHandler,
                TaskEventOperateHandler taskEventOperateHandler,
                JudgerVariableOperateHandler judgerVariableOperateHandler,
                AnalysisOperateHandler analysisOperateHandler,
                AnalysisPictureFileOperateHandler analysisPictureFileOperateHandler,
                AnalysisPicturePackItemFileOperateHandler analysisPicturePackItemFileOperateHandler,
                AnalysisFileFileOperateHandler analysisFileFileOperateHandler,
                AnalysisFilePackItemFileOperateHandler analysisFilePackItemFileOperateHandler,
                JudgementOperateHandler judgementOperateHandler
        ) {
            this.sectionKey = sectionKey;
            this.judgerInfoKey = judgerInfoKey;
            this.taskKey = taskKey;
            this.taskOperateHandler = taskOperateHandler;
            this.taskEventOperateHandler = taskEventOperateHandler;
            this.judgerVariableOperateHandler = judgerVariableOperateHandler;
            this.analysisOperateHandler = analysisOperateHandler;
            this.analysisPictureFileOperateHandler = analysisPictureFileOperateHandler;
            this.analysisPicturePackItemFileOperateHandler = analysisPicturePackItemFileOperateHandler;
            this.analysisFileFileOperateHandler = analysisFileFileOperateHandler;
            this.analysisFilePackItemFileOperateHandler = analysisFilePackItemFileOperateHandler;
            this.judgementOperateHandler = judgementOperateHandler;
        }

        @Override
        public LongIdKey getSectionKey() {
            return sectionKey;
        }

        @Override
        public LongIdKey getJudgerInfoKey() {
            return judgerInfoKey;
        }

        @Override
        public LongIdKey getTaskKey() {
            return taskKey;
        }

        @Override
        public void updateTaskModal(TaskUpdateModalInfo info) throws Exception {
            taskOperateHandler.updateModal(info);
        }

        @Override
        public void createTaskEvent(TaskEventCreateInfo info) throws Exception {
            taskEventOperateHandler.create(info);
        }

        @Nullable
        @Override
        public JudgerVariableInspectResult inspectJudgerVariable(JudgerVariableInspectInfo info) throws Exception {
            return judgerVariableOperateHandler.inspect(info);
        }

        @Override
        public void upsertJudgerVariable(JudgerVariableUpsertInfo info) throws Exception {
            judgerVariableOperateHandler.upsert(info);
        }

        @Override
        public void removeJudgerVariable(JudgerVariableRemoveInfo info) throws Exception {
            judgerVariableOperateHandler.remove(info);
        }

        @Override
        public AnalysisInspectResult inspectAnalysis(AnalysisInspectInfo info) throws HandlerException {
            return analysisOperateHandler.inspect(info);
        }

        @Override
        public AnalysisPictureFile downloadPictureFile(AnalysisPictureFileDownloadInfo info) throws HandlerException {
            return analysisPictureFileOperateHandler.downloadFile(info);
        }

        @Override
        public AnalysisPictureFileStream downloadPictureFileStream(AnalysisPictureFileStreamDownloadInfo info)
                throws HandlerException {
            return analysisPictureFileOperateHandler.downloadFileStream(info);
        }

        @Override
        public AnalysisPictureThumbnail downloadPictureThumbnail(AnalysisPictureThumbnailDownloadInfo info)
                throws HandlerException {
            return analysisPictureFileOperateHandler.downloadThumbnail(info);
        }

        @Override
        public AnalysisPicturePackItemFile downloadPicturePackItemFile(AnalysisPicturePackItemFileDownloadInfo info)
                throws HandlerException {
            return analysisPicturePackItemFileOperateHandler.downloadFile(info);
        }

        @Override
        public AnalysisPicturePackItemFileStream downloadPicturePackItemFileStream(
                AnalysisPicturePackItemFileStreamDownloadInfo info
        ) throws HandlerException {
            return analysisPicturePackItemFileOperateHandler.downloadFileStream(info);
        }

        @Override
        public AnalysisPicturePackItemThumbnail downloadPicturePackItemThumbnail(
                AnalysisPicturePackItemThumbnailDownloadInfo info
        ) throws HandlerException {
            return analysisPicturePackItemFileOperateHandler.downloadThumbnail(info);
        }

        @Override
        public AnalysisFileFile downloadFileFile(AnalysisFileFileDownloadInfo info) throws HandlerException {
            return analysisFileFileOperateHandler.downloadFile(info);
        }

        @Override
        public AnalysisFileFileStream downloadFileFileStream(AnalysisFileFileStreamDownloadInfo info)
                throws HandlerException {
            return analysisFileFileOperateHandler.downloadFileStream(info);
        }

        @Override
        public AnalysisFilePackItemFile downloadFilePackItemFile(AnalysisFilePackItemFileDownloadInfo info)
                throws HandlerException {
            return analysisFilePackItemFileOperateHandler.downloadFile(info);
        }

        @Override
        public AnalysisFilePackItemFileStream downloadFilePackItemFileStream(
                AnalysisFilePackItemFileStreamDownloadInfo info
        ) throws HandlerException {
            return analysisFilePackItemFileOperateHandler.downloadFileStream(info);
        }

        @Nullable
        @Override
        public JudgementInspectResult inspectJudgement(JudgementInspectInfo info) throws HandlerException {
            return judgementOperateHandler.inspect(info);
        }

        @Override
        public void upsertJudgement(JudgementUpsertInfo info) throws Exception {
            judgementOperateHandler.upsert(info);
        }

        @Override
        public void removeJudgement(JudgementRemoveInfo info) throws Exception {
            judgementOperateHandler.remove(info);
        }

        @Override
        public String toString() {
            return "InternalJudgerContext{" +
                    "sectionKey=" + sectionKey +
                    ", judgerInfoKey=" + judgerInfoKey +
                    ", taskKey=" + taskKey +
                    ", taskOperateHandler=" + taskOperateHandler +
                    ", taskEventOperateHandler=" + taskEventOperateHandler +
                    ", judgerVariableOperateHandler=" + judgerVariableOperateHandler +
                    ", analysisOperateHandler=" + analysisOperateHandler +
                    ", analysisPictureFileOperateHandler=" + analysisPictureFileOperateHandler +
                    ", analysisPicturePackItemFileOperateHandler=" + analysisPicturePackItemFileOperateHandler +
                    ", analysisFileFileOperateHandler=" + analysisFileFileOperateHandler +
                    ", analysisFilePackItemFileOperateHandler=" + analysisFilePackItemFileOperateHandler +
                    ", judgementOperateHandler=" + judgementOperateHandler +
                    '}';
        }
    }
}

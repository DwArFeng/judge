package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.util.Constants;
import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.bean.entity.JudgementHistory;
import com.dwarfeng.judge.stack.bean.entity.Task;
import com.dwarfeng.judge.stack.handler.*;
import com.dwarfeng.judge.stack.service.JudgementHistoryMaintainService;
import com.dwarfeng.judge.stack.service.TaskMaintainService;
import com.dwarfeng.judge.stack.struct.JobLocalCache;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
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
    private final JudgementHistoryMaintainService judgementHistoryMaintainService;

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
    private final JudgementModalOperateHandler judgementModalOperateHandler;

    private final ThreadPoolTaskExecutor executor;
    private final ThreadPoolTaskScheduler scheduler;

    private final HandlerValidator handlerValidator;

    @Value("${task.beat_interval}")
    private long beatInterval;

    public JobHandlerImpl(
            ApplicationContext ctx,
            TaskMaintainService taskMaintainService,
            JudgementHistoryMaintainService judgementHistoryMaintainService,
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
            JudgementModalOperateHandler judgementModalOperateHandler,
            ThreadPoolTaskExecutor executor,
            ThreadPoolTaskScheduler scheduler,
            HandlerValidator handlerValidator
    ) {
        this.ctx = ctx;
        this.taskMaintainService = taskMaintainService;
        this.judgementHistoryMaintainService = judgementHistoryMaintainService;
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
        this.judgementModalOperateHandler = judgementModalOperateHandler;
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

        // 日期判断，当前时间必须晚于 JudgementModal 的 happenedData。
        try {
            Date currentDate = new Date();
            handlerValidator.makeSureJudgementModalUpdateHappenedDateValid(sectionKey, currentDate);
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
        Judger judger;
        LongIdKey judgerInfoKey;
        try {
            JobLocalCache jobLocalCache = jobLocalCacheHandler.get(sectionKey);
            analyserInfoKeys = jobLocalCache.getAnalyserInfoKeys();
            analyserMap = jobLocalCache.getAnalyserMap();
            judgerInfoKey = jobLocalCache.getJudgerInfoKey();
            judger = jobLocalCache.getJudger();
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

        // 如果 judger 为 null，则结束任务。
        if (Objects.isNull(judger)) {
            // 取消心跳任务。
            beatTaskFuture.cancel(true);
            // 生成消息。
            String message = "作业部件缺少可用的判断器, 无法执行作业, 任务主键: " + taskKey +
                    ", 部件主键: " + sectionKey;
            // 记录日志。
            LOGGER.warn(message);
            // 插入任务事件。
            taskEventOperateHandler.create(new TaskEventCreateInfo(taskKey, message));
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

        // 进行判断，并展开判断结果参数。
        double judgeResultValue;
        String judgeResultMessage;
        try {
            Judger.JudgeResult judgeResult = judge(sectionKey, judgerInfoKey, taskKey, judger);
            judgeResultValue = judgeResult.getValue();
            judgeResultMessage = judgeResult.getMessage();
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

        // 确保 judgeResultValue 在 [0.0, 1.0] 之间。
        try {
            handlerValidator.makeSureJudgementModalUpdateValueValid(judgeResultValue);
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

        // 生成系统日期，该日期可以保证是合法的。
        Date currentDate = new Date();

        // 处理判断结果.
        try {
            processJudgement(sectionKey, judgerInfoKey, currentDate, judgeResultValue, judgeResultMessage);
        } catch (ServiceException e) {
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
                    analysisFilePackItemFileOperateHandler
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

    private Judger.JudgeResult judge(
            LongIdKey sectionKey, LongIdKey judgerInfoKey, LongIdKey taskKey, Judger judger
    ) throws Exception {
        // 构造判断器上下文。
        Judger.Context judgerContext = ctx.getBean(
                InternalJudgerContext.class,
                sectionKey, judgerInfoKey, taskKey,
                taskOperateHandler, taskEventOperateHandler,
                judgerVariableOperateHandler, analysisOperateHandler,
                analysisPictureFileOperateHandler, analysisPicturePackItemFileOperateHandler,
                analysisFileFileOperateHandler, analysisFilePackItemFileOperateHandler
        );

        // 创建判断器执行器，并初始化。
        Judger.Executor judgerExecutor = judger.newExecutor();
        judgerExecutor.init(judgerContext);

        // 调用判断器执行器的判断方法，获取判断结果并返回。
        return judgerExecutor.judge();
    }

    private void processJudgement(
            LongIdKey sectionKey, LongIdKey judgerInfoKey, Date currentDate, double value, String message
    ) throws Exception {
        // 调用操作服务更新判断结果模态。
        JudgementModalUpdateInfo judgementModalUpdateInfo = new JudgementModalUpdateInfo(
                sectionKey, judgerInfoKey, currentDate, value, message
        );
        judgementModalOperateHandler.update(judgementModalUpdateInfo);

        // 生成判断结果历史，调用维护服务插入。
        JudgementHistory judgementHistory = new JudgementHistory(
                null, sectionKey, judgerInfoKey, currentDate, value, message
        );
        judgementHistoryMaintainService.insert(judgementHistory);
    }

    /**
     * 心跳定时器任务。
     *
     * @author DwArFeng
     * @since 2.0.0
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
     * @since 2.0.0
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
                AnalysisFilePackItemFileOperateHandler analysisFilePackItemFileOperateHandler
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
                    '}';
        }
    }

    /**
     * 判断器上下文的内部实现。
     *
     * @author DwArFeng
     * @since 2.0.0
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
                AnalysisFilePackItemFileOperateHandler analysisFilePackItemFileOperateHandler
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

        @Override
        public String toString() {
            return "InternalJudgerContext{" +
                    "sectionKey=" + sectionKey +
                    ", judgerInfoKey=" + judgerInfoKey +
                    ", judgerVariableOperateHandler=" + judgerVariableOperateHandler +
                    ", analysisOperateHandler=" + analysisOperateHandler +
                    ", analysisPictureFileOperateHandler=" + analysisPictureFileOperateHandler +
                    ", analysisPicturePackItemFileOperateHandler=" + analysisPicturePackItemFileOperateHandler +
                    ", analysisFileFileOperateHandler=" + analysisFileFileOperateHandler +
                    ", analysisFilePackItemFileOperateHandler=" + analysisFilePackItemFileOperateHandler +
                    '}';
        }
    }
}

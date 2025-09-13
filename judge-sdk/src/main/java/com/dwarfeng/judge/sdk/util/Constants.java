package com.dwarfeng.judge.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 常量类。
 *
 * @author DwArFeng
 * @since 1.4.1
 */
public final class Constants {

    private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);

    @TaskStatusItem
    public static final int TASK_STATUS_CREATED = 0;
    @TaskStatusItem
    public static final int TASK_STATUS_PROCESSING = 1;
    @TaskStatusItem
    public static final int TASK_STATUS_FINISHED = 2;
    @TaskStatusItem
    public static final int TASK_STATUS_FAILED = 3;
    @TaskStatusItem
    public static final int TASK_STATUS_EXPIRED = 4;
    @TaskStatusItem
    public static final int TASK_STATUS_DIED = 5;

    @AnalysisTypeItem
    public static final int ANALYSIS_TYPE_STRING = 0;
    @AnalysisTypeItem
    public static final int ANALYSIS_TYPE_LONG = 1;
    @AnalysisTypeItem
    public static final int ANALYSIS_TYPE_DOUBLE = 2;
    @AnalysisTypeItem
    public static final int ANALYSIS_TYPE_BOOLEAN = 3;
    @AnalysisTypeItem
    public static final int ANALYSIS_TYPE_DATE = 4;
    @AnalysisTypeItem
    public static final int ANALYSIS_TYPE_PICTURE = 5;
    @AnalysisTypeItem
    public static final int ANALYSIS_TYPE_PICTURE_PACK = 6;
    @AnalysisTypeItem
    public static final int ANALYSIS_TYPE_FILE = 7;
    @AnalysisTypeItem
    public static final int ANALYSIS_TYPE_FILE_PACK = 8;

    @AnalysisFilePackUpsertTypeItem
    public static final int ANALYSE_FILE_PACK_UPSERT_TYPE_APPEND = 0;
    @AnalysisFilePackUpsertTypeItem
    public static final int ANALYSE_FILE_PACK_UPSERT_TYPE_REPLACE = 1;

    @AnalysisPicturePackUpsertTypeItem
    public static final int ANALYSE_PICTURE_PACK_UPSERT_TYPE_APPEND = 0;
    @AnalysisPicturePackUpsertTypeItem
    public static final int ANALYSE_PICTURE_PACK_UPSERT_TYPE_REPLACE = 1;

    /**
     * 消费者处理器的检查间隔。
     */
    public static final long CONSUMER_HANDLER_CHECK_INTERVAL = 5000L;

    private static final Lock LOCK = new ReentrantLock();

    private static List<Integer> taskStatusSpace = null;
    private static List<Integer> analysisTypeSpace = null;
    private static List<Integer> analysisFilePackUpsertTypeSpace = null;
    private static List<Integer> analysisPicturePackUpsertTypeSpace = null;

    /**
     * 任务状态空间。
     *
     * @return 任务状态空间。
     */
    public static List<Integer> taskStatusSpace() {
        if (Objects.nonNull(taskStatusSpace)) {
            return taskStatusSpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(taskStatusSpace)) {
                return taskStatusSpace;
            }
            initTaskStatusSpace();
            return taskStatusSpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initTaskStatusSpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(TaskStatusItem.class)) {
                continue;
            }
            Integer value;
            try {
                value = (Integer) declaredField.get(null);
                result.add(value);
            } catch (Exception e) {
                LOGGER.error("初始化异常, 请检查代码, 信息如下: ", e);
            }
        }

        taskStatusSpace = Collections.unmodifiableList(result);
    }

    /**
     * 分析结果类型空间。
     *
     * @return 分析结果类型空间。
     */
    public static List<Integer> analysisTypeSpace() {
        if (Objects.nonNull(analysisTypeSpace)) {
            return analysisTypeSpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(analysisTypeSpace)) {
                return analysisTypeSpace;
            }
            initAnalysisTypeSpace();
            return analysisTypeSpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initAnalysisTypeSpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(AnalysisTypeItem.class)) {
                continue;
            }
            Integer value;
            try {
                value = (Integer) declaredField.get(null);
                result.add(value);
            } catch (Exception e) {
                LOGGER.error("初始化异常, 请检查代码, 信息如下: ", e);
            }
        }

        analysisTypeSpace = Collections.unmodifiableList(result);
    }

    /**
     * 分析结果文件包插入或更新类型空间。
     *
     * @return 分析结果文件包插入或更新类型空间。
     */
    public static List<Integer> analysisFilePackUpsertTypeSpace() {
        if (Objects.nonNull(analysisFilePackUpsertTypeSpace)) {
            return analysisFilePackUpsertTypeSpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(analysisFilePackUpsertTypeSpace)) {
                return analysisFilePackUpsertTypeSpace;
            }
            initAnalysisFilePackUpsertTypeSpace();
            return analysisFilePackUpsertTypeSpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initAnalysisFilePackUpsertTypeSpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(AnalysisFilePackUpsertTypeItem.class)) {
                continue;
            }
            Integer value;
            try {
                value = (Integer) declaredField.get(null);
                result.add(value);
            } catch (Exception e) {
                LOGGER.error("初始化异常, 请检查代码, 信息如下: ", e);
            }
        }

        analysisFilePackUpsertTypeSpace = Collections.unmodifiableList(result);
    }

    /**
     * 分析结果图片包插入或更新类型空间。
     *
     * @return 分析结果图片包插入或更新类型空间。
     */
    public static List<Integer> analysisPicturePackUpsertTypeSpace() {
        if (Objects.nonNull(analysisPicturePackUpsertTypeSpace)) {
            return analysisPicturePackUpsertTypeSpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(analysisPicturePackUpsertTypeSpace)) {
                return analysisPicturePackUpsertTypeSpace;
            }
            initAnalysisPicturePackUpsertTypeSpace();
            return analysisPicturePackUpsertTypeSpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initAnalysisPicturePackUpsertTypeSpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(AnalysisPicturePackUpsertTypeItem.class)) {
                continue;
            }
            Integer value;
            try {
                value = (Integer) declaredField.get(null);
                result.add(value);
            } catch (Exception e) {
                LOGGER.error("初始化异常, 请检查代码, 信息如下: ", e);
            }
        }

        analysisPicturePackUpsertTypeSpace = Collections.unmodifiableList(result);
    }

    private Constants() {
        throw new IllegalStateException("禁止实例化");
    }
}

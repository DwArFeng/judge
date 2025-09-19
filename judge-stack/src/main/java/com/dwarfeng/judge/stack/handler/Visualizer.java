package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.exception.VisualizerException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

import javax.annotation.Nullable;

/**
 * 可视化器。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface Visualizer {

    /**
     * 生成一个新的可视化器执行器。
     *
     * @return 新的可视化器执行器。
     * @throws VisualizerException 可视化器异常。
     */
    Executor newExecutor() throws VisualizerException;

    /**
     * 可视化器执行器。
     *
     * @author DwArFeng
     * @since 2.2.0
     */
    interface Executor {

        /**
         * 初始化可视化器执行器。
         *
         * @param context 指定的可视化器上下文。
         */
        void init(Context context);

        /**
         * 可视化数据。
         *
         * <p>
         * 该方法被调用时，需要按照预定的逻辑对数据进行可视化，并把可视化的结果或中间值以可视化结果的形式，
         * 通过 {@link Context} 进行持久化。
         *
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void analyse() throws Exception;
    }

    /**
     * 可视化器上下文。
     *
     * @author DwArFeng
     * @since 2.2.0
     */
    interface Context {

        /**
         * 获取部件键。
         *
         * @return 部件键。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        LongIdKey getSectionKey() throws Exception;

        /**
         * 获取可视化器信息键。
         *
         * @return 可视化器信息键。
         * @throws HandlerException 方法执行过程中发生的任何异常。
         */
        LongIdKey getVisualizerInfoKey() throws HandlerException;

        /**
         * 获取任务键。
         *
         * @return 任务键。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        LongIdKey getTaskKey() throws Exception;

        /**
         * 更新任务模态信息。
         *
         * @param info 更新任务模态信息信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void updateTaskModal(TaskUpdateModalInfo info) throws Exception;

        /**
         * 创建任务事件。
         *
         * @param info 任务事件创建信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void createTaskEvent(TaskEventCreateInfo info) throws Exception;

        /**
         * 查看可视化结果。
         *
         * <p>
         * 当 {@link AnalysisInspectInfo#getTaskKey()} 不存在时，该方法抛出异常。<br>
         * 当 {@link AnalysisInspectInfo#getTaskKey()} 存在，
         * 但 {@link AnalysisInspectInfo#getDataStringId()} 不存在时，该方法返回 <code>null</code>。
         *
         * @param info 可视化结果查看信息。
         * @return 查看结果。
         * @throws HandlerException 处理器异常。
         */
        @Nullable
        AnalysisInspectResult inspectAnalysis(AnalysisInspectInfo info) throws HandlerException;

        /**
         * 下载可视化结果图片。
         *
         * @param info 下载信息。
         * @return 可视化结果图片。
         * @throws HandlerException 处理器异常。
         */
        AnalysisPictureFile downloadPictureFile(AnalysisPictureFileDownloadInfo info) throws HandlerException;

        /**
         * 下载可视化结果图片流。
         *
         * <p>
         * 如果返回的 {@link AnalysisPictureFileStream} 不为 <code>null</code>，则调用者有义务关闭
         * {@link AnalysisPictureFileStream} 中的输入流，即其中的 {@link AnalysisPictureFileStream#getContent()}。
         *
         * @param info 下载信息。
         * @return 可视化结果图片流。
         * @throws HandlerException 处理器异常。
         */
        AnalysisPictureFileStream downloadPictureFileStream(AnalysisPictureFileStreamDownloadInfo info)
                throws HandlerException;

        /**
         * 下载可视化结果图片缩略图。
         *
         * @param info 下载信息。
         * @return 可视化结果图片缩略图。
         * @throws HandlerException 处理器异常。
         */
        AnalysisPictureThumbnail downloadPictureThumbnail(AnalysisPictureThumbnailDownloadInfo info)
                throws HandlerException;

        /**
         * 下载可视化结果图片包条目文件。
         *
         * @param info 下载信息。
         * @return 可视化结果图片包条目文件。
         * @throws HandlerException 处理器异常。
         */
        AnalysisPicturePackItemFile downloadPicturePackItemFile(AnalysisPicturePackItemFileDownloadInfo info)
                throws HandlerException;

        /**
         * 下载可视化结果图片包条目文件流。
         *
         * <p>
         * 如果返回的 {@link AnalysisPicturePackItemFileStream} 不为 <code>null</code>，则调用者有义务关闭
         * {@link AnalysisPicturePackItemFileStream} 中的输入流，
         * 即其中的 {@link AnalysisPicturePackItemFileStream#getContent()}。
         *
         * @param info 下载信息。
         * @return 下载可视化结果图片包条目文件流。
         * @throws HandlerException 处理器异常。
         */
        AnalysisPicturePackItemFileStream downloadPicturePackItemFileStream(
                AnalysisPicturePackItemFileStreamDownloadInfo info
        ) throws HandlerException;

        /**
         * 下载可视化结果图片包条目缩略图。
         *
         * @param info 下载信息。
         * @return 下载可视化结果图片包条目缩略图。
         * @throws HandlerException 处理器异常。
         */
        AnalysisPicturePackItemThumbnail downloadPicturePackItemThumbnail(
                AnalysisPicturePackItemThumbnailDownloadInfo info
        ) throws HandlerException;

        /**
         * 下载可视化结果文件。
         *
         * @param info 下载信息。
         * @return 可视化结果文件。
         * @throws HandlerException 处理器异常。
         */
        AnalysisFileFile downloadFileFile(AnalysisFileFileDownloadInfo info) throws HandlerException;

        /**
         * 下载可视化结果文件流。
         *
         * <p>
         * 如果返回的 {@link AnalysisFileFileStream} 不为 <code>null</code>，则调用者有义务关闭
         * {@link AnalysisFileFileStream} 中的输入流，即其中的 {@link AnalysisFileFileStream#getContent()}。
         *
         * @param info 下载信息。
         * @return 可视化结果文件流。
         * @throws HandlerException 处理器异常。
         */
        AnalysisFileFileStream downloadFileFileStream(AnalysisFileFileStreamDownloadInfo info) throws HandlerException;

        /**
         * 下载可视化结果文件包条目文件。
         *
         * @param info 下载信息。
         * @return 可视化结果文件包条目文件。
         * @throws HandlerException 处理器异常。
         */
        AnalysisFilePackItemFile downloadFilePackItemFile(AnalysisFilePackItemFileDownloadInfo info)
                throws HandlerException;

        /**
         * 下载可视化结果文件包条目文件流。
         *
         * <p>
         * 如果返回的 {@link AnalysisFilePackItemFileStream} 不为 <code>null</code>，则调用者有义务关闭
         * {@link AnalysisFilePackItemFileStream} 中的输入流，
         * 即其中的 {@link AnalysisFilePackItemFileStream#getContent()}。
         *
         * @param info 下载信息。
         * @return 下载可视化结果文件包条目文件流。
         * @throws HandlerException 处理器异常。
         */
        AnalysisFilePackItemFileStream downloadFilePackItemFileStream(AnalysisFilePackItemFileStreamDownloadInfo info)
                throws HandlerException;

        /**
         * 查看判断结果。
         *
         * <p>
         * 当 {@link JudgementInspectInfo#getTaskKey()} 不存在时，该方法抛出异常。<br>
         * 当 {@link JudgementInspectInfo#getTaskKey()} 存在，
         * 但 {@link JudgementInspectInfo#getDataStringId()} 不存在时，该方法返回 <code>null</code>。
         *
         * @param info 判断结果查看信息。
         * @return 查看结果。
         * @throws HandlerException 处理器异常。
         */
        @Nullable
        JudgementInspectResult inspectJudgement(JudgementInspectInfo info) throws HandlerException;

        /**
         * 插入/更新可视化数据。
         *
         * @param info 可视化数据插入/更新信息。
         * @throws HandlerException 处理器异常。
         */
        void upsertVisualizeData(VisualizeDataUpsertInfo info) throws HandlerException;
    }
}

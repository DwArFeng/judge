package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

import javax.annotation.Nullable;

/**
 * 判断器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface Judger {

    /**
     * 生成一个新的判断器执行器。
     *
     * @return 新的判断器执行器。
     * @throws JudgerException 判断器异常。
     */
    Executor newExecutor() throws JudgerException;

    /**
     * 判断器执行器。
     *
     * @author DwArFeng
     * @since 2.0.0-beta
     */
    interface Executor {

        /**
         * 初始化判断器执行器。
         *
         * @param context 指定的判断器上下文。
         */
        void init(Context context);

        /**
         * 判断数据。
         *
         * <p>
         * 该方法被调用时，需要按照预定的逻辑对数据进行判断，并把判断的结果或中间值以判断结果的形式，
         * 通过 {@link Context} 进行持久化。
         *
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void judge() throws Exception;
    }

    /**
     * 判断器上下文。
     *
     * @author DwArFeng
     * @since 2.0.0-beta
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
         * 获取判断器信息键。
         *
         * @return 判断器信息键。
         * @throws HandlerException 方法执行过程中发生的任何异常。
         */
        LongIdKey getJudgerInfoKey() throws HandlerException;

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
         * 查看判断器变量。
         *
         * <p>
         * 该方法返回指定的判断器变量查看信息对应的判断器变量的查看结果。<br>
         * 如果指定的判断器变量不存在，则返回 <code>null</code>。
         *
         * @param info 判断器变量查看信息。
         * @return 判断器变量的查看结果。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        @Nullable
        JudgerVariableInspectResult inspectJudgerVariable(JudgerVariableInspectInfo info) throws Exception;

        /**
         * 插入/更新判断器变量。
         *
         * @param info 判断器变量插入/更新信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void upsertJudgerVariable(JudgerVariableUpsertInfo info) throws Exception;

        /**
         * 删除判断器变量。
         *
         * @param info 判断器变量删除信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void removeJudgerVariable(JudgerVariableRemoveInfo info) throws Exception;

        /**
         * 查看分析结果。
         *
         * <p>
         * 当 {@link AnalysisInspectInfo#getTaskKey()} 不存在时，该方法抛出异常。<br>
         * 当 {@link AnalysisInspectInfo#getTaskKey()} 存在，
         * 但 {@link AnalysisInspectInfo#getDataStringId()} 不存在时，该方法返回 <code>null</code>。
         *
         * @param info 分析结果查看信息。
         * @return 查看结果。
         * @throws HandlerException 处理器异常。
         */
        @Nullable
        AnalysisInspectResult inspectAnalysis(AnalysisInspectInfo info) throws HandlerException;

        /**
         * 下载分析结果图片。
         *
         * @param info 下载信息。
         * @return 分析结果图片。
         * @throws HandlerException 处理器异常。
         */
        AnalysisPictureFile downloadPictureFile(AnalysisPictureFileDownloadInfo info) throws HandlerException;

        /**
         * 下载分析结果图片流。
         *
         * <p>
         * 如果返回的 {@link AnalysisPictureFileStream} 不为 <code>null</code>，则调用者有义务关闭
         * {@link AnalysisPictureFileStream} 中的输入流，即其中的 {@link AnalysisPictureFileStream#getContent()}。
         *
         * @param info 下载信息。
         * @return 分析结果图片流。
         * @throws HandlerException 处理器异常。
         */
        AnalysisPictureFileStream downloadPictureFileStream(AnalysisPictureFileStreamDownloadInfo info)
                throws HandlerException;

        /**
         * 下载分析结果图片缩略图。
         *
         * @param info 下载信息。
         * @return 分析结果图片缩略图。
         * @throws HandlerException 处理器异常。
         */
        AnalysisPictureThumbnail downloadPictureThumbnail(AnalysisPictureThumbnailDownloadInfo info)
                throws HandlerException;

        /**
         * 下载分析结果图片包条目文件。
         *
         * @param info 下载信息。
         * @return 分析结果图片包条目文件。
         * @throws HandlerException 处理器异常。
         */
        AnalysisPicturePackItemFile downloadPicturePackItemFile(AnalysisPicturePackItemFileDownloadInfo info)
                throws HandlerException;

        /**
         * 下载分析结果图片包条目文件流。
         *
         * <p>
         * 如果返回的 {@link AnalysisPicturePackItemFileStream} 不为 <code>null</code>，则调用者有义务关闭
         * {@link AnalysisPicturePackItemFileStream} 中的输入流，
         * 即其中的 {@link AnalysisPicturePackItemFileStream#getContent()}。
         *
         * @param info 下载信息。
         * @return 下载分析结果图片包条目文件流。
         * @throws HandlerException 处理器异常。
         */
        AnalysisPicturePackItemFileStream downloadPicturePackItemFileStream(
                AnalysisPicturePackItemFileStreamDownloadInfo info
        ) throws HandlerException;

        /**
         * 下载分析结果图片包条目缩略图。
         *
         * @param info 下载信息。
         * @return 下载分析结果图片包条目缩略图。
         * @throws HandlerException 处理器异常。
         */
        AnalysisPicturePackItemThumbnail downloadPicturePackItemThumbnail(
                AnalysisPicturePackItemThumbnailDownloadInfo info
        ) throws HandlerException;

        /**
         * 下载分析结果文件。
         *
         * @param info 下载信息。
         * @return 分析结果文件。
         * @throws HandlerException 处理器异常。
         */
        AnalysisFileFile downloadFileFile(AnalysisFileFileDownloadInfo info) throws HandlerException;

        /**
         * 下载分析结果文件流。
         *
         * <p>
         * 如果返回的 {@link AnalysisFileFileStream} 不为 <code>null</code>，则调用者有义务关闭
         * {@link AnalysisFileFileStream} 中的输入流，即其中的 {@link AnalysisFileFileStream#getContent()}。
         *
         * @param info 下载信息。
         * @return 分析结果文件流。
         * @throws HandlerException 处理器异常。
         */
        AnalysisFileFileStream downloadFileFileStream(AnalysisFileFileStreamDownloadInfo info) throws HandlerException;

        /**
         * 下载分析结果文件包条目文件。
         *
         * @param info 下载信息。
         * @return 分析结果文件包条目文件。
         * @throws HandlerException 处理器异常。
         */
        AnalysisFilePackItemFile downloadFilePackItemFile(AnalysisFilePackItemFileDownloadInfo info)
                throws HandlerException;

        /**
         * 下载分析结果文件包条目文件流。
         *
         * <p>
         * 如果返回的 {@link AnalysisFilePackItemFileStream} 不为 <code>null</code>，则调用者有义务关闭
         * {@link AnalysisFilePackItemFileStream} 中的输入流，
         * 即其中的 {@link AnalysisFilePackItemFileStream#getContent()}。
         *
         * @param info 下载信息。
         * @return 下载分析结果文件包条目文件流。
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
         * 插入或更新判断结果。
         *
         * @param info 判断结果插入或更新信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void upsertJudgement(JudgementUpsertInfo info) throws Exception;

        /**
         * 删除判断结果。
         *
         * @param info 判断结果删除信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void removeJudgement(JudgementRemoveInfo info) throws Exception;
    }
}

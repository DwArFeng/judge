package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 分析结果文件包条目文件操作处理器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalysisFilePackItemFileOperateHandler extends Handler {

    /**
     * 下载分析结果文件包条目文件。
     *
     * @param info 下载信息。
     * @return 分析结果文件包条目文件。
     * @throws HandlerException 处理器异常。
     */
    AnalysisFilePackItemFile downloadFile(AnalysisFilePackItemFileDownloadInfo info) throws HandlerException;

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
    AnalysisFilePackItemFileStream downloadFileStream(AnalysisFilePackItemFileStreamDownloadInfo info)
            throws HandlerException;

    /**
     * 上传分析结果文件包条目文件。
     *
     * <p>
     * 如果上传的分析结果包不存在，则抛出异常。<br>
     * 如果上传的分析结果包条存在，则向文件包尾部追加文件包条目。
     *
     * @param info 上传信息。
     * @return 生成的分析结果文件包条目文件信息主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey uploadFile(AnalysisFilePackItemFileUploadInfo info) throws HandlerException;

    /**
     * 上传分析结果文件包条目文件流。
     *
     * <p>
     * 该方法会读取输入流中的数据作为文件内容，并将其追加到指定的分析结果包条的尾部。
     *
     * <p>
     * 如果上传的分析结果包不存在，则抛出异常。<br>
     * 如果上传的分析结果包条存在，则向文件包尾部追加文件包条目。
     *
     * <p>
     * 调用者有义务关闭 {@link AnalysisFilePackItemFileStreamUploadInfo} 中的输入流，
     * 即其中的 {@link AnalysisFilePackItemFileStreamUploadInfo#getContent()}。
     *
     * @param info 上传信息。
     * @return 生成的分析结果文件包条目文件信息主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey uploadFileStream(AnalysisFilePackItemFileStreamUploadInfo info) throws HandlerException;
}

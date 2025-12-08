package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 分析结果文件文件操作处理器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalysisFileFileOperateHandler extends Handler {

    /**
     * 下载分析结果文件。
     *
     * @param info 下载信息。
     * @return 分析结果文件。
     * @throws HandlerException 处理器异常。
     */
    AnalysisFileFile downloadFile(AnalysisFileFileDownloadInfo info) throws HandlerException;

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
    AnalysisFileFileStream downloadFileStream(AnalysisFileFileStreamDownloadInfo info) throws HandlerException;

    /**
     * 上传分析结果文件。
     *
     * <p>
     * 如果上传的分析结果文件文件不存在，则进行创建。<br>
     * 如果上传的分析结果文件文件存在，则进行替换。
     *
     * @param info 上传信息。
     * @return 上传结果。
     * @throws HandlerException 处理器异常。
     */
    AnalysisFileFileUploadResult uploadFile(AnalysisFileFileUploadInfo info) throws HandlerException;

    /**
     * 通过流的方式上传分析结果文件。
     *
     * <p>
     * 该方法会读取输入流中的数据作为文件内容，并将其写入到指定的分析结果文件文件中。
     *
     * <p>
     * 如果上传的分析结果文件文件不存在，则进行创建。<br>
     * 如果上传的分析结果文件文件存在，则进行替换。
     *
     * <p>
     * 调用者有义务关闭 {@link AnalysisFileFileStreamUploadInfo} 中的输入流，
     * 即其中的 {@link AnalysisFileFileStreamUploadInfo#getContent()}。
     *
     * @param info 上传信息。
     * @return 上传结果。
     * @throws HandlerException 处理器异常。
     */
    AnalysisFileFileUploadResult uploadFileStream(AnalysisFileFileStreamUploadInfo info) throws HandlerException;
}

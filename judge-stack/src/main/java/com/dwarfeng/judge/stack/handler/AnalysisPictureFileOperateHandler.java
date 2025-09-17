package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 分析结果图片文件操作处理器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalysisPictureFileOperateHandler extends Handler {

    /**
     * 下载分析结果图片。
     *
     * @param info 下载信息。
     * @return 分析结果图片。
     * @throws HandlerException 处理器异常。
     */
    AnalysisPictureFile downloadFile(AnalysisPictureFileDownloadInfo info) throws HandlerException;

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
    AnalysisPictureFileStream downloadFileStream(AnalysisPictureFileStreamDownloadInfo info) throws HandlerException;

    /**
     * 下载分析结果图片缩略图。
     *
     * @param info 下载信息。
     * @return 分析结果图片缩略图。
     * @throws HandlerException 处理器异常。
     */
    AnalysisPictureThumbnail downloadThumbnail(AnalysisPictureThumbnailDownloadInfo info) throws HandlerException;

    /**
     * 上传分析结果图片。
     *
     * <p>
     * 如果上传的分析结果图片文件不存在，则进行创建。<br>
     * 如果上传的分析结果图片文件存在，则进行替换。
     *
     * @param info 上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadFile(AnalysisPictureFileUploadInfo info) throws HandlerException;

    /**
     * 通过流的方式上传分析结果图片。
     *
     * <p>
     * 该方法会读取输入流中的数据作为文件内容，并将其写入到指定的分析结果图片文件中。
     *
     * <p>
     * 如果上传的分析结果图片文件不存在，则进行创建。<br>
     * 如果上传的分析结果图片文件存在，则进行替换。
     *
     * <p>
     * 调用者有义务关闭 {@link AnalysisPictureFileStreamUploadInfo} 中的输入流，
     * 即其中的 {@link AnalysisPictureFileStreamUploadInfo#getContent()}。
     *
     * @param info 上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadFileStream(AnalysisPictureFileStreamUploadInfo info) throws HandlerException;
}

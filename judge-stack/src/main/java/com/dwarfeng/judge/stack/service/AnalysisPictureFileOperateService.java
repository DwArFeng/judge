package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 分析结果图片文件操作服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalysisPictureFileOperateService extends Service {

    /**
     * 下载分析结果图片。
     *
     * @param info 下载信息。
     * @return 分析结果图片。
     * @throws ServiceException 服务异常。
     */
    AnalysisPictureFile downloadFile(AnalysisPictureFileDownloadInfo info) throws ServiceException;

    /**
     * 下载分析结果图片流。
     *
     * <p>
     * 如果返回的 {@link AnalysisPictureFileStream} 不为 <code>null</code>，则调用者有义务关闭
     * {@link AnalysisPictureFileStream} 中的输入流，即其中的 {@link AnalysisPictureFileStream#getContent()}。
     *
     * @param info 下载信息。
     * @return 分析结果图片流。
     * @throws ServiceException 服务异常。
     */
    AnalysisPictureFileStream downloadFileStream(AnalysisPictureFileStreamDownloadInfo info) throws ServiceException;

    /**
     * 下载分析结果图片缩略图。
     *
     * @param info 下载信息。
     * @return 分析结果图片缩略图。
     * @throws ServiceException 服务异常。
     */
    AnalysisPictureThumbnail downloadThumbnail(AnalysisPictureThumbnailDownloadInfo info) throws ServiceException;
}

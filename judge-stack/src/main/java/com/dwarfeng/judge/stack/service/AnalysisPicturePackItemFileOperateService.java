package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 分析结果图片包条目文件操作服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalysisPicturePackItemFileOperateService extends Service {

    /**
     * 下载分析结果包条目图片。
     *
     * @param info 下载信息。
     * @return 分析结果包条目图片。
     * @throws ServiceException 服务异常。
     */
    AnalysisPicturePackItemFile downloadFile(AnalysisPicturePackItemFileDownloadInfo info) throws ServiceException;

    /**
     * 下载分析结果包条目图片流。
     *
     * <p>
     * 如果返回的 {@link AnalysisPicturePackItemFileStream} 不为 <code>null</code>，则调用者有义务关闭
     * {@link AnalysisPicturePackItemFileStream} 中的输入流，
     * 即其中的 {@link AnalysisPicturePackItemFileStream#getContent()}。
     *
     * @param info 下载信息。
     * @return 分析结果包条目图片流。
     * @throws ServiceException 服务异常。
     */
    AnalysisPicturePackItemFileStream downloadFileStream(AnalysisPicturePackItemFileStreamDownloadInfo info)
            throws ServiceException;

    /**
     * 下载分析结果包条目图片缩略图。
     *
     * @param info 下载信息。
     * @return 分析结果包条目图片缩略图。
     * @throws ServiceException 服务异常。
     */
    AnalysisPicturePackItemThumbnail downloadThumbnail(AnalysisPicturePackItemThumbnailDownloadInfo info)
            throws ServiceException;
}

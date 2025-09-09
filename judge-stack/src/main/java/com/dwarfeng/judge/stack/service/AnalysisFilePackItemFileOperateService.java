package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackItemFile;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackItemFileDownloadInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackItemFileStream;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackItemFileStreamDownloadInfo;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 分析结果文件包条目文件操作服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalysisFilePackItemFileOperateService extends Service {

    /**
     * 下载分析结果包条目文件。
     *
     * @param info 下载信息。
     * @return 分析结果包条目文件。
     * @throws ServiceException 服务异常。
     */
    AnalysisFilePackItemFile downloadFile(AnalysisFilePackItemFileDownloadInfo info) throws ServiceException;

    /**
     * 下载分析结果包条目文件流。
     *
     * <p>
     * 如果返回的 {@link AnalysisFilePackItemFileStream} 不为 <code>null</code>，则调用者有义务关闭
     * {@link AnalysisFilePackItemFileStream} 中的输入流，
     * 即其中的 {@link AnalysisFilePackItemFileStream#getContent()}。
     *
     * @param info 下载信息。
     * @return 分析结果包条目文件流。
     * @throws ServiceException 服务异常。
     */
    AnalysisFilePackItemFileStream downloadFileStream(AnalysisFilePackItemFileStreamDownloadInfo info)
            throws ServiceException;
}

package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.dto.AnalysisFileFile;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFileFileDownloadInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFileFileStream;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFileFileStreamDownloadInfo;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 分析结果文件文件操作服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalysisFileFileOperateService extends Service {

    /**
     * 下载分析结果文件。
     *
     * @param info 下载信息。
     * @return 分析结果文件。
     * @throws ServiceException 服务异常。
     */
    AnalysisFileFile downloadFile(AnalysisFileFileDownloadInfo info) throws ServiceException;

    /**
     * 下载分析结果文件流。
     *
     * <p>
     * 如果返回的 {@link AnalysisFileFileStream} 不为 <code>null</code>，则调用者有义务关闭
     * {@link AnalysisFileFileStream} 中的输入流，即其中的 {@link AnalysisFileFileStream#getContent()}。
     *
     * @param info 下载信息。
     * @return 分析结果文件流。
     * @throws ServiceException 服务异常。
     */
    AnalysisFileFileStream downloadFileStream(AnalysisFileFileStreamDownloadInfo info) throws ServiceException;
}

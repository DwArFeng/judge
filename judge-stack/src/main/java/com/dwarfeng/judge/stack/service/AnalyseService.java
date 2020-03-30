package com.dwarfeng.judge.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 分析处理服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface AnalyseService extends Service {

    /**
     * 对指定的部件进行分析。
     *
     * @param sectionKey 部件的主键。
     * @throws ServiceException 服务异常。
     */
    void analyse(LongIdKey sectionKey) throws ServiceException;
}

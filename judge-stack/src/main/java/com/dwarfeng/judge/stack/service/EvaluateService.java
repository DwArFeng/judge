package com.dwarfeng.judge.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 评估服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface EvaluateService extends Service {

    /**
     * 对指定的部件进行评估。
     *
     * @param sectionKey 部件的主键。
     * @throws ServiceException 服务异常。
     */
    void evaluate(LongIdKey sectionKey) throws ServiceException;
}

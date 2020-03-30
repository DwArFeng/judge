package com.dwarfeng.judge.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 判断处理服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface JudgeProcessService extends Service {

    /**
     * 对指定的部件进行判断。
     *
     * @param sectionKey 部件的主键。
     * @throws ServiceException 服务异常。
     */
    void judge(LongIdKey sectionKey) throws ServiceException;
}

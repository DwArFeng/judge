package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.dto.ProvideLookupInfo;
import com.dwarfeng.judge.stack.bean.dto.ProvideLookupResult;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 提供 QoS 服务。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface ProvideQosService extends Service {

    /**
     * 查询提供数据。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws ServiceException 服务异常。
     */
    ProvideLookupResult lookup(ProvideLookupInfo info) throws ServiceException;
}

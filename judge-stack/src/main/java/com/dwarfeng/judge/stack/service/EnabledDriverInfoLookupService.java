package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

import java.util.List;

/**
 * 有效的驱动器信息查询服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface EnabledDriverInfoLookupService extends Service {

    /**
     * 获取指定的数据点所属的有效的驱动器信息。
     *
     * @param sectionKey 部件主键。
     * @return 指定的数据点所属的有效的驱动器信息。
     * @throws ServiceException 服务异常。
     */
    List<DriverInfo> getEnabledDriverInfos(LongIdKey sectionKey) throws ServiceException;
}

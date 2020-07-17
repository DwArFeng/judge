package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

import java.util.List;

/**
 * 有效的评价信息查询服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface EnabledJudgerInfoLookupService extends Service {

    /**
     * 获取指定的数据点所属的有效的判断器信息。
     *
     * @param sectionKey 部件主键。
     * @return 指定的数据点所属的有效的判断器信息。
     * @throws ServiceException 服务异常。
     */
    List<JudgerInfo> getEnabledJudgerInfos(LongIdKey sectionKey) throws ServiceException;
}

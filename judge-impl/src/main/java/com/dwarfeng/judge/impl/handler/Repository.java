package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dcti.stack.bean.dto.TimedValue;
import com.dwarfeng.judge.stack.exception.RepositoryException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;
import java.util.List;

/**
 * 仓库。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface Repository {

    /**
     * 返回仓库是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 仓库是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 获取仓库中的实时值。
     *
     * @param pointKey 指定的数据点主键。
     * @return 指定的数据点主键对应的仓库的实时值。
     * @throws RepositoryException 仓库异常。
     */
    TimedValue realtimeValue(LongIdKey pointKey) throws RepositoryException;

    /**
     * 获取仓库中的实时值。
     *
     * @param pointKey      指定的数据点主键。
     * @param processPreset 处理预设名称。
     * @param args          处理预设参数。
     * @return 指定的数据点主键对应的仓库的实时值。
     * @throws RepositoryException 仓库异常。
     */
    List<TimedValue> realtimeValue(LongIdKey pointKey, String processPreset, Object[] args) throws RepositoryException;

    /**
     * 获取仓库的持久化值。
     *
     * @param pointKey  指定的数据点主键。
     * @param startDate 起始时间。
     * @param endDate   结束时间。
     * @return 指定的数据点主键对应的仓库持久化值。
     * @throws RepositoryException 仓库异常。
     */
    List<TimedValue> persistenceValue(LongIdKey pointKey, Date startDate, Date endDate) throws RepositoryException;

    /**
     * 获取仓库的持久化值。
     *
     * @param pointKey      指定的数据点主键。
     * @param startDate     起始时间。
     * @param endDate       结束时间。
     * @param processPreset 处理预设名称。
     * @param args          处理预设参数。
     * @return 指定的数据点主键对应的仓库持久化值。
     * @throws RepositoryException 仓库异常。
     */
    List<TimedValue> persistenceValue(
            LongIdKey pointKey, Date startDate, Date endDate, String processPreset, Object[] args)
            throws RepositoryException;
}

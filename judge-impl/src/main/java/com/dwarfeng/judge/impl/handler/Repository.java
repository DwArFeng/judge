package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.TimedValue;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

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
     * @throws HandlerException 处理器异常。
     */
    TimedValue realtimeValue(LongIdKey pointKey) throws HandlerException;

    /**
     * 获取仓库中的实时值。
     *
     * @param pointKey      指定的数据点主键。
     * @param processPreset 处理预设名称。
     * @param args          处理预设参数。
     * @return 指定的数据点主键对应的仓库的实时值。
     * @throws HandlerException 处理器异常。
     */
    TimedValue realtimeValue(
            LongIdKey pointKey,
            String processPreset, Object[] args) throws HandlerException;

    /**
     * 获取仓库的持久化值。
     *
     * @param pointKey  指定的数据点主键。
     * @param startDate 起始时间。
     * @param endDate   结束时间。
     * @return 指定的数据点主键对应的仓库持久化值。
     * @throws HandlerException 处理器异常。
     */
    List<TimedValue> persistenceValue(
            LongIdKey pointKey, Date startDate, Date endDate) throws HandlerException;

    /**
     * 获取仓库的持久化值。
     *
     * @param pointKey      指定的数据点主键。
     * @param startDate     起始时间。
     * @param endDate       结束时间。
     * @param processPreset 处理预设名称。
     * @param args          处理预设参数。
     * @return 指定的数据点主键对应的仓库持久化值。
     * @throws HandlerException 处理器异常。
     */
    List<TimedValue> persistenceValue(
            LongIdKey pointKey, Date startDate, Date endDate,
            String processPreset, Object[] args) throws HandlerException;
}

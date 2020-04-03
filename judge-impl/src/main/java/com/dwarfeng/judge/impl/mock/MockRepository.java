package com.dwarfeng.judge.impl.mock;

import com.dwarfeng.dcti.stack.bean.dto.TimedValue;
import com.dwarfeng.judge.impl.handler.Repository;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 随机数仓库。
 *
 * <p>实时数据返回一个介于0.0 至 1.0的随机数，持久数据返回空值的仓库。</p>
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class MockRepository implements Repository {

    @Override
    public boolean supportType(String type) {
        return false;
    }

    @Override
    public TimedValue realtimeValue(LongIdKey pointKey) {
        return null;
    }

    @Override
    public List<TimedValue> realtimeValue(LongIdKey pointKey, String processPreset, Object[] args) {
        return null;
    }

    @Override
    public List<TimedValue> persistenceValue(LongIdKey pointKey, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<TimedValue> persistenceValue(
            LongIdKey pointKey, Date startDate, Date endDate, String processPreset, Object[] args) {
        return null;
    }
}

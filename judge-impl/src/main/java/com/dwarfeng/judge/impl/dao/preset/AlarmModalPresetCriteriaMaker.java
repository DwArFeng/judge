package com.dwarfeng.judge.impl.dao.preset;

import com.dwarfeng.judge.stack.service.AlarmModalMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AlarmModalPresetCriteriaMaker implements PresetCriteriaMaker {

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    public void makeCriteria(DetachedCriteria criteria, String preset, Object[] objs) {
        switch (preset) {
            case AlarmModalMaintainService.ALARMING_EQUALS:
                alarmingEquals(criteria, objs);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }

    private void alarmingEquals(DetachedCriteria criteria, Object[] objs) {
        try {
            boolean alarming = (boolean) objs[0];
            criteria.add(Restrictions.eq("alarming", alarming));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }
}

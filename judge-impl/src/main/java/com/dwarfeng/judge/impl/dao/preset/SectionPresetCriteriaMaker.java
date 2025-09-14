package com.dwarfeng.judge.impl.dao.preset;

import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SectionPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria criteria, String preset, Object[] objs) {
        switch (preset) {
            case SectionMaintainService.NAME_LIKE:
                nameLike(criteria, objs);
                break;
            case SectionMaintainService.ENABLED:
                enabled(criteria, objs);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }

    private void nameLike(DetachedCriteria criteria, Object[] objs) {
        try {
            String pattern = (String) objs[0];
            criteria.add(Restrictions.like("name", pattern, MatchMode.ANYWHERE));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void enabled(DetachedCriteria criteria, Object[] objs) {
        try {
            criteria.add(Restrictions.eq("enabled", true));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }
}

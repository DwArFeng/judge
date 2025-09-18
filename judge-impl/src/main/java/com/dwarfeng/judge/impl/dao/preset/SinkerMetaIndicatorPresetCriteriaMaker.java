package com.dwarfeng.judge.impl.dao.preset;

import com.dwarfeng.judge.stack.service.SinkerMetaIndicatorMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SinkerMetaIndicatorPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria criteria, String preset, Object[] objs) {
        switch (preset) {
            case SinkerMetaIndicatorMaintainService.SINKER_TYPE_EQ:
                sinkerTypeEq(criteria, objs);
                break;
            case SinkerMetaIndicatorMaintainService.SINKER_TYPE_EQ_META_ID_ASC:
                sinkerTypeEqMetaIdAsc(criteria, objs);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }

    private void sinkerTypeEq(DetachedCriteria criteria, Object[] objs) {
        try {
            String sinkerType = objs[0].toString();
            criteria.add(Restrictions.eq("sinkerTypeStringId", sinkerType));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void sinkerTypeEqMetaIdAsc(DetachedCriteria criteria, Object[] objs) {
        try {
            String sinkerType = objs[0].toString();
            criteria.add(Restrictions.eq("sinkerTypeStringId", sinkerType));
            criteria.addOrder(Order.asc("metaStringId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }
}

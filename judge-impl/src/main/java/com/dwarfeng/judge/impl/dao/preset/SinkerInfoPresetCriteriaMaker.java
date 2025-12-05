package com.dwarfeng.judge.impl.dao.preset;

import com.dwarfeng.judge.stack.service.SinkerInfoMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SinkerInfoPresetCriteriaMaker implements PresetCriteriaMaker {

    // 处理 SinkerInfoMaintainService.CHILD_FOR_KEYS_NIN 以向后兼容，故忽略相关警告。
    @SuppressWarnings("deprecation")
    @Override
    public void makeCriteria(DetachedCriteria criteria, String preset, Object[] objs) {
        switch (preset) {
            case SinkerInfoMaintainService.CHILD_FOR_KEYS_NIN:
            case SinkerInfoMaintainService.KEY_NOT_IN:
                keyNotIn(criteria, objs);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }

    private void keyNotIn(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("longId"));
            } else {
                // 此处类型转换安全应该由调用人员保证。
                @SuppressWarnings("unchecked")
                Collection<LongIdKey> keys = (Collection<LongIdKey>) objs[0];
                if (keys.isEmpty()) {
                    criteria.add(Restrictions.isNotNull("longId"));
                } else {
                    criteria.add(Restrictions.not(Restrictions.in(
                            "longId", keys.stream().map(LongIdKey::getLongId).collect(Collectors.toList())
                    )));
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

}

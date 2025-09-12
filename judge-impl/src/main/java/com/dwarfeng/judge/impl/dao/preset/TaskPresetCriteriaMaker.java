package com.dwarfeng.judge.impl.dao.preset;

import com.dwarfeng.judge.sdk.util.Constants;
import com.dwarfeng.judge.stack.service.TaskMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

@Component
public class TaskPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria criteria, String preset, Object[] objs) {
        switch (preset) {
            case TaskMaintainService.CHILD_FOR_SECTION:
                childForSection(criteria, objs);
                break;
            case TaskMaintainService.SHOULD_EXPIRE:
                shouldExpire(criteria, objs);
                break;
            case TaskMaintainService.SHOULD_DIE:
                shouldDie(criteria, objs);
                break;
            case TaskMaintainService.CREATE_DATE_DESC:
                createDateDesc(criteria, objs);
                break;
            case TaskMaintainService.CHILD_FOR_SECTION_CREATE_DATE_DESC:
                childForSectionCreateDateDesc(criteria, objs);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForSection(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("sectionLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("sectionLongId", longIdKey.getLongId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void shouldExpire(DetachedCriteria criteria, Object[] objs) {
        try {
            Date currentDate = new Date();
            criteria.add(Restrictions.in("status", Collections.singletonList(Constants.TASK_STATUS_CREATED)));
            criteria.add(Restrictions.le("shouldExpireDate", currentDate));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void shouldDie(DetachedCriteria criteria, Object[] objs) {
        try {
            Date currentDate = new Date();
            criteria.add(
                    Restrictions.in("status", Collections.singletonList(Constants.TASK_STATUS_PROCESSING))
            );
            criteria.add(Restrictions.le("shouldDieDate", currentDate));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void createDateDesc(DetachedCriteria criteria, Object[] objs) {
        try {
            criteria.addOrder(Order.desc("createDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void childForSectionCreateDateDesc(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("sectionLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("sectionLongId", longIdKey.getLongId()));
            }
            criteria.addOrder(Order.desc("createDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }
}

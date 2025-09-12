package com.dwarfeng.judge.impl.dao.preset;

import com.dwarfeng.judge.stack.service.AlarmHistoryMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Component
public class AlarmHistoryPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria criteria, String preset, Object[] objs) {
        switch (preset) {
            case AlarmHistoryMaintainService.CHILD_FOR_SECTION:
                childForSection(criteria, objs);
                break;
            case AlarmHistoryMaintainService.START_DATE_BETWEEN:
                startDateBetween(criteria, objs);
                break;
            case AlarmHistoryMaintainService.END_DATE_BETWEEN:
                endDateBetween(criteria, objs);
                break;
            case AlarmHistoryMaintainService.CHILD_FOR_SECTION_START_DATE_BETWEEN:
                childForSectionStartDateBetween(criteria, objs);
                break;
            case AlarmHistoryMaintainService.CHILD_FOR_SECTION_START_DATE_BETWEEN_RECENT:
                childForSectionStartDateBetweenRecent(criteria, objs);
                break;
            case AlarmHistoryMaintainService.CHILD_FOR_SECTION_END_DATE_BETWEEN:
                childForSectionEndDateBetween(criteria, objs);
                break;
            case AlarmHistoryMaintainService.CHILD_FOR_SECTION_END_DATE_BETWEEN_RECENT:
                childForSectionEndDateBetweenRecent(criteria, objs);
                break;
            case AlarmHistoryMaintainService.DURATION_GT:
                durationGt(criteria, objs);
                break;
            case AlarmHistoryMaintainService.DURATION_LT:
                durationLt(criteria, objs);
                break;
            case AlarmHistoryMaintainService.CHILD_FOR_SECTION_DURATION_GT:
                childForSectionDurationGt(criteria, objs);
                break;
            case AlarmHistoryMaintainService.CHILD_FOR_SECTION_DURATION_LT:
                childForSectionDurationLt(criteria, objs);
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

    private void startDateBetween(DetachedCriteria criteria, Object[] objs) {
        try {
            Date startDate = (Date) objs[0];
            Date endDate = (Date) objs[1];
            criteria.add(Restrictions.ge("startDate", startDate));
            criteria.add(Restrictions.lt("startDate", endDate));
            criteria.addOrder(Order.asc("startDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void endDateBetween(DetachedCriteria criteria, Object[] objs) {
        try {
            Date startDate = (Date) objs[0];
            Date endDate = (Date) objs[1];
            criteria.add(Restrictions.ge("endDate", startDate));
            criteria.add(Restrictions.lt("endDate", endDate));
            criteria.addOrder(Order.asc("endDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForSectionStartDateBetween(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("sectionLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("sectionLongId", longIdKey.getLongId()));
            }
            Date startDate = (Date) objs[1];
            Date endDate = (Date) objs[2];
            criteria.add(Restrictions.ge("startDate", startDate));
            criteria.add(Restrictions.lt("startDate", endDate));
            criteria.addOrder(Order.asc("startDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForSectionStartDateBetweenRecent(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("sectionLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("sectionLongId", longIdKey.getLongId()));
            }
            Date startDate = (Date) objs[1];
            Date endDate = (Date) objs[2];
            criteria.add(Restrictions.ge("startDate", startDate));
            criteria.add(Restrictions.lt("startDate", endDate));
            criteria.addOrder(Order.desc("startDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForSectionEndDateBetween(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("sectionLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("sectionLongId", longIdKey.getLongId()));
            }
            Date startDate = (Date) objs[1];
            Date endDate = (Date) objs[2];
            criteria.add(Restrictions.ge("endDate", startDate));
            criteria.add(Restrictions.lt("endDate", endDate));
            criteria.addOrder(Order.asc("endDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForSectionEndDateBetweenRecent(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("sectionLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("sectionLongId", longIdKey.getLongId()));
            }
            Date startDate = (Date) objs[1];
            Date endDate = (Date) objs[2];
            criteria.add(Restrictions.ge("endDate", startDate));
            criteria.add(Restrictions.lt("endDate", endDate));
            criteria.addOrder(Order.desc("endDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void durationGt(DetachedCriteria criteria, Object[] objs) {
        try {
            long duration = (long) objs[0];
            criteria.add(Restrictions.gt("duration", duration));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void durationLt(DetachedCriteria criteria, Object[] objs) {
        try {
            long duration = (long) objs[0];
            criteria.add(Restrictions.lt("duration", duration));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForSectionDurationGt(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("sectionLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("sectionLongId", longIdKey.getLongId()));
            }
            long duration = (long) objs[1];
            criteria.add(Restrictions.lt("duration", duration));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForSectionDurationLt(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("sectionLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("sectionLongId", longIdKey.getLongId()));
            }
            long duration = (long) objs[1];
            criteria.add(Restrictions.lt("duration", duration));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }
}

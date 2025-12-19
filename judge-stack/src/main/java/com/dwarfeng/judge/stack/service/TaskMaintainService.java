package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.Task;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 任务维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface TaskMaintainService extends BatchCrudService<LongIdKey, Task>,
        EntireLookupService<Task>, PresetLookupService<Task> {

    String CHILD_FOR_SECTION = "child_for_section";

    String SHOULD_EXPIRE = "should_expire";
    String SHOULD_DIE = "should_die";

    String CREATE_DATE_DESC = "create_date_desc";
    String CHILD_FOR_SECTION_CREATE_DATE_DESC = "child_for_section_create_date_desc";

    /**
     * 获取将要被清理的任务实体。
     *
     * <p>
     * 返回 <code>endedDate（结束日期）</code> 早于指定的日期的实体，
     * 且 <code>status（状态）</code> 在 <code>任务完成, 任务失败, 任务过期, 任务死亡</code> 中，
     * 且按照 <code>endedDate（结束日期）</code> 升序排列。
     *
     * <p>
     * 参数列表：
     * <ol>
     *     <li>Data 指定的日期。</li>
     * </ol>
     * 返回的数据按照 <code>endedDate（结束日期）</code> 升序排列。
     *
     * @since 2.2.0
     */
    String TO_PURGED = "to_purged";

    /**
     * @since 2.3.2
     */
    String STATUS_EQ = "status_eq";

    /**
     * @since 2.3.2
     */
    String STATUS_EQ_CREATE_DATE_DESC = "status_eq_create_date_desc";
}

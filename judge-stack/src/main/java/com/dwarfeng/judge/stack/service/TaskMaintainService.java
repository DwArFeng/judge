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
 * @since 2.0.0
 */
public interface TaskMaintainService extends BatchCrudService<LongIdKey, Task>,
        EntireLookupService<Task>, PresetLookupService<Task> {

    String CHILD_FOR_SECTION = "child_for_section";

    String SHOULD_EXPIRE = "should_expire";
    String SHOULD_DIE = "should_die";

    String CREATE_DATE_DESC = "create_date_desc";
    String CHILD_FOR_SECTION_CREATE_DATE_DESC = "child_for_section_create_date_desc";
}

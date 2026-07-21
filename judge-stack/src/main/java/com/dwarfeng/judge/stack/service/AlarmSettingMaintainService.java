package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmSetting;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 报警设置维护服务。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface AlarmSettingMaintainService extends BatchCrudService<LongIdKey, AlarmSetting>,
        EntireLookupService<AlarmSetting>, PresetLookupService<AlarmSetting> {

    // region 预设查询 - 级联

    String CHILD_FOR_SECTION = "child_for_section";

    // endregion

    // region 预设查询 - 业务逻辑

    String CHILD_FOR_SECTION_DATA_ID_ASC_THRESHOLD_DESC = "child_for_section_data_id_asc_threshold_desc";

    // endregion
}

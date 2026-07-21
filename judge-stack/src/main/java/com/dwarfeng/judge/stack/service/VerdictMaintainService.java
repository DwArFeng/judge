package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.Verdict;
import com.dwarfeng.judge.stack.bean.key.VerdictKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 部件裁决结果维护服务。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface VerdictMaintainService extends BatchCrudService<VerdictKey, Verdict>, EntireLookupService<Verdict>,
        PresetLookupService<Verdict> {

    // region 预设查询 - 级联

    String CHILD_FOR_SECTION = "child_for_section";

    // endregion
}

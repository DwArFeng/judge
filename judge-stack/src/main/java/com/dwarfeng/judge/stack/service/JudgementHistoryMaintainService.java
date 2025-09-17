package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.JudgementHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 判断结果历史维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface JudgementHistoryMaintainService extends BatchCrudService<LongIdKey, JudgementHistory>,
        EntireLookupService<JudgementHistory>, PresetLookupService<JudgementHistory> {

    String CHILD_FOR_SECTION = "child_for_section";
    String CHILD_FOR_JUDGER_INFO = "child_for_judger_info";

    String HAPPENED_DATE_BETWEEN = "happened_date_between";

    String CHILD_FOR_SECTION_HAPPENED_DATE_BETWEEN = "child_for_section_happened_date_between";

    /**
     * CHILD_FOR_SECTION_HAPPENED_DATE_BETWEEN_RECENT
     *
     * <p>
     * 属于某个部件，发生时间处于某个范围，并按照发生时间降序排序。
     *
     * <p>
     * 参数数组：
     * <table>
     *     <tr>
     *         <th>索引</th>
     *         <th>类型</th>
     *         <th>说明</th>
     *     </tr>
     *     <tr>
     *         <td>0</td>
     *         <td>{@link LongIdKey}</td>
     *         <td>部件的主键</td>
     *     </tr>
     *     <tr>
     *         <td>1</td>
     *         <td>{@link java.util.Date}</td>
     *         <td>发生时间区间的第 1 个值</td>
     *     </tr>
     *     <tr>
     *         <td>2</td>
     *         <td>{@link java.util.Date}</td>
     *         <td>发生时间区间的第 2 个值</td>
     *     </tr>
     * </table>
     */
    String CHILD_FOR_SECTION_HAPPENED_DATE_BETWEEN_RECENT = "child_for_section_happened_date_between_recent";
}

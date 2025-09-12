package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 报警历史维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AlarmHistoryMaintainService extends BatchCrudService<LongIdKey, AlarmHistory>,
        EntireLookupService<AlarmHistory>, PresetLookupService<AlarmHistory> {

    String CHILD_FOR_SECTION = "child_for_section";

    String START_DATE_BETWEEN = "start_date_between";
    String END_DATE_BETWEEN = "end_date_between";

    String CHILD_FOR_SECTION_START_DATE_BETWEEN = "child_for_section_start_date_between";

    /**
     * CHILD_FOR_SECTION_START_DATE_BETWEEN_RECENT
     *
     * <p>
     * 属于某个部件，起始时间处于某个范围，并按照起始时间降序排序。
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
     *         <td>起始时间区间的第 1 个值</td>
     *     </tr>
     *     <tr>
     *         <td>2</td>
     *         <td>{@link java.util.Date}</td>
     *         <td>起始时间区间的第 2 个值</td>
     *     </tr>
     * </table>
     */
    String CHILD_FOR_SECTION_START_DATE_BETWEEN_RECENT = "child_for_section_start_date_between_recent";

    String CHILD_FOR_SECTION_END_DATE_BETWEEN = "child_for_section_end_date_between";

    /**
     * CHILD_FOR_SECTION_END_DATE_BETWEEN_RECENT
     *
     * <p>
     * 属于某个部件，结束时间处于某个范围，并按照结束时间降序排序。
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
     *         <td>结束时间区间的第 1 个值</td>
     *     </tr>
     *     <tr>
     *         <td>2</td>
     *         <td>{@link java.util.Date}</td>
     *         <td>结束时间区间的第 2 个值</td>
     *     </tr>
     * </table>
     */
    String CHILD_FOR_SECTION_END_DATE_BETWEEN_RECENT = "child_for_section_end_date_between_recent";

    String DURATION_GT = "duration_gt";
    String DURATION_LT = "duration_lt";

    String CHILD_FOR_SECTION_DURATION_GT = "child_for_section_duration_gt";
    String CHILD_FOR_SECTION_DURATION_LT = "child_for_section_duration_lt";

}

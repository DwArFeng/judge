package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerRelation;
import com.dwarfeng.judge.stack.bean.key.SinkerRelationKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 下沉器关联信息维护服务。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerRelationMaintainService extends BatchCrudService<SinkerRelationKey, SinkerRelation>,
        EntireLookupService<SinkerRelation>, PresetLookupService<SinkerRelation> {

    String CHILD_FOR_SECTION = "child_for_section";
    String CHILD_FOR_SINKER_INFO = "child_for_sinker_info";
    String CHILD_FOR_SECTION_ENABLED = "child_for_section_enabled";
    String CHILD_FOR_SECTION_ENABLED_SECTION_ENABLED = "child_for_section_enabled_section_enabled";
    String CHILD_FOR_SINKER_INFO_ENABLED_SECTION_ENABLED = "child_for_sinker_info_enabled_section_enabled";

    /**
     * 属于部件且满足绑定条件。
     *
     * <p>
     * 绑定条件是指:
     * <ol>
     *     <li>下沉器关联信息本身启用</li>
     *     <li>关联的部件启用</li>
     *     <li>关联的下沉器信息启用</li>
     * </ol>
     *
     * <p>
     * 该预设对应的参数数组为:
     * <table>
     *     <tr>
     *         <th>索引</th>
     *         <th>类型</th>
     *         <th>描述</th>
     *     </tr>
     *     <tr>
     *         <td>0</td>
     *         <td>LongIdKey</td>
     *         <td>部件主键</td>
     *     </tr>
     * </table>
     *
     * @since 2.2.1
     */
    String CHILD_FOR_SECTION_BINDING = "child_for_section_binding";

    /**
     * 属于下沉器信息且满足绑定条件。
     *
     * <p>
     * 绑定条件是指:
     * <ol>
     *     <li>下沉器关联信息本身启用</li>
     *     <li>关联的部件启用</li>
     *     <li>关联的下沉器信息启用</li>
     * </ol>
     *
     * <p>
     * 该预设对应的参数数组为:
     * <table>
     *     <tr>
     *         <th>索引</th>
     *         <th>类型</th>
     *         <th>描述</th>
     *     </tr>
     *     <tr>
     *         <td>0</td>
     *         <td>LongIdKey</td>
     *         <td>下沉器信息主键</td>
     *     </tr>
     * </table>
     *
     * @since 2.2.1
     */
    String CHILD_FOR_SINKER_INFO_BINDING = "child_for_sinker_info_binding";
}

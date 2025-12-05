package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 下沉器信息维护服务。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerInfoMaintainService extends BatchCrudService<LongIdKey, SinkerInfo>,
        EntireLookupService<SinkerInfo>, PresetLookupService<SinkerInfo> {

    /**
     * 键不在列表中。
     *
     * <p>
     * 对象数组明细:
     * <table>
     *     <tr>
     *         <th>索引</th>
     *         <th>类型</th>
     *         <th>说明</th>
     *     </tr>
     *     <tr>
     *         <td>0</td>
     *         <td>Collection&lt;LongIdKey&gt;</td>
     *         <td>键集合</td>
     *     </tr>
     * </table>
     *
     * @see #KEY_NOT_IN
     * @deprecated 该常量命名不符合规范，使用 {@link #KEY_NOT_IN} 代替。
     */
    // SinkerInfoPresetCriteriaMaker 中仍在使用该常量，以向后兼容，故忽略相关警告。
    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    String CHILD_FOR_KEYS_NIN = "child_for_keys_nin";

    /**
     * 键不在列表中。
     *
     * <p>
     * 对象数组明细:
     * <table>
     *     <tr>
     *         <th>索引</th>
     *         <th>类型</th>
     *         <th>说明</th>
     *     </tr>
     *     <tr>
     *         <td>0</td>
     *         <td>Collection&lt;LongIdKey&gt;</td>
     *         <td>键集合</td>
     *     </tr>
     * </table>
     */
    String KEY_NOT_IN = "key_not_in";
}

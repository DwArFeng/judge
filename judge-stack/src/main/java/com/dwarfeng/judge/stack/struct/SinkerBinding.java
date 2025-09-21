package com.dwarfeng.judge.stack.struct;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Map;

/**
 * 下沉器绑定本地缓存。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public final class SinkerBinding {

    /**
     * 映射。
     *
     * <p>
     * 该字段的类型是 <code>Map&lt;LongIdKey, Map&lt;String, SinkerMetaInfo&gt;&gt;</code>，其中:
     * <table>
     *     <tr>
     *         <th></th>
     *         <th>类型</th>
     *         <th>含义</th>
     *     </tr>
     *     <tr>
     *         <td>键</td>
     *         <td>LongIdKey</td>
     *         <td>部件主键</td>
     *     </tr>
     *     <tr>
     *         <td>值</td>
     *         <td>Map&lt;String, SinkerMetaInfo&gt;</td>
     *         <td>下沉器标识与下沉器元信息的映射，详见下文</td>
     *     </tr>
     * </table>
     *
     * <p>
     * 映射中的值的类型是 <code>Map&lt;String, SinkerMetaInfo&gt;</code>，其中:
     * <table>
     *     <tr>
     *         <th></th>
     *         <th>类型</th>
     *         <th>含义</th>
     *     </tr>
     *     <tr>
     *         <td>键</td>
     *         <td>String</td>
     *         <td>下沉器的元数据 ID</td>
     *     </tr>
     *     <tr>
     *         <td>值</td>
     *         <td>SinkerMetaInfo</td>
     *         <td>下沉器元信息</td>
     *     </tr>
     * </table>
     *
     * @see SinkerMetaInfo
     */
    private final Map<LongIdKey, Map<String, SinkerMetaInfo>> map;

    public SinkerBinding(Map<LongIdKey, Map<String, SinkerMetaInfo>> map) {
        this.map = map;
    }

    public Map<LongIdKey, Map<String, SinkerMetaInfo>> getMap() {
        return map;
    }

    @Override
    public String toString() {
        return "SinkerBinding{" +
                "map=" + map +
                '}';
    }
}

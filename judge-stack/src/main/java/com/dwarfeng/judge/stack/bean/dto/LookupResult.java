package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;
import java.util.Map;

/**
 * 查询结果。
 *
 * @author DwArFeng
 * @since 2.3.0
 */
public class LookupResult implements Dto {

    private static final long serialVersionUID = 6293238618779956132L;

    /**
     * 数据列表。
     *
     * <p>
     * 数据列表中的每一个元素都是一个映射，表示一条数据记录。
     * 映射的键是字符串，表示数据字段的名称；映射的值是对象，表示数据字段的值。<br>
     * 例如，对于一个查询历史数据的提供器，返回的列表中的每一个映射可能包含以下键值对：
     * <table>
     *     <tr>
     *         <th>键</th>
     *         <th>值类型</th>
     *         <th>描述</th>
     *     </tr>
     *     <tr>
     *         <td>timestamp</td>
     *         <td>Long</td>
     *         <td>时间戳，表示数据记录的时间。</td>
     *     </tr>
     *     <tr>
     *         <td>value</td>
     *         <td>Double</td>
     *         <td>数值，表示数据记录的数值。</td>
     *     </tr>
     * </table>
     *
     * <p>
     * 返回的数据列表每个映射中键对应的值必须是简单对象，例如字符串、数字等。<br>
     * 禁止使用无法序列化的复杂对象，例如数据库连接、文件句柄等。
     *
     * <p>
     * 该值不允许为 <code>null</code>，如果没有查询到数据，应该返回空列表。
     */
    private List<Map<String, Object>> datas;

    /**
     * 元数据。
     *
     * <p>
     * 元数据是一个映射，用于描述数据列表，或者对查询结果做补充说明。<br>
     * 例如，对于一个查询历史数据的提供器，假设其支持分页查询，那么元数据可能包含以下键值对：
     * <table>
     *     <tr>
     *         <th>键</th>
     *         <th>值类型</th>
     *         <th>描述</th>
     *     </tr>
     *     <tr>
     *         <td>currentPage</td>
     *         <td>int</td>
     *         <td>当前的页数。</td>
     *     </tr>
     *     <tr>
     *         <td>totalPages</td>
     *         <td>int</td>
     *         <td>总共的页数。</td>
     *     </tr>
     *     <tr>
     *         <td>rows</td>
     *         <td>int</td>
     *         <td>每页返回的行数。</td>
     *     </tr>
     *     <tr>
     *         <td>count</td>
     *         <td>long</td>
     *         <td>总共数量。</td>
     *     </tr>
     * </table>
     *
     * <p>
     * 元数据映射中键对应的值必须是简单对象，例如字符串、数字等。<br>
     * 禁止使用无法序列化的复杂对象，例如数据库连接、文件句柄等。
     *
     * <p>
     * 该值不允许为 <code>null</code>，如果没有元数据，应该返回空映射。
     */
    private Map<String, Object> meta;

    public LookupResult() {
    }

    public LookupResult(List<Map<String, Object>> datas, Map<String, Object> meta) {
        this.datas = datas;
        this.meta = meta;
    }

    public List<Map<String, Object>> getDatas() {
        return datas;
    }

    public void setDatas(List<Map<String, Object>> datas) {
        this.datas = datas;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "LookupResult{" +
                "datas=" + datas +
                ", meta=" + meta +
                '}';
    }
}

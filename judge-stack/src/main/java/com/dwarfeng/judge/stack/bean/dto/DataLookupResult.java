package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;
import java.util.Map;

/**
 * 提供结果。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class DataLookupResult implements Dto {

    private static final long serialVersionUID = -5780722216383469528L;

    /**
     * 结果列表。
     *
     * <p>
     * 结果列表中的每一个元素都是一个映射，表示一条数据记录。
     * 映射的键是字符串，表示数据字段的名称；映射的值是对象，表示数据字段的值。<br>
     * 例如，对于一个提供历史数据的提供器，返回的列表中的每一个映射可能包含以下键值对：
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
     * 结果列表每个映射中键对应的值必须是简单对象，例如字符串、数字等。<br>
     * 禁止使用无法序列化的复杂对象，例如数据库连接、文件句柄等。
     */
    private List<Map<String, Object>> results;

    public DataLookupResult() {
    }

    public DataLookupResult(List<Map<String, Object>> results) {
        this.results = results;
    }

    public List<Map<String, Object>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, Object>> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "DataLookupResult{" +
                "results=" + results +
                '}';
    }
}

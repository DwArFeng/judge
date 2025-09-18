package com.dwarfeng.judge.impl.handler.provider.groovy;

import java.util.List;
import java.util.Map;

/**
 * Groovy 处理器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface Processor {

    /**
     * 打开提供器会话。
     *
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    void openSession() throws Exception;

    /**
     * 关闭提供器会话。
     *
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    void closeSession() throws Exception;

    /**
     * 提供数据。
     *
     * <p>
     * 方法中的 <code>preset</code> 参数用于区分数据的提供方式。<br>
     * 例如一个提供器可以提供常规历史数据和报警历史数据，则这两种数据应该用不同的 <code>preset</code> 加以区分。
     *
     * <p>
     * 方法中的 <code>objs</code> 参数用于指定预设的参数，提供器根据参数提供对应的数据。<br>
     * 例如一个提供器可以根据时间范围提供历史数据，则 <code>objs</code> 中应该包含两个参数，分别表示开始时间和结束时间。
     *
     * <p>
     * 方法中的 <code>objs</code> 参数中的每一个对象必须是简单对象，例如字符串、数字等。<br>
     * 禁止使用无法序列化的复杂对象，例如数据库连接、文件句柄等。
     *
     * <p>
     * 提供器应明确地在描述或文档中说明每个预设所需的参数类型和顺序。<br>
     * 如果提供器无法识别指定的预设，或者提供的参数不符合预设的要求，则应抛出异常。
     *
     * <p>
     * 返回的数据列表中的每一个元素都是一个映射，表示一条数据记录。
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
     * 返回的数据列表每个映射中键对应的值必须是简单对象，例如字符串、数字等。<br>
     * 禁止使用无法序列化的复杂对象，例如数据库连接、文件句柄等。
     *
     * @param preset 指定的预设名称。
     * @param objs   预设对应的对象数组。
     * @return 提供的结果列表。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    List<Map<String, Object>> provide(String preset, Object[] objs) throws Exception;
}

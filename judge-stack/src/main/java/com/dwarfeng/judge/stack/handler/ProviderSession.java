package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.exception.ProviderSessionException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 提供器会话。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface ProviderSession {

    /**
     * 打开提供器会话。
     *
     * @throws ProviderSessionException 提供器会话异常。
     */
    void openSession() throws ProviderSessionException;

    /**
     * 关闭提供器会话。
     *
     * @throws ProviderSessionException 提供器会话异常。
     */
    void closeSession() throws ProviderSessionException;

    /**
     * 查询。
     *
     * <p>
     * 有关参数和返回值的详细描述，请参阅相关对象的文档注释。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws ProviderSessionException 提供器会话异常。
     * @since 2.6.0
     */
    LookupResult lookup(LookupInfo info) throws ProviderSessionException;

    /**
     * 查询信息。
     *
     * @author DwArFeng
     * @since 2.6.0
     */
    final class LookupInfo {

        /**
         * 预设。
         *
         * <p>
         * <code>preset</code> 参数用于区分数据的查询方式。<br>
         * 例如一个提供器可以查询常规历史数据和报警历史数据，则这两种数据应该用不同的 <code>preset</code> 加以区分。
         */
        private final String preset;

        /**
         * 预设对应的对象数组。
         *
         * <p>
         * <code>objs</code> 参数用于指定预设的参数，提供器根据参数查询对应的数据。<br>
         * 例如一个提供器可以根据时间范围分页查询历史数据，则 <code>objs</code> 可能为以下值:
         * <table>
         *     <tr>
         *         <th>索引</th>
         *         <th>类型</th>
         *         <th>描述</th>
         *     </tr>
         *     <tr>
         *         <td>0</td>
         *         <td>Date</td>
         *         <td>起始时间</td>
         *     </tr>
         *     <tr>
         *         <td>1</td>
         *         <td>Date</td>
         *         <td>结束时间</td>
         *     </tr>
         *     <tr>
         *         <td>2</td>
         *         <td>int</td>
         *         <td>查询的页数</td>
         *     </tr>
         *     <tr>
         *         <td>3</td>
         *         <td>int</td>
         *         <td>每页返回的行数</td>
         *     </tr>
         * </table>
         *
         * <p>
         * <code>objs</code> 参数中的每一个对象必须是简单对象，例如字符串、数字等。<br>
         * 禁止使用无法序列化的复杂对象，例如数据库连接、文件句柄等。
         */
        private final Object[] objs;

        public LookupInfo(String preset, Object[] objs) {
            this.preset = preset;
            this.objs = objs;
        }

        public String getPreset() {
            return preset;
        }

        public Object[] getObjs() {
            return objs;
        }

        @Override
        public String toString() {
            return "LookupInfo{" +
                    "preset='" + preset + '\'' +
                    ", objs=" + Arrays.toString(objs) +
                    '}';
        }
    }

    /**
     * 查询结果。
     *
     * @author DwArFeng
     * @since 2.6.0
     */
    final class LookupResult {

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
        private final List<Map<String, Object>> datas;

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
        private final Map<String, Object> meta;

        public LookupResult(List<Map<String, Object>> datas, Map<String, Object> meta) {
            this.datas = datas;
            this.meta = meta;
        }

        public List<Map<String, Object>> getDatas() {
            return datas;
        }

        public Map<String, Object> getMeta() {
            return meta;
        }

        @Override
        public String toString() {
            return "LookupResult{" +
                    "datas=" + datas +
                    ", meta=" + meta +
                    '}';
        }
    }
}

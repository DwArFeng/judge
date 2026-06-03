package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.exception.AdapterException;

import java.util.Arrays;

/**
 * 适配器。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.3.0
 */
public interface Adapter {

    /**
     * 适配。
     *
     * <p>
     * 该方法被调用时，会按照预定的逻辑对提供的数据进行适配，并把适配的结果进行返回，
     *
     * @param info 适配信息。
     * @return 适配结果。
     * @throws AdapterException 适配器异常。
     * @since 2.6.0
     */
    AdaptResult adapt(AdaptInfo info) throws AdapterException;

    /**
     * 适配信息。
     *
     * @author DwArFeng
     * @since 2.6.0
     */
    final class AdaptInfo {

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

        public AdaptInfo(String preset, Object[] objs) {
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
            return "AdaptInfo{" +
                    "preset='" + preset + '\'' +
                    ", objs=" + Arrays.toString(objs) +
                    '}';
        }
    }

    /**
     * 适配结果。
     *
     * @author DwArFeng
     * @since 2.6.0
     */
    final class AdaptResult {

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

        public AdaptResult(String preset, Object[] objs) {
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
            return "AdaptResult{" +
                    "preset='" + preset + '\'' +
                    ", objs=" + Arrays.toString(objs) +
                    '}';
        }
    }
}

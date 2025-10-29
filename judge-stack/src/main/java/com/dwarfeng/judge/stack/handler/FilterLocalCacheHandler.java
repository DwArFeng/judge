package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.handler.LocalCacheHandler;

/**
 * 过滤器本地缓存处理器。
 *
 * <p>
 * 该处理器实现了 <code>LocalCacheHandler&lt;LongIdKey, Filter&gt;</code> 接口，
 * 用于处理与驱动相关的本地缓存。<br>
 * 对于 <code>LocalCacheHandler&lt;K, V&gt;</code> 接口中的泛型参数:
 * <table>
 *     <tr>
 *         <th></th>
 *         <th>类型</th>
 *         <th>含义</th>
 *     </tr>
 *     <tr>
 *         <td>K</td>
 *         <td>LongIdKey</td>
 *         <td>过滤器信息主键</td>
 *     </tr>
 *     <tr>
 *         <td>V</td>
 *         <td>Filter</td>
 *         <td>过滤器</td>
 *     </tr>
 * </table>
 *
 * @author wangyc
 * @see LocalCacheHandler
 * @see Filter
 * @since 2.3.0
 */
public interface FilterLocalCacheHandler extends LocalCacheHandler<LongIdKey, Filter> {
}

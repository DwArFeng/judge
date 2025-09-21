package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.handler.LocalCacheHandler;

/**
 * 提供器本地缓存处理器。
 *
 * <p>
 * 该处理器实现了 <code>LocalCacheHandler&lt;LongIdKey, Provider&gt;</code> 接口，
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
 *         <td>提供器信息主键</td>
 *     </tr>
 *     <tr>
 *         <td>V</td>
 *         <td>Provider</td>
 *         <td>提供器</td>
 *     </tr>
 * </table>
 *
 * @author DwArFeng
 * @see LocalCacheHandler
 * @see Provider
 * @since 2.1.0-beta
 */
public interface ProviderLocalCacheHandler extends LocalCacheHandler<LongIdKey, Provider> {
}

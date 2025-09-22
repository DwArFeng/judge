package groovy

import com.dwarfeng.judge.impl.handler.provider.groovy.Processor
import com.dwarfeng.judge.stack.bean.dto.LookupInfo
import com.dwarfeng.judge.stack.bean.dto.LookupResult

/**
 * 提供随机数据，以提供示例。
 *
 * <p>
 * 提供方法的行为如下：<br>
 * 无论 <code>preset</code> 和 <code>objs</code> 的值为何值，都有：
 * <ul>
 *     <li>
 *         <code>datas</code> 为一个长度为 <code>DATA_LENGTH</code> 的列表，
 *         列表中的每个元素均为一个包含如下键值对的映射：
 *         <table>
 *             <tr>
 *                 <th>键</th>
 *                 <th>值类型</th>
 *                 <th>描述</th>
 *             </tr>
 *             <tr>
 *                 <td>timestamp</td>
 *                 <td>Long</td>
 *                 <td>数据发生的时间戳。</td>
 *             </tr>
 *             <tr>
 *                 <td>value</td>
 *                 <td>Double</td>
 *                 <td>数值，符合高斯分布。</td>
 *             </tr>
 *         </table>
 *         返回的数据对应的 <code>timestamp</code> 从低到高，数据之间的跨距为 <code>DATA_EXPAND</code> 毫秒，
 *         最后一个数据的 <code>timestamp</code> 为方法调用时对应的时间戳。
 *     </li>
 *     <li>
 *         <code>meta</code> 为一个包含如下键值对的映射：
 *         <table>
 *             <tr>
 *                 <th>键</th>
 *                 <th>值类型</th>
 *                 <th>描述</th>
 *             </tr>
 *             <tr>
 *                 <td>length</td>
 *                 <td>int</td>
 *                 <td>数据的数量。</td>
 *             </tr>
 *             <tr>
 *                 <td>expand</td>
 *                 <td>long</td>
 *                 <td>两个数据点时间戳的毫秒跨距。</td>
 *             </tr>
 *             <tr>
 *                 <td>happenedTimestamp</td>
 *                 <td>long</td>
 *                 <td>该方法调用时的时间戳。</td>
 *             </tr>
 *         </table>
 *     </li>
 * </ul>
 *
 * @author Dwarfeng
 * @since 2.1.0-beta
 */
@SuppressWarnings(['GrPackage', 'unused'])
class ExampleProviderProcessor implements Processor {

    public static final int DATA_LENGTH = 60
    public static final long DATA_EXPAND = 1000

    @Override
    void openSession() throws Exception {
    }

    @Override
    void closeSession() throws Exception {
    }

    @Override
    LookupResult lookup(LookupInfo info) throws Exception {
        List<Map<String, Object>> datas = new ArrayList<>()
        long currentTimeMillis = System.currentTimeMillis()
        Random random = new Random()
        for (int i = 0; i < DATA_LENGTH; i++) {
            Map<String, Object> map = new HashMap<>()
            map.put("timestamp", currentTimeMillis - (DATA_LENGTH - i - 1) * DATA_EXPAND)
            map.put("value", random.nextGaussian())
            datas.add(map)
        }
        Map<String, Object> meta = new HashMap<>()
        meta.put("length", DATA_LENGTH)
        meta.put("expand", DATA_EXPAND)
        meta.put("happenedTimestamp", currentTimeMillis)
        return new LookupResult(datas, meta)
    }
}

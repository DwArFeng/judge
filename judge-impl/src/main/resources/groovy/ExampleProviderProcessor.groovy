package groovy

import com.dwarfeng.judge.impl.handler.provider.groovy.Processor

/**
 * 提供随机数据，以提供示例。
 *
 * <p>
 * 提供方法的行为如下：<br>
 * 无论 <code>preset</code> 和 <code>objs</code> 的值为何，均返回一个长度为 <code>DATA_LENGTH</code> 的列表，
 * 列表中的每个元素均为一个包含如下键值对的映射：
 * <table>
 *     <tr>
 *         <th>键</th>
 *         <th>值类型</th>
 *         <th>描述</th>
 *     </tr>
 *     <tr>
 *         <td>timestamp</td>
 *         <td>Long</td>
 *         <td>数据发生的时间戳。</td>
 *     </tr>
 *     <tr>
 *         <td>value</td>
 *         <td>Double</td>
 *         <td>数值，符合高斯分布。</td>
 *     </tr>
 * </table>
 * 返回的数据对应的 <code>timestamp</code> 从低到高，数据之间的跨距为 <code>DATA_EXPAND</code> 毫秒，
 * 最后一个数据的 <code>timestamp</code> 为方法调用时对应的时间戳。
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
    List<Map<String, Object>> provide(String preset, Object[] objs) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>()
        long currentTimeMillis = System.currentTimeMillis()
        Random random = new Random()
        for (int i = 0; i < DATA_LENGTH; i++) {
            Map<String, Object> map = new HashMap<>()
            map.put("timestamp", currentTimeMillis - (DATA_LENGTH - i - 1) * DATA_EXPAND)
            map.put("value", random.nextGaussian())
            list.add(map)
        }
        return list
    }
}

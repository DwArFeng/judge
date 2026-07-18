# Preset Provider Implements - 预设提供器实现

## 说明

本文档详细说明了本项目内置的所有提供器。

所有内置提供器的代码均位于 `com.dwarfeng.judge.impl.handler.provider` 包的下属包中，
您可以通过查看该包下的所有类来了解本项目内置的所有提供器。

项目中所有的内置提供器如下：

| 类型     | 名称           | 说明                                         |
|----------|----------------|----------------------------------------------|
| `groovy` | GroovyProvider | 通过自定义 Groovy 脚本提供数据。             |
| `date`   | DateProvider   | 根据固定预设生成时间点、时间范围和时间序列。 |

`ProviderInfo.param` 是提供器的构造参数，由具体提供器类型解析。
查询时使用的 `preset` 和 `objs` 属于 `ProviderSession.LookupInfo`，用于选择查询方式并传递本次查询的参数。

以下各实现的示例参数可作为 `ProviderInfo.param` 或 `ProviderSession.LookupInfo` 的参考。
实际使用时，需要结合分析器逻辑、数据结构、时区和部署环境进行调整。

## GroovyProvider

### 介绍

`GroovyProvider` 是基于 Groovy 脚本的通用提供器。 该提供器将 `ProviderInfo.param` 作为完整的 Groovy 源码进行编译，
并将编译生成的类实例转换为 `Processor`。

Groovy 脚本可以访问数据库、远程服务、文件或其它数据源，也可以直接计算并生成数据。
脚本负责解释查询时传入的 `preset` 和 `objs`，并返回符合提供器协议的 `LookupResult`。

每个 Groovy 提供器实例持有一个 `Processor`，由其创建的会话复用该处理器。
会话调用 `lookup` 时持有读锁，调用 `openSession` 和 `closeSession` 时持有写锁。
多个查询可以并发执行，但会话打开或关闭不会与查询同时执行。

### 参数

`ProviderInfo.param` 必须是可以被 Groovy 编译器编译的完整类源码，并满足以下要求：

| 项目                 | 说明                                                               |
|----------------------|--------------------------------------------------------------------|
| 实现接口             | 实现 `com.dwarfeng.judge.impl.handler.provider.groovy.Processor`。 |
| `openSession()`      | 初始化会话资源；没有初始化逻辑时可以为空实现。                     |
| `lookup(LookupInfo)` | 根据 `preset` 和 `objs` 执行查询并返回 `LookupResult`。            |
| `closeSession()`     | 释放会话资源；没有资源需要释放时可以为空实现。                     |
| `datas`              | 不允许为 `null`；没有数据时返回空列表。                            |
| `meta`               | 不允许为 `null`；没有元数据时返回空映射。                          |

脚本生成的 `datas` 和 `meta` 中应使用字符串、数字、布尔值、时间戳等简单对象。
如果脚本需要保存连接、客户端或其它运行资源，应在 `openSession()` 中创建，并在 `closeSession()` 中释放。

### 示例参数

以下脚本生成 60 条随机数据，数据之间的时间跨度为 1000 毫秒：

```groovy
package groovy

import com.dwarfeng.judge.impl.handler.provider.groovy.Processor
import com.dwarfeng.judge.stack.handler.ProviderSession

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
    ProviderSession.LookupResult lookup(ProviderSession.LookupInfo info) throws Exception {
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
        return new ProviderSession.LookupResult(datas, meta)
    }
}
```

该示例忽略 `preset` 和 `objs`，无论查询参数为何都返回相同结构的数据。
实际脚本可以通过 `info.getPreset()` 选择查询逻辑，并通过 `info.getObjs()` 获取查询参数。

## DateProvider

### 介绍

`DateProvider` 是日期与时间辅助提供器。 该提供器不访问外部数据源，而是根据固定预设生成时间点、时间范围和时间序列。

`DateProvider` 是无状态实现，打开和关闭会话时不需要分配或释放资源。
所有输出统一使用自 Unix Epoch 起的毫秒时间戳 `Long`，不负责解析或格式化日期字符串。

`ProviderInfo.param` 不参与构造，可以使用空字符串。
具体查询方式由 `ProviderSession.LookupInfo.preset` 指定，参数由固定长度的 `objs` 数组传入。

### 公共参数约定

#### 默认值

可选参数使用字符串 `"-"` 表示采用默认值。 参数位置不能省略，`objs` 的长度必须与预设要求完全一致。

| 参数         | 默认值          | 说明                                        |
|--------------|-----------------|---------------------------------------------|
| 时区         | `Asia/Shanghai` | 使用 Java `ZoneId` 时区 ID。                |
| 基准时间戳   | 当前系统时间    | 调用查询时的 `System.currentTimeMillis()`。 |
| 自然周期偏移 | `0`             | 表示当前日、当前周、当前月或当前年。        |

`null`、空字符串和缺少参数均不表示默认值，会被视为非法参数。

#### Duration

偏移量、步长和桶宽使用 Flux 风格的 duration 字符串。

| 单位 | 说明   |
|------|--------|
| `ms` | 毫秒。 |
| `s`  | 秒。   |
| `m`  | 分钟。 |
| `h`  | 小时。 |
| `d`  | 天。   |
| `w`  | 周。   |
| `mo` | 月。   |
| `y`  | 年。   |

duration 支持单段或复合表达式，例如 `-500ms`、`-1h`、`1h30m`。表达式不允许包含空格或小数；月份使用 `mo`，分钟使用 `m`。

时间计算基于指定时区的 `ZonedDateTime`。 因此，使用天、周、月、年等日历单位时，会遵守对应时区的自然日历和夏令时规则。

#### 输出结构

时间点预设返回的数据行结构如下：

| 字段        | 类型   | 说明               |
|-------------|--------|--------------------|
| `timestamp` | `Long` | 时间点毫秒时间戳。 |

时间范围预设返回的数据行结构如下：

| 字段              | 类型   | 说明                 |
|-------------------|--------|----------------------|
| `start_timestamp` | `Long` | 范围起始毫秒时间戳。 |
| `end_timestamp`   | `Long` | 范围结束毫秒时间戳。 |

时间范围采用左闭右开语义，即包含 `start_timestamp`，不包含 `end_timestamp`。

所有预设的 `meta` 至少包含以下字段：

| 字段         | 类型     | 说明                 |
|--------------|----------|----------------------|
| `preset`     | `String` | 本次执行的预设名称。 |
| `timezone`   | `String` | 本次计算使用的时区。 |
| `data_count` | `int`    | 返回的数据行数量。   |

不同预设还会在 `meta` 中加入 `base_timestamp`、`start_timestamp`、`end_timestamp`、`offset`、`count` 或 `unit` 等字段。

### 时间点预设

时间点预设返回一条包含 `timestamp` 的数据。

| 预设                 | `objs` 长度 | 参数                                 | 说明                               |
|----------------------|-------------|--------------------------------------|------------------------------------|
| `instant.now`        | 1           | `[timezone]`                         | 返回当前系统时间。                 |
| `instant.yesterday`  | 2           | `[base_timestamp, timezone]`         | 返回基准时间对应的前一天同一时刻。 |
| `instant.last_week`  | 2           | `[base_timestamp, timezone]`         | 返回基准时间对应的前一周同一时刻。 |
| `instant.last_month` | 2           | `[base_timestamp, timezone]`         | 返回基准时间对应的前一月同一时刻。 |
| `instant.last_year`  | 2           | `[base_timestamp, timezone]`         | 返回基准时间对应的前一年同一时刻。 |
| `instant.offset`     | 3           | `[offset, base_timestamp, timezone]` | 对基准时间应用指定 duration。      |

参数说明如下：

| 参数             | 类型                | 是否允许 `"-"` | 说明                                  |
|------------------|---------------------|----------------|---------------------------------------|
| `offset`         | `String`            | 否             | 必填 duration，可以为正数、负数或零。 |
| `base_timestamp` | `Number` / `String` | 是             | 基准毫秒时间戳，默认当前系统时间。    |
| `timezone`       | `String`            | 是             | Java 时区 ID，默认 `Asia/Shanghai`。  |

#### 示例参数

返回当前时间：

```json
{
  "preset": "instant.now",
  "objs": [
    "Asia/Shanghai"
  ]
}
```

返回当前时间的前一天同一时刻：

```json
{
  "preset": "instant.yesterday",
  "objs": [
    "-",
    "-"
  ]
}
```

返回指定时间向前偏移 1 小时后的时间点：

```json
{
  "preset": "instant.offset",
  "objs": [
    "-1h",
    1767225600000,
    "Asia/Shanghai"
  ]
}
```

### 时间范围预设

时间范围预设返回一条包含 `start_timestamp` 和 `end_timestamp` 的数据。

| 预设             | `objs` 长度 | 参数                                                 | 说明                                       |
|------------------|-------------|------------------------------------------------------|--------------------------------------------|
| `range.relative` | 3           | `[offset, base_timestamp, timezone]`                 | 以基准时间为一端，生成指定跨度的范围。     |
| `range.day`      | 3           | `[period_offset, base_timestamp, timezone]`          | 返回目标自然日的时间范围。                 |
| `range.week`     | 3           | `[period_offset, base_timestamp, timezone]`          | 返回目标自然周的时间范围，星期一为周起点。 |
| `range.month`    | 3           | `[period_offset, base_timestamp, timezone]`          | 返回目标自然月的时间范围。                 |
| `range.year`     | 3           | `[period_offset, base_timestamp, timezone]`          | 返回目标自然年的时间范围。                 |
| `range.offset`   | 4           | `[start_timestamp, end_timestamp, offset, timezone]` | 将已有时间范围整体偏移指定 duration。      |

参数说明如下：

| 参数              | 类型                | 是否允许 `"-"` | 说明                                                 |
|-------------------|---------------------|----------------|------------------------------------------------------|
| `offset`          | `String`            | 否             | duration；`range.relative` 中不允许为零。            |
| `period_offset`   | `Number` / `String` | 是             | 自然周期偏移，默认 `0`；负数表示过去，正数表示未来。 |
| `base_timestamp`  | `Number` / `String` | 是             | 基准毫秒时间戳，默认当前系统时间。                   |
| `start_timestamp` | `Number` / `String` | 否             | 原始范围起始时间，必须严格小于结束时间。             |
| `end_timestamp`   | `Number` / `String` | 否             | 原始范围结束时间，必须严格大于起始时间。             |
| `timezone`        | `String`            | 是             | Java 时区 ID，默认 `Asia/Shanghai`。                 |

`range.relative` 中，负偏移生成 `[base + offset, base)`，正偏移生成 `[base, base + offset)`。

`range.day`、`range.week`、`range.month` 和 `range.year` 先按 `period_offset` 移动到目标周期， 再返回该周期起点到下一个周期起点的范围。

#### 示例参数

返回最近 24 小时的时间范围：

```json
{
  "preset": "range.relative",
  "objs": [
    "-24h",
    "-",
    "Asia/Shanghai"
  ]
}
```

返回当前自然周的时间范围：

```json
{
  "preset": "range.week",
  "objs": [
    "-",
    "-",
    "Asia/Shanghai"
  ]
}
```

返回上一个自然月的时间范围：

```json
{
  "preset": "range.month",
  "objs": [
    -1,
    "-",
    "Asia/Shanghai"
  ]
}
```

将已有时间范围整体向后偏移一天：

```json
{
  "preset": "range.offset",
  "objs": [
    1767225600000,
    1767312000000,
    "1d",
    "Asia/Shanghai"
  ]
}
```

### 时间序列预设

时间序列预设返回多条按时间升序排列的数据。 单次查询最多允许生成 10000 条数据，超过限制时查询失败。

| 预设                      | `objs` 长度 | 参数                                                      | 说明                                 |
|---------------------------|-------------|-----------------------------------------------------------|--------------------------------------|
| `sequence.interval`       | 4           | `[start_timestamp, end_timestamp, step, timezone]`        | 按固定步长生成时间点序列。           |
| `sequence.bucket`         | 4           | `[start_timestamp, end_timestamp, bucket_size, timezone]` | 按固定桶宽拆分时间范围。             |
| `sequence.recent_buckets` | 4           | `[bucket_size, count, base_timestamp, timezone]`          | 生成以基准时间结束的最近若干时间桶。 |

参数说明如下：

| 参数              | 类型                | 是否允许 `"-"` | 说明                                    |
|-------------------|---------------------|----------------|-----------------------------------------|
| `start_timestamp` | `Number` / `String` | 否             | 序列起始时间，必须严格小于结束时间。    |
| `end_timestamp`   | `Number` / `String` | 否             | 序列结束时间。                          |
| `step`            | `String`            | 否             | 时间点步长，必须是严格为正的 duration。 |
| `bucket_size`     | `String`            | 否             | 时间桶宽度，必须是严格为正的 duration。 |
| `count`           | `Number` / `String` | 否             | 时间桶数量，范围为 1 至 10000。         |
| `base_timestamp`  | `Number` / `String` | 是             | 基准毫秒时间戳，默认当前系统时间。      |
| `timezone`        | `String`            | 是             | Java 时区 ID，默认 `Asia/Shanghai`。    |

`sequence.interval` 从 `start_timestamp` 开始生成时间点，不包含大于或等于 `end_timestamp` 的时间点。

`sequence.bucket` 从起始时间开始生成左闭右开的时间桶。 如果最后一个完整桶超过 `end_timestamp`，最后一个桶会在
`end_timestamp` 处截断。

`sequence.recent_buckets` 以 `base_timestamp` 作为最后一个桶的结束时间，向前生成指定数量的时间桶， 最终结果仍按时间从早到晚排列。

#### 示例参数

每小时生成一个时间点：

```json
{
  "preset": "sequence.interval",
  "objs": [
    1767225600000,
    1767312000000,
    "1h",
    "Asia/Shanghai"
  ]
}
```

将一天拆分为每小时一个时间桶：

```json
{
  "preset": "sequence.bucket",
  "objs": [
    1767225600000,
    1767312000000,
    "1h",
    "Asia/Shanghai"
  ]
}
```

生成截至当前时间的最近 24 个一小时桶：

```json
{
  "preset": "sequence.recent_buckets",
  "objs": [
    "1h",
    24,
    "-",
    "Asia/Shanghai"
  ]
}
```

## 参阅

- [Provider](./Provider.md) - 提供器，详细说明了本项目的提供器机制。
- [Provide](./Provide.md) - 提供机制，详细说明了本项目的提供机制。

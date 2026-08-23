# Preset Analyser Implements - 预设分析器实现

## 说明

本文档详细说明了本项目内置的所有分析器。

所有内置分析器的代码均位于 `com.dwarfeng.judge.impl.handler.analyser` 包的下属包中，
您可以通过查看该包下的所有类来了解本项目内置的所有分析器。

项目中所有的内置分析器如下：

| 类型                              | 名称                        | 说明                                                             |
|-----------------------------------|-----------------------------|------------------------------------------------------------------|
| `groovy_analyser`                 | GroovyAnalyser              | 通过自定义 Groovy 脚本执行分析。                                 |
| `value_set_analyser`              | ValueSetAnalyser            | 将常量、已有分析结果或查询结果转换后写入一个分析结果。           |
| `value_delete_analyser`           | ValueDeleteAnalyser         | 删除当前任务中的指定分析结果。                                   |
| `value_lookup_batch_set_analyser` | ValueLookupBatchSetAnalyser | 执行一次查询，并将多个查询字段批量写入多个分析结果。             |
| `value_lookup_list_set_analyser`  | ValueLookupListSetAnalyser  | 执行一次查询，将数据行中的字段列表序列化为字符串或文件分析结果。 |

`AnalyserInfo.param` 是分析器的构造参数，由具体分析器类型解析。
分析器执行时通过 `Analyser.Context` 访问当前任务、已有分析结果、分析器变量和 Provide 查询链路。

以下各实现的示例参数可作为 `AnalyserInfo.param` 的参考。
实际使用时，需要结合分析器执行顺序、数据类型、提供器配置和部署环境进行调整。

## GroovyAnalyser

### 介绍

`GroovyAnalyser` 是基于 Groovy 脚本的通用分析器。
该分析器将 `AnalyserInfo.param` 作为完整的 Groovy 源码进行编译，并将编译生成的类实例转换为 `Processor`。

Groovy 脚本可以通过分析器上下文读取和写入分析结果、维护分析器变量、更新任务信息，
也可以调用 Provide 查询链路访问数据库、远程服务或其它数据源。

每个 Groovy 分析器实例持有一个 `Processor`，由该分析器创建的执行器复用同一个处理器。
任务执行时，系统为当前任务创建新的 `GroovyExecutor`，并将任务上下文传递给处理器。

分析器实例可能被多个任务并发使用。
如果 Groovy 处理器保存了可变成员状态，脚本需要自行保证并发访问的线程安全性。

### 参数

`AnalyserInfo.param` 必须是可以被 Groovy 编译器编译的完整类源码，并满足以下要求：

| 项目                        | 说明                                                               |
|-----------------------------|--------------------------------------------------------------------|
| 实现接口                    | 实现 `com.dwarfeng.judge.impl.handler.analyser.groovy.Processor`。 |
| `analyse(Analyser.Context)` | 执行分析逻辑，并通过传入的上下文读取数据或持久化结果。             |
| 类实例化                    | 脚本类必须可以通过无参构造器实例化。                               |
| 包名和类名                  | 可以自定义，但源码中引用的包和类型必须在运行节点的类路径中可用。   |

`AnalyserInfo.param` 不是脚本文件路径，也不是 Spring 资源地址，而是完整的脚本文本。
注册器在构造分析器时直接编译该文本。

脚本通过 `Analyser.Context` 可以使用以下主要能力：

| 能力         | 示例方法                                                             |
|--------------|----------------------------------------------------------------------|
| 获取当前主键 | `getSectionKey()`、`getAnalyserInfoKey()`、`getTaskKey()`            |
| 维护变量     | `inspectAnalyserVariable(...)`、`upsertAnalyserVariable(...)`        |
| 维护分析结果 | `inspectAnalysis(...)`、`upsertAnalysis(...)`、`removeAnalysis(...)` |
| 更新任务信息 | `updateTaskModal(...)`、`createTaskEvent(...)`                       |
| 查询外部数据 | `lookupProvide(...)`                                                 |
| 下载分析文件 | `downloadPictureFile(...)`、`downloadFileFile(...)` 等方法           |

如果脚本打开输入流、数据库连接或其它临时资源，应在一次 `analyse()` 调用内完成清理。
分析器接口没有显式的关闭或销毁回调。

### 示例参数

以下脚本演示分析器上下文的部分能力：

```groovy
package groovy

import com.dwarfeng.judge.impl.handler.analyser.groovy.Processor
import com.dwarfeng.judge.sdk.util.Constants
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableUpsertInfo
import com.dwarfeng.judge.stack.bean.dto.AnalysisUpsertInfo
import com.dwarfeng.judge.stack.bean.dto.TaskEventCreateInfo
import com.dwarfeng.judge.stack.bean.dto.TaskUpdateModalInfo
import com.dwarfeng.judge.stack.handler.Analyser
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey

/**
 * 调用上下文的部分方法，以提供示例。
 *
 * <p>
 * 分析方法的行为如下：
 * <ol>
 *     <li>向分析器变量中写入值，键：groovy，值：UUID。</li>
 *     <li>更新任务模态，anchorMessage：groovy。</li>
 *     <li>插入任务事件，message：groovy。</li>
 *     <li>插入分析结果，键：groovy.string，值：UUID。</li>
 *     <li>插入分析结果，键：groovy.long，值：随机长整型。</li>
 *     <li>插入分析结果，键：groovy.double，值：随机双精度浮点型。</li>
 *     <li>插入分析结果，键：groovy.boolean，值：随机布尔型。</li>
 *     <li>插入分析结果，键：groovy.date，值：当前时间。</li>
 * </ol>
 *
 * @author Dwarfeng
 * @since 2.0.0-beta
 */
@SuppressWarnings(['GrPackage', 'unused'])
class ExampleAnalyserProcessor implements Processor {

    @Override
    void analyse(Analyser.Context context) throws Exception {
        // 获取分析器信息键。
        LongIdKey analyserInfoKey = context.getAnalyserInfoKey()

        // 向分析器变量中写入值，键：groovy，值：UUID。
        context.upsertAnalyserVariable(new AnalyserVariableUpsertInfo(
                analyserInfoKey, "groovy", Constants.VARIABLE_VALUE_TYPE_STRING, UUID.randomUUID().toString()
        ))

        // 获取任务键。
        LongIdKey taskKey = context.getTaskKey()

        // 更新任务模态，anchorMessage：groovy。
        context.updateTaskModal(new TaskUpdateModalInfo(taskKey, "groovy"))

        // 插入任务事件，message：groovy。
        context.createTaskEvent(new TaskEventCreateInfo(taskKey, "groovy"))

        // 插入分析结果，键：groovy.string，值：UUID。
        context.upsertAnalysis(new AnalysisUpsertInfo(
                taskKey, "groovy.string", Constants.ANALYSIS_TYPE_STRING, UUID.randomUUID().toString()
        ))

        // 插入分析结果，键：groovy.long，值：随机长整型。
        context.upsertAnalysis(new AnalysisUpsertInfo(
                taskKey, "groovy.long", Constants.ANALYSIS_TYPE_LONG, new Random().nextLong()
        ))

        // 插入分析结果，键：groovy.double，值：随机双精度浮点型。
        context.upsertAnalysis(new AnalysisUpsertInfo(
                taskKey, "groovy.double", Constants.ANALYSIS_TYPE_DOUBLE, new Random().nextDouble()
        ))

        // 插入分析结果，键：groovy.boolean，值：随机布尔型。
        context.upsertAnalysis(new AnalysisUpsertInfo(
                taskKey, "groovy.boolean", Constants.ANALYSIS_TYPE_BOOLEAN, new Random().nextBoolean()
        ))

        // 插入分析结果，键：groovy.date，值：当前时间。
        context.upsertAnalysis(new AnalysisUpsertInfo(
                taskKey, "groovy.date", Constants.ANALYSIS_TYPE_DATE, new Date()
        ))
    }
}
```

## ValueSetAnalyser

### 介绍

`ValueSetAnalyser` 用于取得一个源值，将其转换为指定类型，并写入当前任务的一个 `Analysis`。

源值可以来自以下位置：

| 来源       | 说明                                                       |
|------------|------------------------------------------------------------|
| `constant` | 从分析器参数中解析一个常量值。                             |
| `analysis` | 读取当前任务中已经存在的一个分析结果。                     |
| `lookup`   | 调用一次 Provide 查询，并从 `datas` 或 `meta` 中提取字段。 |

该分析器适合在分析链路中写入固定值、复制并转换已有分析结果，
或从一次外部查询中提取单个字段作为后续判断器和可视化器的输入。

### 参数

顶层参数如下：

| 参数                         | 类型     | 必填条件               | 说明                                                        |
|------------------------------|----------|------------------------|-------------------------------------------------------------|
| `output_analysis_data_id`    | `String` | 必填                   | 输出 `Analysis` 的 `dataStringId`。                         |
| `output_analysis_value_type` | `String` | 必填                   | 输出值类型：`string`、`long`、`double`、`boolean`、`date`。 |
| `source_type`                | `String` | 必填                   | 源值来源：`constant`、`analysis`、`lookup`。                |
| `constant_value_type`        | `String` | `source_type=constant` | 常量值类型，合法值与输出值类型相同。                        |
| `constant_value`             | `String` | `source_type=constant` | 常量原始字符串，按照 `constant_value_type` 解析。           |
| `analysis_data_id`           | `String` | `source_type=analysis` | 当前任务中源分析结果的 `dataStringId`。                     |
| `lookup`                     | `Object` | `source_type=lookup`   | Provide 查询配置。                                          |
| `lookup_result`              | `Object` | `source_type=lookup`   | 从查询结果中提取源值的规则。                                |

`lookup` 参数如下：

| 参数               | 类型     | 必填 | 说明                                      |
|--------------------|----------|------|-------------------------------------------|
| `provider_info_id` | `Long`   | 是   | 提供器信息主键。                          |
| `adapter_info_id`  | `Long`   | 否   | 适配器信息主键；不使用适配器时为 `null`。 |
| `filter_info_id`   | `Long`   | 否   | 过滤器信息主键；不使用过滤器时为 `null`。 |
| `preset`           | `String` | 是   | 提供器查询预设。                          |
| `objs`             | `Array`  | 是   | 查询对象配置数组，允许为空数组。          |

`lookup.objs` 中每个对象的参数如下：

| 参数                  | 类型     | 必填条件                      | 说明                               |
|-----------------------|----------|-------------------------------|------------------------------------|
| `object_source_type`  | `String` | 必填                          | 对象来源：`constant`、`analysis`。 |
| `constant_value_type` | `String` | `object_source_type=constant` | 常量类型。                         |
| `constant_value`      | `String` | `object_source_type=constant` | 常量原始字符串。                   |
| `analysis_data_id`    | `String` | `object_source_type=analysis` | 当前任务中的源分析结果标识。       |

`lookup_result` 参数如下：

| 参数           | 类型      | 必填条件             | 说明                                 |
|----------------|-----------|----------------------|--------------------------------------|
| `result_scope` | `String`  | 必填                 | 结果范围：`datas`、`meta`。          |
| `row_index`    | `Integer` | `result_scope=datas` | 数据行索引，必须大于等于 `0`。       |
| `field_name`   | `String`  | 必填                 | 从目标数据行或元数据中提取的字段名。 |

常量和源值转换规则如下：

| 目标类型  | 支持的常见源值                                                     |
|-----------|--------------------------------------------------------------------|
| `string`  | 字符串以及可通过 `String.valueOf(...)` 表示的对象。                |
| `long`    | 数字、整数字符串、日期；日期转换为毫秒时间戳。                     |
| `double`  | 数字、浮点数字符串、日期；日期转换为毫秒时间戳的双精度值。         |
| `boolean` | 布尔值，或忽略大小写的字符串 `true`、`false`。                     |
| `date`    | 日期、毫秒时间戳数字、`yyyy-MM-dd HH:mm:ss` 字符串或时间戳字符串。 |

源分析结果不存在、查询结果为空、字段不存在、字段值为 `null` 或类型转换失败时，分析器执行失败。

### 示例参数

从 Provide 查询结果的第一行提取 `value`，转换为双精度浮点值并写入 `target_value`：

```json
{
  "output_analysis_data_id": "target_value",
  "output_analysis_value_type": "double",
  "source_type": "lookup",
  "lookup": {
    "provider_info_id": 1001,
    "adapter_info_id": null,
    "filter_info_id": null,
    "preset": "latest",
    "objs": [
      {
        "object_source_type": "constant",
        "constant_value_type": "string",
        "constant_value": "point-001"
      },
      {
        "object_source_type": "analysis",
        "analysis_data_id": "start_time"
      }
    ]
  },
  "lookup_result": {
    "result_scope": "datas",
    "row_index": 0,
    "field_name": "value"
  }
}
```

将常量 `12.5` 写入 `example.constant`：

```json
{
  "output_analysis_data_id": "example.constant",
  "output_analysis_value_type": "double",
  "source_type": "constant",
  "constant_value_type": "double",
  "constant_value": "12.5"
}
```

读取 `example.source`，转换为字符串并写入 `example.analysis`：

```json
{
  "output_analysis_data_id": "example.analysis",
  "output_analysis_value_type": "string",
  "source_type": "analysis",
  "analysis_data_id": "example.source"
}
```

## ValueDeleteAnalyser

### 介绍

`ValueDeleteAnalyser` 用于删除当前任务中指定 `dataStringId` 对应的 `Analysis`。

该分析器只操作当前执行上下文绑定的任务，不会删除其它任务的分析结果，也不会删除分析器变量、判断结果或可视化数据。

可以将该分析器放在分析链路后部，用于清理只服务于中间计算的临时分析结果。

### 参数

参数如下：

| 参数                      | 类型     | 必填 | 说明                                  |
|---------------------------|----------|------|---------------------------------------|
| `delete_analysis_data_id` | `String` | 是   | 待删除 `Analysis` 的 `dataStringId`。 |

参数为空时，分析器执行失败。如果目标分析结果不存在，具体删除行为由分析结果操作处理器统一处理。

### 示例参数

删除当前任务中的 `temp` 分析结果：

```json
{
  "delete_analysis_data_id": "temp"
}
```

## ValueLookupBatchSetAnalyser

### 介绍

`ValueLookupBatchSetAnalyser` 执行一次 Provide 查询，并按照多个输出配置从同一查询结果中提取字段，
转换后写入当前任务的多个 `Analysis`。

与为每个字段分别配置一个 `ValueSetAnalyser` 相比，该分析器只执行一次查询，
适合从同一数据行或同一组元数据中同时取得多个指标。

每个输出可以独立指定目标分析结果标识、目标值类型以及查询结果字段。输出按照 `outputs` 数组顺序处理。

### 参数

顶层参数如下：

| 参数      | 类型     | 必填 | 说明                       |
|-----------|----------|------|----------------------------|
| `lookup`  | `Object` | 是   | Provide 查询配置。         |
| `outputs` | `Array`  | 是   | 输出配置数组，不允许为空。 |

`lookup` 以及 `lookup.objs` 的结构与 `ValueSetAnalyser` 中的对应配置相同：

| 参数               | 类型     | 必填 | 说明                                                |
|--------------------|----------|------|-----------------------------------------------------|
| `provider_info_id` | `Long`   | 是   | 提供器信息主键。                                    |
| `adapter_info_id`  | `Long`   | 否   | 可选适配器信息主键。                                |
| `filter_info_id`   | `Long`   | 否   | 可选过滤器信息主键。                                |
| `preset`           | `String` | 是   | 查询预设。                                          |
| `objs`             | `Array`  | 是   | 对象配置数组，对象来源支持 `constant`、`analysis`。 |

`outputs` 中每个输出的参数如下：

| 参数                         | 类型     | 必填 | 说明                                                      |
|------------------------------|----------|------|-----------------------------------------------------------|
| `output_analysis_data_id`    | `String` | 是   | 输出 `Analysis` 的 `dataStringId`。                       |
| `output_analysis_value_type` | `String` | 是   | 输出类型：`string`、`long`、`double`、`boolean`、`date`。 |
| `lookup_result`              | `Object` | 是   | 从本次查询结果中取值的规则。                              |

`lookup_result` 参数如下：

| 参数           | 类型      | 必填条件             | 说明                           |
|----------------|-----------|----------------------|--------------------------------|
| `result_scope` | `String`  | 必填                 | 结果范围：`datas`、`meta`。    |
| `row_index`    | `Integer` | `result_scope=datas` | 数据行索引，必须大于等于 `0`。 |
| `field_name`   | `String`  | 必填                 | 数据行或元数据中的字段名。     |

所有输出复用同一个 `ProvideLookupResult`。
任意一个输出配置非法、字段不存在或类型转换失败时，分析器执行失败，后续输出不会继续处理。

### 示例参数

以下参数执行一次 `latest` 查询，并从第一行提取温度、湿度，同时从元数据中提取总数：

```json
{
  "lookup": {
    "provider_info_id": 1001,
    "adapter_info_id": null,
    "filter_info_id": null,
    "preset": "latest",
    "objs": [
      {
        "object_source_type": "constant",
        "constant_value_type": "string",
        "constant_value": "point-001"
      },
      {
        "object_source_type": "analysis",
        "analysis_data_id": "start_time"
      }
    ]
  },
  "outputs": [
    {
      "output_analysis_data_id": "temperature.current",
      "output_analysis_value_type": "double",
      "lookup_result": {
        "result_scope": "datas",
        "row_index": 0,
        "field_name": "temperature"
      }
    },
    {
      "output_analysis_data_id": "humidity.current",
      "output_analysis_value_type": "double",
      "lookup_result": {
        "result_scope": "datas",
        "row_index": 0,
        "field_name": "humidity"
      }
    },
    {
      "output_analysis_data_id": "lookup.total",
      "output_analysis_value_type": "long",
      "lookup_result": {
        "result_scope": "meta",
        "field_name": "total"
      }
    }
  ]
}
```

## ValueLookupListSetAnalyser

### 介绍

`ValueLookupListSetAnalyser` 执行一次 Provide 查询，从 `ProvideLookupResult.datas` 的所有行中提取指定字段，
将字段值列表序列化后写入字符串或文件类型的 `Analysis`。

该分析器支持为同一次查询配置多个输出。每个输出可以提取不同字段，并独立选择 JSON 数组或单列 CSV 格式。

支持的输出类型如下：

| 输出类型 | 说明                                               |
|----------|----------------------------------------------------|
| `string` | 将序列化后的文本直接保存为字符串分析结果。         |
| `file`   | 将序列化后的文本编码为字节，并保存为文件分析结果。 |

### 参数

顶层参数如下：

| 参数      | 类型     | 必填 | 说明                               |
|-----------|----------|------|------------------------------------|
| `lookup`  | `Object` | 是   | Provide 查询配置。                 |
| `outputs` | `Array`  | 是   | 字段列表输出配置数组，不允许为空。 |

`lookup` 以及 `lookup.objs` 的结构与其它 ValueLookup 分析器相同：

| 参数               | 类型     | 必填 | 说明                                                |
|--------------------|----------|------|-----------------------------------------------------|
| `provider_info_id` | `Long`   | 是   | 提供器信息主键。                                    |
| `adapter_info_id`  | `Long`   | 否   | 可选适配器信息主键。                                |
| `filter_info_id`   | `Long`   | 否   | 可选过滤器信息主键。                                |
| `preset`           | `String` | 是   | 查询预设。                                          |
| `objs`             | `Array`  | 是   | 对象配置数组，对象来源支持 `constant`、`analysis`。 |

`outputs` 中每个输出的参数如下：

| 参数                         | 类型     | 必填条件                          | 说明                                |
|------------------------------|----------|-----------------------------------|-------------------------------------|
| `output_analysis_data_id`    | `String` | 必填                              | 输出 `Analysis` 的 `dataStringId`。 |
| `output_analysis_value_type` | `String` | 必填                              | 输出类型：`string`、`file`。        |
| `field_name`                 | `String` | 必填                              | 从 `datas` 每一行提取的字段名。     |
| `format`                     | `String` | 必填                              | 序列化格式：`json_array`、`csv`。   |
| `file_origin_name`           | `String` | `output_analysis_value_type=file` | 文件分析结果的原始文件名。          |
| `csv`                        | `Object` | 否                                | `format=csv` 时使用的 CSV 子配置。  |

`csv` 参数如下：

| 参数             | 类型      | 默认值  | 说明                                   |
|------------------|-----------|---------|----------------------------------------|
| `include_header` | `Boolean` | `false` | 是否输出表头。                         |
| `header_name`    | `String`  | 字段名  | 表头名称；未配置时使用 `field_name`。  |
| `charset`        | `String`  | `UTF-8` | 字符串转文件时使用的 Java 字符集名称。 |
| `line_separator` | `String`  | `LF`    | 换行符，支持 `LF`、`CRLF`。            |

`json_array` 使用 JSON 数组格式保存字段列表，例如 `[12.5,13.0,14.2]`。

CSV 为单列结构，每个数据值占一行。
当单元格包含英文逗号、双引号、回车或换行时，分析器按照 CSV 规则使用双引号包裹并转义内部双引号。

查询结果的 `datas` 为空、任意数据行为 `null`、字段不存在或字段值为 `null` 时，分析器执行失败。

### 示例参数

以下参数执行一次范围查询，并生成三个分析结果：

- 将温度列表以 JSON 数组写入字符串分析结果。
- 将湿度列表以带表头的 UTF-8 CSV 写入文件分析结果。
- 将状态列表以 JSON 数组写入文件分析结果。

```json
{
  "lookup": {
    "provider_info_id": 1001,
    "adapter_info_id": null,
    "filter_info_id": null,
    "preset": "range_query",
    "objs": [
      {
        "object_source_type": "constant",
        "constant_value_type": "string",
        "constant_value": "point-001"
      },
      {
        "object_source_type": "analysis",
        "analysis_data_id": "query.start_time"
      },
      {
        "object_source_type": "analysis",
        "analysis_data_id": "query.end_time"
      }
    ]
  },
  "outputs": [
    {
      "output_analysis_data_id": "temperature.list.json",
      "output_analysis_value_type": "string",
      "field_name": "temperature",
      "format": "json_array"
    },
    {
      "output_analysis_data_id": "humidity.list.csv",
      "output_analysis_value_type": "file",
      "field_name": "humidity",
      "format": "csv",
      "file_origin_name": "humidity.csv",
      "csv": {
        "include_header": true,
        "header_name": "humidity",
        "charset": "UTF-8",
        "line_separator": "LF"
      }
    },
    {
      "output_analysis_data_id": "status.list.json",
      "output_analysis_value_type": "file",
      "field_name": "status",
      "format": "json_array",
      "file_origin_name": "status.json"
    }
  ]
}
```

## 参阅

- [Analyser](./Analyser.md) - 分析器，详细说明了本项目的分析器机制。

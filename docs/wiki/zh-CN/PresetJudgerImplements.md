# Preset Judger Implements - 预设判断器实现

## 说明

本文档详细说明了本项目内置的所有判断器。

所有内置判断器的代码均位于 `com.dwarfeng.judge.impl.handler.judger` 包的下属包中，
您可以通过查看该包下的所有类来了解本项目内置的所有判断器。

项目中所有的内置判断器如下：

| 类型                    | 名称                | 说明                                                       |
|-------------------------|---------------------|------------------------------------------------------------|
| `groovy_judger`         | GroovyJudger        | 通过自定义 Groovy 脚本执行判断。                           |
| `identity_judger`       | IdentityJudger      | 将常量或分析结果直接标准化为判断结果。                     |
| `threshold_judger`      | ThresholdJudger     | 在低阈值与高阈值之间按线性插值输出标准化判断结果。         |
| `linear_mapping_judger` | LinearMappingJudger | 将输入值、系数和偏移按线性公式映射为标准化判断结果。       |
| `binarization_judger`   | BinarizationJudger  | 将输入值与基准值比较后输出二值判断结果。                   |

`JudgerInfo.param` 是判断器的构造参数，由具体判断器类型解析。
判断器执行时通过 `Judger.Context` 访问当前任务、已有分析结果、已有判断结果、判断器变量和 Provide 查询链路。

以下各实现的示例参数可作为 `JudgerInfo.param` 的参考。
实际使用时，需要结合分析器输出、判断器执行顺序、报警阈值和部署环境进行调整。

## 公共数值源约定

除 `GroovyJudger` 外，其它预设判断器都使用相同的数值源解析方式。

一个数值源可以来自以下位置：

| 来源       | 说明                                   |
|------------|----------------------------------------|
| `constant` | 从判断器参数中解析一个常量 double 值。 |
| `analysis` | 读取当前任务中已经存在的一个分析结果。 |

当来源为 `analysis` 时，判断器支持以下分析结果类型：

| 分析结果类型 | 转换规则                                            |
|--------------|-----------------------------------------------------|
| `long`       | 将 `Long` 转换为 `double`。                         |
| `double`     | 直接使用 `Double`。                                 |
| `string`     | 按 Java `Double.parseDouble(...)` 解析字符串。      |
| `boolean`    | 使用对应配置中的 false/true 映射值转换为 `double`。 |

如果分析结果不存在、分析结果类型不受支持、字符串无法解析为 `double`，
或布尔分析结果缺少 false/true 映射值，判断器执行失败。

所有预设判断器最终都会写入 `Judgement`。
写入的判断值必须在 `[0.0, 1.0]` 范围内；部分判断器会主动裁剪输出值，部分判断器通过自身规则保证输出值合法。

## GroovyJudger

### 介绍

`GroovyJudger` 是基于 Groovy 脚本的通用判断器。
该判断器将 `JudgerInfo.param` 作为完整的 Groovy 源码进行编译，并将编译生成的类实例转换为 `Processor`。

Groovy 脚本可以通过判断器上下文读取分析结果和判断结果、维护判断器变量、更新任务信息，
也可以调用 Provide 查询链路访问数据库、远程服务或其它数据源。

每个 Groovy 判断器实例持有一个 `Processor`，由该判断器创建的执行器复用同一个处理器。
任务执行时，系统为当前任务创建新的 `GroovyExecutor`，并将任务上下文传递给处理器。

判断器实例可能被多个任务并发使用。
如果 Groovy 处理器保存了可变成员状态，脚本需要自行保证并发访问的线程安全性。

### 参数

`JudgerInfo.param` 必须是可以被 Groovy 编译器编译的完整类源码，并满足以下要求：

| 项目                     | 说明                                                             |
|--------------------------|------------------------------------------------------------------|
| 实现接口                 | 实现 `com.dwarfeng.judge.impl.handler.judger.groovy.Processor`。 |
| `judge(Judger.Context)`  | 执行判断逻辑，并通过传入的上下文读取数据或持久化结果。           |
| 类实例化                 | 脚本类必须可以通过无参构造器实例化。                             |
| 包名和类名               | 可以自定义，但源码中引用的包和类型必须在运行节点的类路径中可用。 |

`JudgerInfo.param` 不是脚本文件路径，也不是 Spring 资源地址，而是完整的脚本文本。
注册器在构造判断器时直接编译该文本。

脚本通过 `Judger.Context` 可以使用以下主要能力：

| 能力         | 示例方法                                                                |
|--------------|-------------------------------------------------------------------------|
| 获取当前主键 | `getSectionKey()`、`getJudgerInfoKey()`、`getTaskKey()`                 |
| 维护变量     | `inspectJudgerVariable(...)`、`upsertJudgerVariable(...)`               |
| 读取分析结果 | `inspectAnalysis(...)`                                                  |
| 维护判断结果 | `inspectJudgement(...)`、`upsertJudgement(...)`、`removeJudgement(...)` |
| 更新任务信息 | `updateTaskModal(...)`、`createTaskEvent(...)`                          |
| 查询外部数据 | `lookupProvide(...)`                                                    |
| 下载分析文件 | `downloadPictureFile(...)`、`downloadFileFile(...)` 等方法              |

如果脚本打开输入流、数据库连接或其它临时资源，应在一次 `judge()` 调用内完成清理。
判断器接口没有显式的关闭或销毁回调。

### 示例参数

以下脚本演示判断器上下文的部分能力：

```groovy
package groovy

import com.dwarfeng.judge.impl.handler.judger.groovy.Processor
import com.dwarfeng.judge.sdk.util.Constants
import com.dwarfeng.judge.stack.bean.dto.JudgementUpsertInfo
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableUpsertInfo
import com.dwarfeng.judge.stack.bean.dto.TaskEventCreateInfo
import com.dwarfeng.judge.stack.bean.dto.TaskUpdateModalInfo
import com.dwarfeng.judge.stack.handler.Judger
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey

/**
 * 调用上下文的部分方法，以提供示例。
 *
 * <p>
 * 判断方法的行为如下：
 * <ol>
 *     <li>向判断器变量中写入值，键：groovy，值：UUID。</li>
 *     <li>更新任务模态，anchorMessage：groovy。</li>
 *     <li>插入任务事件，message：groovy。</li>
 *     <li>插入判断结果，键：groovy，判断值：介于 [0.0, 1.0] 之间的随机双精度浮点数，判断消息: 示例消息。</li>
 * </ol>
 *
 * @author Dwarfeng
 * @since 2.0.0-beta
 */
@SuppressWarnings(['GrPackage', 'unused'])
class ExampleJudgerProcessor implements Processor {

    @Override
    void judge(Judger.Context context) throws Exception {
        // 获取判断器信息键。
        LongIdKey judgerInfoKey = context.getJudgerInfoKey()

        // 向判断器变量中写入值，键：groovy，值：UUID。
        context.upsertJudgerVariable(new JudgerVariableUpsertInfo(
                judgerInfoKey, "groovy", Constants.VARIABLE_VALUE_TYPE_STRING, UUID.randomUUID().toString()
        ))

        // 获取任务键。
        LongIdKey taskKey = context.getTaskKey()

        // 更新任务模态，anchorMessage：groovy。
        context.updateTaskModal(new TaskUpdateModalInfo(taskKey, "groovy"))

        // 插入任务事件，message：groovy。
        context.createTaskEvent(new TaskEventCreateInfo(taskKey, "groovy"))

        // 插入判断结果，键：groovy，判断值：随机双精度浮点数，判断消息: 示例消息。
        context.upsertJudgement(new JudgementUpsertInfo(
                taskKey, "groovy", new Random().nextDouble(), "示例消息"
        ))
    }
}
```

## IdentityJudger

### 介绍

`IdentityJudger` 用于取得一个源值，将其转换为 `double`，裁剪到 `[0.0, 1.0]`，
并写入当前任务的一个 `Judgement`。

源值可以来自常量或当前任务中的分析结果。
该判断器适合将已经标准化的分析结果直接转为判断结果，也适合对简单数值进行统一的范围裁剪。

### 参数

参数如下：

| 参数                       | 类型     | 必填条件                     | 说明                                    |
|----------------------------|----------|------------------------------|-----------------------------------------|
| `output_judgement_data_id` | `String` | 必填                         | 输出 `Judgement` 的 `dataStringId`。    |
| `input_source_type`        | `String` | 必填                         | 输入来源：`constant`、`analysis`。      |
| `input_constant_value`     | `String` | `input_source_type=constant` | 常量原始字符串，按 `double` 解析。      |
| `input_analysis_data_id`   | `String` | `input_source_type=analysis` | 当前任务中源分析结果的 `dataStringId`。 |
| `input_false_value`        | `String` | 输入分析结果类型为 `boolean` | `false` 对应的 double 字符串。          |
| `input_true_value`         | `String` | 输入分析结果类型为 `boolean` | `true` 对应的 double 字符串。           |

输入值转换为 `double` 后会被裁剪到 `[0.0, 1.0]`。
输出判断消息会记录来源、原始值、转换值和最终输出值。

### 示例参数

读取 `example.analysis`，转换并写入 `example.judgement`：

```json
{
  "output_judgement_data_id": "example.judgement",
  "input_source_type": "analysis",
  "input_constant_value": "0.6",
  "input_analysis_data_id": "example.analysis",
  "input_false_value": "0.0",
  "input_true_value": "1.0"
}
```

将常量 `0.75` 写入判断结果：

```json
{
  "output_judgement_data_id": "example.constant",
  "input_source_type": "constant",
  "input_constant_value": "0.75",
  "input_analysis_data_id": null,
  "input_false_value": null,
  "input_true_value": null
}
```

## ThresholdJudger

### 介绍

`ThresholdJudger` 用于解析输入值、低阈值和高阈值三个数值源，
并在低阈值与高阈值之间按线性插值输出 `[0.0, 1.0]` 范围内的判断结果。

当低阈值小于高阈值时，输入值越接近高阈值，输出值越高。
当低阈值大于高阈值时，判断器执行反向插值，输入值越接近高阈值，输出值越高。

### 参数

顶层参数如下：

| 参数                         | 类型     | 必填 | 说明                                      |
|------------------------------|----------|------|-------------------------------------------|
| `output_judgement_data_id`   | `String` | 是   | 输出 `Judgement` 的 `dataStringId`。      |
| `input_source_type`          | `String` | 是   | 输入值来源：`constant`、`analysis`。      |
| `low_threshold_source_type`  | `String` | 是   | 低阈值来源：`constant`、`analysis`。      |
| `high_threshold_source_type` | `String` | 是   | 高阈值来源：`constant`、`analysis`。      |

每个数值源都使用相同的字段组。
以下以前缀 `input` 为例，低阈值和高阈值分别使用 `low_threshold`、`high_threshold` 前缀：

| 参数                        | 类型     | 必填条件                          | 说明                                 |
|-----------------------------|----------|-----------------------------------|--------------------------------------|
| `<prefix>_constant_value`   | `String` | `<prefix>_source_type=constant`   | 常量原始字符串，按 `double` 解析。   |
| `<prefix>_analysis_data_id` | `String` | `<prefix>_source_type=analysis`   | 当前任务中的源分析结果标识。         |
| `<prefix>_false_value`      | `String` | 源分析结果类型为 `boolean`        | `false` 对应的 double 字符串。       |
| `<prefix>_true_value`       | `String` | 源分析结果类型为 `boolean`        | `true` 对应的 double 字符串。        |

低阈值与高阈值不能相等。
如果二者相等，判断器执行失败。

插值规则如下：

| 条件                            | 输出规则                              |
|---------------------------------|---------------------------------------|
| `low < high` 且输入不大于低阈值 | 输出 `0.0`。                          |
| `low < high` 且输入不小于高阈值 | 输出 `1.0`。                          |
| `low < high` 且输入在区间内     | 输出 `(input - low) / (high - low)`。 |
| `low > high` 且输入不小于低阈值 | 输出 `0.0`。                          |
| `low > high` 且输入不大于高阈值 | 输出 `1.0`。                          |
| `low > high` 且输入在区间内     | 输出 `(low - input) / (low - high)`。 |

### 示例参数

读取 `example.input`，在 `0` 到 `100` 之间正向插值：

```json
{
  "output_judgement_data_id": "example.judgement",
  "input_source_type": "analysis",
  "input_constant_value": "25",
  "input_analysis_data_id": "example.input",
  "input_false_value": "0.0",
  "input_true_value": "1.0",
  "low_threshold_source_type": "constant",
  "low_threshold_constant_value": "0",
  "low_threshold_analysis_data_id": null,
  "low_threshold_false_value": null,
  "low_threshold_true_value": null,
  "high_threshold_source_type": "constant",
  "high_threshold_constant_value": "100",
  "high_threshold_analysis_data_id": null,
  "high_threshold_false_value": null,
  "high_threshold_true_value": null
}
```

## LinearMappingJudger

### 介绍

`LinearMappingJudger` 用于解析输入值 `x`、系数 `k` 和偏移 `b` 三个数值源，
按 `k * x + b` 计算映射值，裁剪到 `[0.0, 1.0]` 后写入判断结果。

该判断器适合将业务指标按一次函数转换为标准化评分，例如将百分比、小数比例或偏移后的测量值映射为判断值。

### 参数

顶层参数如下：

| 参数                       | 类型     | 必填 | 说明                                      |
|----------------------------|----------|------|-------------------------------------------|
| `output_judgement_data_id` | `String` | 是   | 输出 `Judgement` 的 `dataStringId`。      |
| `input_source_type`        | `String` | 是   | 输入值 `x` 来源：`constant`、`analysis`。 |
| `coefficient_source_type`  | `String` | 是   | 系数 `k` 来源：`constant`、`analysis`。   |
| `offset_source_type`       | `String` | 是   | 偏移 `b` 来源：`constant`、`analysis`。   |

每个数值源都使用相同的字段组。
以下以前缀 `input` 为例，系数和偏移分别使用 `coefficient`、`offset` 前缀：

| 参数                        | 类型     | 必填条件                          | 说明                                 |
|-----------------------------|----------|-----------------------------------|--------------------------------------|
| `<prefix>_constant_value`   | `String` | `<prefix>_source_type=constant`   | 常量原始字符串，按 `double` 解析。   |
| `<prefix>_analysis_data_id` | `String` | `<prefix>_source_type=analysis`   | 当前任务中的源分析结果标识。         |
| `<prefix>_false_value`      | `String` | 源分析结果类型为 `boolean`        | `false` 对应的 double 字符串。       |
| `<prefix>_true_value`       | `String` | 源分析结果类型为 `boolean`        | `true` 对应的 double 字符串。        |

映射值小于 `0.0` 时输出 `0.0`，大于 `1.0` 时输出 `1.0`。
输出判断消息会记录 `x`、`k`、`b` 的来源、原始值、转换值、映射值和最终输出值。

### 示例参数

读取 `example.input`，按 `0.1 * x + 0.0` 映射：

```json
{
  "output_judgement_data_id": "example.judgement",
  "input_source_type": "analysis",
  "input_constant_value": "10",
  "input_analysis_data_id": "example.input",
  "input_false_value": "0.0",
  "input_true_value": "1.0",
  "coefficient_source_type": "constant",
  "coefficient_constant_value": "0.1",
  "coefficient_analysis_data_id": null,
  "coefficient_false_value": null,
  "coefficient_true_value": null,
  "offset_source_type": "constant",
  "offset_constant_value": "0.0",
  "offset_analysis_data_id": null,
  "offset_false_value": null,
  "offset_true_value": null
}
```

## BinarizationJudger

### 介绍

`BinarizationJudger` 用于解析输入值和基准值两个数值源，
按配置的比较方式得到布尔判断结果，并输出固定二值 `1.0` 或 `0.0`。

该判断器适合把阈值命中、开关状态、达标状态等规则转换为判断结果。

### 参数

顶层参数如下：

| 参数                       | 类型     | 必填 | 说明                                      |
|----------------------------|----------|------|-------------------------------------------|
| `output_judgement_data_id` | `String` | 是   | 输出 `Judgement` 的 `dataStringId`。      |
| `input_source_type`        | `String` | 是   | 输入值来源：`constant`、`analysis`。      |
| `baseline_source_type`     | `String` | 是   | 基准值来源：`constant`、`analysis`。      |
| `comparator`               | `String` | 是   | 比较方式：`ge`、`le`、`gt`、`lt`。        |

输入值和基准值都使用相同的字段组。
以下以前缀 `input` 为例，基准值使用 `baseline` 前缀：

| 参数                        | 类型     | 必填条件                          | 说明                                 |
|-----------------------------|----------|-----------------------------------|--------------------------------------|
| `<prefix>_constant_value`   | `String` | `<prefix>_source_type=constant`   | 常量原始字符串，按 `double` 解析。   |
| `<prefix>_analysis_data_id` | `String` | `<prefix>_source_type=analysis`   | 当前任务中的源分析结果标识。         |
| `<prefix>_false_value`      | `String` | 源分析结果类型为 `boolean`        | `false` 对应的 double 字符串。       |
| `<prefix>_true_value`       | `String` | 源分析结果类型为 `boolean`        | `true` 对应的 double 字符串。        |

比较器规则如下：

| 比较器 | 条件                    | 条件成立时 | 条件不成立时 |
|--------|-------------------------|------------|--------------|
| `ge`   | `input >= baseline`     | `1.0`      | `0.0`        |
| `le`   | `input <= baseline`     | `1.0`      | `0.0`        |
| `gt`   | `input > baseline`      | `1.0`      | `0.0`        |
| `lt`   | `input < baseline`      | `1.0`      | `0.0`        |

### 示例参数

读取 `example.input`，判断其是否大于等于常量 `3`：

```json
{
  "output_judgement_data_id": "example.judgement",
  "input_source_type": "analysis",
  "input_constant_value": "5",
  "input_analysis_data_id": "example.input",
  "input_false_value": "0.0",
  "input_true_value": "1.0",
  "baseline_source_type": "constant",
  "baseline_constant_value": "3",
  "baseline_analysis_data_id": null,
  "baseline_false_value": null,
  "baseline_true_value": null,
  "comparator": "ge"
}
```

## 参阅

- [Judger](./Judger.md) - 判断器，详细说明了本项目的判断器机制。

# Preset Sinker Implements - 预设下沉器实现

## 说明

本文档详细说明了本项目内置的所有下沉器。

所有内置下沉器的代码均位于 `com.dwarfeng.judge.impl.handler.sinker` 包的下属包中，
您可以通过查看该包下的注册器、下沉器和会话类来了解本项目内置的下沉器。

项目中所有的内置下沉器如下：

| 类型           | 名称              | 说明                                                 |
|----------------|-------------------|------------------------------------------------------|
| `mock`         | MockSinker        | 将下沉信息写入日志，可配置延时和日志等级，用于测试。 |
| `kafka.native` | NativeKafkaSinker | 使用 Spring Kafka 原生客户端将下沉信息发送到 Kafka。 |

`SinkerInfo.param` 是下沉器的构造参数，由具体下沉器类型解析。
下沉器执行时通过 `SinkerSession.Context` 读取部件绑定元数据和下沉器变量，
通过 `SinkInfo` 接收当前任务的完整结果快照。

以下各实现的示例参数可作为 `SinkerInfo.param` 的参考。
实际使用时，还需要根据部件关联关系、关联元数据和目标系统配置进行调整。

## MockSinker

### 介绍

`MockSinker` 是用于测试和演示的下沉器实现。
它不会访问外部系统，也不会持久化下沉结果，而是将收到的 `SinkInfo` 写入日志。

`MockSinker` 在会话打开时读取当前下沉器关联的全部部件绑定，并为每个部件解析 `sink_delay` 和 `log_level`。
每次下沉时，它根据当前 `sectionKey` 取得配置，先执行配置的延时，再按配置的日志等级记录下沉信息。
会话关闭时清理已经解析的部件配置。

### 配置项

`MockSinker` 不需要构造参数，`SinkerInfo.param` 应为空字符串。
它使用以下部件关联元数据：

| 元数据标识   | 标签     | 默认值 | 说明                                              |
|--------------|----------|--------|---------------------------------------------------|
| `sink_delay` | 下沉延时 | `0`    | 延时时间，单位为毫秒；必须能解析为非负长整数。    |
| `log_level`  | 日志等级 | `info` | 日志等级，支持 `debug`、`info`、`warn`、`error`。 |

元数据值以字符串保存。未配置某项时，绑定信息使用注册器声明的默认值。
日志等级比较使用小写值，配置其它字符串会导致该部件的下沉执行异常，并由下沉处理器记录警告、丢弃本次下沉。

### 示例参数

`SinkerInfo` 示例：

| 字段      | 示例值   | 说明                      |
|-----------|----------|---------------------------|
| `enabled` | `true`   | 启用该下沉器信息。        |
| `type`    | `mock`   | 使用 `MockSinker`。       |
| `param`   | 空字符串 | Mock 实现不需要构造参数。 |

部件关联元数据示例：

| `metaStringId` | `value` | 说明                          |
|----------------|---------|-------------------------------|
| `sink_delay`   | `100`   | 每次下沉前等待 100 毫秒。     |
| `log_level`    | `debug` | 使用 debug 等级记录下沉信息。 |

### 适用场景

`MockSinker` 适合以下场景：

- 验证部件、下沉器信息、关联关系和元数据配置是否生效。
- 验证作业是否能够执行到第 5/5 步并生成完整 `SinkInfo`。
- 在不连接 Kafka 或其它外部目标的情况下观察下沉内容。
- 使用 `sink_delay` 模拟较慢的下沉操作，检查心跳、任务时序和运维命令。

它不适合生产结果投递，也不提供跨进程可靠投递、重试或补偿能力。

## NativeKafkaSinker

### 介绍

`NativeKafkaSinker` 使用 Spring Kafka 的 `KafkaTemplate<String, String>` 将任务下沉信息发送到 Kafka。
每个下沉器会话打开时，根据 `SinkerInfo.param` 中的 Bean 名称从 Spring 容器取得 KafkaTemplate 和
`KafkaTransactionManager`，并读取该下沉器关联的全部部件分区配置。

每次下沉时，Native Kafka 实现以当前部件主键的长整型字符串作为 Kafka 消息键，
使用部件关联元数据确定分区，并将 `SinkInfo` 转换为 `FastJsonSinkInfo` 后序列化为 JSON 消息体。
发送在 Kafka 事务中执行，发送成功后提交事务；发送或提交失败时回滚事务并记录警告。

### 配置项

`SinkerInfo.param` 必须是 JSON 对象，字段如下：

| 字段                                  | 类型     | 说明                                                                  |
|---------------------------------------|----------|-----------------------------------------------------------------------|
| `kafka_template_bean_name`            | `String` | Spring 容器中的 `KafkaTemplate<String, String>` Bean 名称。           |
| `kafka_transaction_manager_bean_name` | `String` | Spring 容器中的 `KafkaTransactionManager<String, String>` Bean 名称。 |
| `topic`                               | `String` | Kafka 目标主题。                                                      |

Native Kafka 使用以下部件关联元数据：

| 元数据标识  | 标签 | 默认值 | 说明                                                    |
|-------------|------|--------|---------------------------------------------------------|
| `partition` | 分区 | `any`  | `any` 表示不指定分区；整数值表示发送到指定 Kafka 分区。 |

分区配置按部件保存。下沉器会话打开时解析这些配置，并在每次下沉时按当前部件选择分区。
分区值不是 `any` 且不能解析为整数时，该部件的下沉执行失败。

### 示例参数

`SinkerInfo.param` 示例：

```json
{
  "kafka_template_bean_name": "nativeKafkaSinkerKafkaTemplate",
  "kafka_transaction_manager_bean_name": "nativeKafkaSinkerKafkaTransactionManager",
  "topic": "your-topic-here"
}
```

关联元数据示例：

```text
metaStringId: partition
value: any
```

在此配置下，消息发送到 `your-topic-here`，不主动指定分区，由 Kafka 根据消息键和生产者配置选择分区。
将 `value` 改为 `0`、`1` 等整数时，消息会发送到对应分区。

Kafka Bean 示例配置位于 [Opt Directory](./OptDirectory.md) 的 `opt-sinker-kafka-native.xml` 部分，
其中需要配置 Kafka Bootstrap Servers、重试次数、批量大小、等待时间、缓冲区大小、确认模式和事务前缀。

### 适用场景

`NativeKafkaSinker` 适合将 Judge 任务结果发布到 Kafka，再由下游服务进行存储、通知、索引或二次处理。
它适合需要消息主题隔离、按部件指定分区以及 Kafka 事务发送的部署场景。

该实现只负责当前一次发送过程。`SinkHandler` 不提供框架级重试；Kafka Producer 的 `retries` 是生产者客户端参数，
不能替代下沉处理器对失败下沉的补偿和业务幂等设计。下游消费者应根据 `task_key`、消息键或自身业务标识设计去重策略。

## 启用方式

预设下沉器的组件扫描位于 `opt/opt-sinker.xml`，默认配置为注释状态。
启用某种下沉器时，取消对应包的 `context:component-scan` 注释并重启节点。

启用 `NativeKafkaSinker` 还必须配置 `opt/opt-sinker-kafka-native.xml` 中的 Kafka 相关 Bean，
并保证 `SinkerInfo.param` 中引用的 Bean 名称与实际 Bean 名称一致。

启用完成后，建议执行 `support --reset-sinker`，使当前加载的注册器及其元数据指示器写入支持信息；
然后创建或更新 `SinkerInfo`、`SinkerRelation` 和 `SinkerMeta`，最后执行 `reset --reset-sink` 清理旧缓存并重新打开会话。

## 运维与配置

可以使用以下 Telqos 命令检查预设下沉器：

| 命令                       | 说明                                               |
|----------------------------|----------------------------------------------------|
| `slc -l <sinker-info-id>`  | 查看 Mock 或 Native Kafka 下沉器实例缓存。         |
| `sblc -l <sinker-info-id>` | 查看指定下沉器关联的部件和元数据。                 |
| `ss -l <sinker-info-id>`   | 查看指定下沉器会话是否已打开。                     |
| `reset --reset-sink`       | 关闭会话并清理下沉相关缓存，使配置重新生效。       |

配置变更后，如果仍复用旧会话或旧绑定缓存，新的参数和元数据不会立即反映到运行中的下沉器；
应先执行下沉重置，再观察目标日志或 Kafka 消费端。

## 参阅

- [Sinker](./Sinker.md) - 下沉器机制，详细说明接口、生命周期和绑定缓存。
- [Opt Directory](./OptDirectory.md) - 可选配置目录，说明预设下沉器和 Kafka Bean 配置。
- [Telqos Commands](./TelqosCommands.md) - Telqos 命令，说明下沉器缓存、会话和重置命令。

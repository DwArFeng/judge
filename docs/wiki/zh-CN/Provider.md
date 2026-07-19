# Provider - 提供器

## 说明

提供器用于在 Judge 的提供机制中访问外部数据或生成辅助数据。

在评价任务执行过程中，分析器可能需要查询历史记录、实时状态、时间范围、统计结果或其它业务数据。
不同数据源的连接方式、查询参数和结果结构存在差异，如果分析器直接访问这些数据源，分析逻辑会与数据访问逻辑耦合。

提供器将具体的数据访问过程封装为统一的会话查询接口。
调用方通过 `preset` 指定查询方式，通过 `objs` 传递查询参数，提供器返回统一结构的 `datas` 和 `meta`。

提供器是 [Provide](./Provide.md) 查询链路中的核心执行环节，典型链路如下：

```text
ProvideLookupInfo
  -> Adapter
  -> ProviderSession
  -> Filter
  -> ProvideLookupResult
```

`Adapter` 和 `Filter` 是可选环节，`ProviderSession` 负责执行实际查询。
本文介绍提供器本身的信息实体、接口、生命周期和运行机制；完整查询链路请参阅 [Provide](./Provide.md)。

## ProviderInfo

`ProviderInfo` 描述一个提供器运行实例，关键字段如下：

| 字段      | 类型        | 说明                                                       |
|-----------|-------------|------------------------------------------------------------|
| `key`     | `LongIdKey` | 提供器信息主键。                                           |
| `enabled` | `boolean`   | 是否启用；只有启用的提供器信息可以通过本地缓存创建提供器。 |
| `type`    | `String`    | 提供器类型标识，用于匹配对应的 `ProviderMaker`。           |
| `param`   | `String`    | 提供器构造参数，具体格式由提供器类型决定。                 |
| `remark`  | `String`    | 备注。                                                     |

同一种 `type` 可以对应多条 `ProviderInfo`。
因此，同一种提供器实现可以使用不同的参数构造多个运行实例，分别服务不同的查询场景。

提供器按 `ProviderInfo.key` 进入本地缓存和会话持有映射。
当提供器信息不存在或 `enabled=false` 时，系统认为对应提供器不可用，不会为其建立会话。

如果 `type` 没有对应的制造器，系统抛出不支持提供器类型异常。
如果 `param` 无法被对应实现解析，系统在构造提供器时抛出提供器制造异常。

## ProviderSupport

`ProviderSupport` 描述当前节点支持的一种提供器类型，关键字段如下：

| 字段           | 类型          | 说明                                          |
|----------------|---------------|-----------------------------------------------|
| `key`          | `StringIdKey` | 提供器类型标识，与 `ProviderInfo.type` 对应。 |
| `label`        | `String`      | 提供器类型的显示名称。                        |
| `description`  | `String`      | 提供器类型的说明。                            |
| `exampleParam` | `String`      | 示例构造参数。                                |

提供器注册通常同时实现 `ProviderMaker` 和 `ProviderSupporter`。
`ProviderMaker` 负责根据类型和参数构造提供器，`ProviderSupporter` 负责提供类型、标签、说明和示例参数。

系统启动或执行支持重置时，会收集 Spring 容器中的 `ProviderSupporter` 并更新 `ProviderSupport`。
管理端可以读取这些信息，展示当前部署实际加载的提供器类型及其示例参数。

`ProviderSupport` 只描述类型支持能力，不代表已经存在对应的 `ProviderInfo`，也不代表已经建立提供器会话。

## 接口

### Provider

提供器 `Provider` 是创建查询会话的入口，主要方法如下：

| 方法签名                                                | 说明                     |
|---------------------------------------------------------|--------------------------|
| `ProviderSession newSession() throws ProviderException` | 创建一个新的提供器会话。 |

具体实现通常继承 SDK 中的 `AbstractProvider`，并实现创建会话的模板方法。
提供器可以保存构造参数、客户端工厂或脚本处理器等创建会话所需的状态。

### ProviderSession

`ProviderSession` 表示一次可复用的提供器会话，主要方法如下：

| 方法签名                                                               | 说明       |
|------------------------------------------------------------------------|------------|
| `void openSession() throws ProviderSessionException`                   | 打开会话。 |
| `LookupResult lookup(LookupInfo info) throws ProviderSessionException` | 执行查询。 |
| `void closeSession() throws ProviderSessionException`                  | 关闭会话。 |

具体实现通常继承 SDK 中的 `AbstractProviderSession`。
抽象实现会捕获底层异常，并将其转换为统一的 `ProviderSessionException`。

### LookupInfo

`ProviderSession.LookupInfo` 描述提供器实际收到的查询信息，结构如下：

```java
public final class LookupInfo {

    private final String preset;
    private final Object[] objs;

    public LookupInfo(String preset, Object[] objs) {
        this.preset = preset;
        this.objs = objs;
    }

    // getter 省略
}
```

| 字段     | 类型       | 说明                                                       |
|----------|------------|------------------------------------------------------------|
| `preset` | `String`   | 查询预设，用于区分同一提供器支持的不同查询方式。           |
| `objs`   | `Object[]` | 查询参数数组，参数数量、顺序和类型由具体提供器及预设定义。 |

`objs` 中应使用字符串、数字、布尔值、时间戳等简单对象。
不应传递数据库连接、文件句柄、网络连接等无法可靠序列化或跨调用传递的运行资源。

### LookupResult

`ProviderSession.LookupResult` 描述提供器的查询结果，结构如下：

```java
public final class LookupResult {

    private final List<Map<String, Object>> datas;
    private final Map<String, Object> meta;

    public LookupResult(List<Map<String, Object>> datas, Map<String, Object> meta) {
        this.datas = datas;
        this.meta = meta;
    }

    // getter 省略
}
```

| 字段    | 类型                        | 说明                                                       |
|---------|-----------------------------|------------------------------------------------------------|
| `datas` | `List<Map<String, Object>>` | 数据列表，每一个映射表示一条数据。                         |
| `meta`  | `Map<String, Object>`       | 元数据，用于描述数据列表或补充分页、统计和时间范围等信息。 |

`datas` 和 `meta` 不允许为 `null`。
没有查询到数据时应返回空列表，没有元数据时应返回空映射。

数据映射和元数据映射中的值也应使用简单对象，保证后续适配、过滤、序列化和远程调用能够正常处理。

### ProviderMaker

`ProviderMaker` 用于注册和构造一种或多种提供器类型，主要方法如下：

| 方法签名                                           | 说明                           |
|----------------------------------------------------|--------------------------------|
| `boolean supportType(String type)`                 | 判断是否支持指定类型。         |
| `Provider makeProvider(String type, String param)` | 根据类型和构造参数创建提供器。 |

`ProviderHandler` 会从所有 `ProviderMaker` 中查找第一个支持指定类型的制造器，并调用其构造提供器。

### ProviderSupporter

`ProviderSupporter` 用于提供类型支持信息，主要方法如下：

| 方法签名                       | 说明               |
|--------------------------------|--------------------|
| `String provideType()`         | 提供类型标识。     |
| `String provideLabel()`        | 提供显示名称。     |
| `String provideDescription()`  | 提供类型说明。     |
| `String provideExampleParam()` | 提供示例构造参数。 |

SDK 中的 `AbstractProviderRegistry` 同时实现 `ProviderMaker` 和 `ProviderSupporter`，适合用于编写提供器注册。

## 提供器的生命周期

### 构造

提供器采用延迟构造方式。

当系统第一次请求指定 `ProviderInfo.key` 时，提供器本地缓存首先读取对应的 `ProviderInfo`。
如果信息不存在或未启用，本次获取失败；如果信息有效，则根据 `type` 查找 `ProviderMaker`，
并将 `type` 和 `param` 传给制造器。

制造器完成参数解析、脚本编译或依赖获取后返回 `Provider` 实例。
生成的实例进入本地缓存，后续请求可以直接复用。

提供器本身不直接执行查询，其主要职责是保存构造状态并创建 `ProviderSession`。

### 建立会话

系统通过提供器会话持有处理器获取指定主键的会话。

如果会话持有映射中已经存在对应会话，系统直接返回该会话。
如果会话不存在，系统从提供器本地缓存中获取 `Provider`，调用 `newSession()` 创建会话，再调用 `openSession()` 完成初始化。

只有 `openSession()` 成功完成后，会话才会进入持有映射。
如果创建或打开过程发生异常，本次获取失败，不会缓存未成功打开的会话。

具体提供器可以在打开会话时创建数据库连接、初始化客户端、准备脚本状态或分配其它查询资源。

### 执行查询

提供机制将查询请求转换为 `ProviderSession.LookupInfo` 后调用 `lookup()`。

提供器根据 `preset` 选择查询逻辑，根据 `objs` 解析查询参数，并返回 `LookupResult`。
同一会话可以被多次调用，因此实现需要明确会话状态是否可复用，以及查询方法是否允许并发执行。

提供器只负责生成原始 `datas` 和 `meta`。
如果查询链路配置了过滤器，系统会在提供器返回后继续处理数据和元数据，再形成最终的查询结果。

### 关闭会话

提供器会话不会在每次查询完成后立即关闭，而是由系统持有并复用。

执行统一的关闭清理操作时，系统遍历当前持有的全部会话，逐一调用 `closeSession()`，随后清空会话映射。
单个会话关闭失败时，系统记录警告日志，并继续关闭其它会话。

具体实现应在 `closeSession()` 中释放打开阶段创建的连接、客户端、线程、文件或脚本资源。

## 工作机制

### 本地缓存

提供器本地缓存以 `ProviderInfo.key` 为键保存 `Provider` 实例。

缓存未命中时，系统读取 `ProviderInfo` 并构造提供器；缓存命中时直接返回已有实例。
本地缓存避免在每次查询时重复解析参数或编译脚本。

清除提供器本地缓存只会移除 `Provider` 实例，不会自动关闭已经建立并由会话持有处理器保存的 `ProviderSession`。
当配置发生变化并需要重新构造提供器和会话时，需要分别清理会话和本地缓存。

### 会话保持

提供器会话持有处理器以 `ProviderInfo.key` 为键保存 `ProviderSession`。
同一节点内，对同一个提供器信息主键的查询复用同一个会话。

会话持有映射使用互斥锁保护。 会话查找、创建、打开以及统一关闭清理不会并发修改该映射。

持有处理器只保护会话映射本身，不会为具体会话的每次 `lookup()` 自动加锁。
如果会话内部状态不是线程安全的，具体实现需要自行提供并发控制。

### 查询结果

提供器返回的数据列表由若干映射组成，不要求所有提供器使用完全相同的字段。
字段约定由具体提供器类型及查询预设定义。

`meta` 可以携带分页信息、统计信息、查询范围、数据条数、时区或数据源标识等补充内容。
调用方和过滤器不应假设所有提供器都包含同一组元数据字段。

### 异常转换

提供器机制使用统一异常类型隔离底层实现差异：

| 异常                               | 说明                                     |
|------------------------------------|------------------------------------------|
| `UnsupportedProviderTypeException` | 没有制造器支持指定的提供器类型。         |
| `ProviderMakeException`            | 提供器参数解析、脚本编译或实例构造失败。 |
| `ProviderNotExistsException`       | 指定提供器信息不存在或当前不可用。       |
| `ProviderSessionException`         | 会话打开、查询或关闭过程中发生异常。     |

具体实现可以抛出业务异常或运行时异常，SDK 抽象类会将其转换为对应的提供器异常。

## 运维与配置

提供器扩展通过 `opt/opt-provider.xml` 加载。
发行包中的默认文件预留了预设提供器的组件扫描配置，但默认均被注释。

启用对应提供器时，取消相应 `context:component-scan` 的注释，并重启节点使 Spring 重新加载组件。
具体配置方式请参阅 [Opt Directory](./OptDirectory.md)。

Telqos 中与提供器相关的常用命令如下：

| 命令                        | 说明                                                               |
|-----------------------------|--------------------------------------------------------------------|
| `plc -l <provider-info-id>` | 查看指定提供器信息和本地缓存中的提供器实例；缓存不存在时尝试创建。 |
| `plc -c`                    | 清除提供器本地缓存。                                               |
| `ps -l <provider-info-id>`  | 查看指定提供器会话；会话不存在时尝试创建并打开。                   |
| `ps -cnc`                   | 关闭并清除当前节点持有的全部提供器会话。                           |
| `support --reset-provider`  | 根据当前加载的注册器重置 `ProviderSupport`。                       |

`support --reset-provider` 只重置支持信息，不会清除提供器本地缓存，也不会关闭提供器会话。

排查提供器配置问题时，可以分别确认：

1. `opt-provider.xml` 是否加载了对应实现包。
2. `ProviderSupport` 中是否存在对应类型。
3. `ProviderInfo` 是否存在、已启用且类型与参数正确。
4. `plc -l` 是否可以成功构造提供器。
5. `ps -l` 是否可以成功创建并打开会话。
6. 查询使用的 `preset` 和 `objs` 是否符合具体实现约定。

## 参阅

- [Core Concepts](./CoreConcepts.md) - 核心概念，说明部件、处理器、任务、结果等基础对象。
- [Provide](./Provide.md) - 提供机制，详细说明了本项目的提供机制。
- [Opt Directory](./OptDirectory.md) - 可选配置目录说明，详细介绍了本项目的可选配置，即 `opt/` 目录下的内容。
- [Telqos Commands](./TelqosCommands.md) - Telqos 命令，详细说明了本项目的 Telqos 命令。

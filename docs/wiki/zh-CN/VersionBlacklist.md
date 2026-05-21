# Version Blacklist - 版本黑名单

## 说明

本项目的版本黑名单，列出了本项目的版本黑名单，请注意避免使用这些版本。

列入黑名单的版本，可能是因为以下原因：

- 该版本存在严重的 Bug，可能会导致核心功能无法正常使用。
- 该版本存在严重的 Bug，可能会导致数据丢失、数据错误等严重后果。

## 设计目的

该黑名单旨在帮助开发人员快速识别各版本中已知的问题点，明确受影响的模块与典型触发场景，从而做出基于事实的用/避决策与迁移安排，
而不是简单地“一刀切”禁用某一版本。

## 如何使用

黑名单不是绝对禁用清单。遇到被列入黑名单的版本时，应当基于“问题原因—是否命中—影响面”的原则进行研判：

- 是否直接或间接使用了被提及的模块/类（例如：`com.dwarfeng.judge.sdk.bean.entity.FastJsonSinkerMetaIndicator`）。
- 业务中是否存在与“原因”描述相符的顺序敏感或易触发场景（例如：对列表型缓存的严格顺序一致性要求）。
- 是否已出现与问题相符的日志告警、数据核对失败或线上异常案例。

若未使用黑名单中提及的问题代码部分，或经过验证不命中触发条件，则仍然可以继续使用该版本；若命中或存在较大不确定性，
建议参考对应编号“详细原因”章节末尾的迁移建议执行升级或规避方案，并充分做回归与灰度验证。

请注意：迁移建议仅针对对应编号的问题，所推荐版本可能仍存在其他风险。请继续核查该推荐版本是否仍在黑名单中；
若仍在黑名单中，请持续研判并迭代升级，直至风险可接受或升级至未被列入黑名单的版本。

## 版本黑名单

| 编号                                                 | 大版本   | 起始版本    | 结束版本    | 原因                                            |
|----------------------------------------------------|-------|---------|---------|-----------------------------------------------|
| [BLACKLIST-20260521.2](#BLACKLIST-202605212)       | 2.5.x | 2.5.0.a | 2.5.1.a | Task 预设查询 Hibernate 排序字段无法解析，执行相关预设查询时可能报错    |
| [BLACKLIST-20260521.2](#BLACKLIST-202605212)       | 2.4.x | 2.4.0.a | 2.4.3.a | Task 预设查询 Hibernate 排序字段无法解析，执行相关预设查询时可能报错    |
| [BLACKLIST-20260521.2](#BLACKLIST-202605212)       | 2.3.x | 2.3.0.a | 2.3.2.a | Task 预设查询 Hibernate 排序字段无法解析，执行相关预设查询时可能报错    |
| [BLACKLIST-20260521.2](#BLACKLIST-202605212)       | 2.2.x | 2.2.0.a | 2.2.1.a | Task 预设查询 Hibernate 排序字段无法解析，执行相关预设查询时可能报错    |
| [BLACKLIST-20260514.1](#BLACKLIST-202605141)       | 2.5.x | 2.5.0.a | 2.5.0.a | JSFixedFastJson 实体字段注解错误，可能导致 JSON 序列化/反序列化问题 |
| [BLACKLIST-20260514.1](#BLACKLIST-202605141)       | 2.4.x | 2.4.0.a | 2.4.3.a | JSFixedFastJson 实体字段注解错误，可能导致 JSON 序列化/反序列化问题 |
| [BLACKLIST-20260514.1](#BLACKLIST-202605141)       | 2.3.x | 2.3.0.a | 2.3.2.a | JSFixedFastJson 实体字段注解错误，可能导致 JSON 序列化/反序列化问题 |
| [BLACKLIST-20260514.1](#BLACKLIST-202605141)       | 2.2.x | 2.2.0.a | 2.2.1.a | JSFixedFastJson 实体字段注解错误，可能导致 JSON 序列化/反序列化问题 |
| [BLACKLIST-20251221.1](#BLACKLIST-202512211)       | 2.4.x | 2.4.0.a | 2.4.0.a | Dubbo 服务注册缺失，可能导致部分服务无法通过 RPC 调用              |
| [BLACKLIST-20251221.1](#BLACKLIST-202512211)       | 2.3.x | 2.3.0.a | 2.3.2.a | Dubbo 服务注册缺失，可能导致部分服务无法通过 RPC 调用              |
| [BLACKLIST-20251221.1](#BLACKLIST-202512211)       | 2.2.x | 2.2.0.a | 2.2.1.a | Dubbo 服务注册缺失，可能导致部分服务无法通过 RPC 调用              |
| [BLACKLIST-20251210.1](#BLACKLIST-202512101)       | 2.3.x | 2.3.0.a | 2.3.0.a | FastJson 实体字段注解错误，可能导致 JSON 序列化/反序列化问题        |
| [BLACKLIST-20251210.1](#BLACKLIST-202512101)       | 2.2.x | 2.2.0.a | 2.2.1.a | FastJson 实体字段注解错误，可能导致 JSON 序列化/反序列化问题        |
| [BLACKLIST-20251210.1](#BLACKLIST-202512101)       | 2.1.x | 2.1.0.a | 2.1.0.a | FastJson 实体字段注解错误，可能导致 JSON 序列化/反序列化问题        |
| [BLACKLIST-LEGACY-2.2.0.a](#BLACKLIST-LEGACY-220a) | 2.2.x | 2.2.0.a | 2.2.0.a | 核心功能无法正常使用                                    |
| [BLACKLIST-LEGACY-2.1.0.a](#BLACKLIST-LEGACY-210a) | 2.1.x | 2.1.0.a | 2.1.0.a | Beta 版本，与现行接口差异较大                             |
| [BLACKLIST-LEGACY-2.0.0.a](#BLACKLIST-LEGACY-200a) | 2.0.x | 2.0.0.a | 2.0.0.a | Beta 版本，与现行接口差异较大                             |

## 详细原因

### BLACKLIST-20260521.2

原因：`TaskPresetCriteriaMaker` 在按任务创建时间降序排序时，
Hibernate Criteria 使用了实体上不存在的属性名 `createDate`（实体与 Hibernate 映射的实际属性为 `createdDate`）。
执行相应预设查询时，Hibernate 无法解析该排序属性，进而导致查询失败。

- 受影响模块/类：
  - `com.dwarfeng.judge.stack.service.TaskMaintainService`
  - `com.dwarfeng.judge.impl.dao.preset.TaskPresetCriteriaMaker`
- 典型触发条件：
  - 通过 `TaskMaintainService` 的预设查询传入预设字符串 `create_date_desc`（自 `2.0.0.a` 起）；
  - 通过 `TaskMaintainService` 的预设查询传入预设字符串 `child_for_section_create_date_desc`（自 `2.0.0.a` 起）；
  - 通过 `TaskMaintainService` 的预设查询传入预设字符串 `status_eq_create_date_desc`（自 `2.3.2.a` 起）。
- 典型症状：
  - 预设查询执行失败，日志或异常中出现 Hibernate 无法解析属性 `createDate`（或等价表述）；
  - 依赖上述预设的任务列表、按区段筛选的任务列表、按状态筛选并排序等 UI 或接口功能不可用。
- 影响范围：
  - 仅影响使用上述预设字符串的查询路径。
  - 不影响 `TaskMaintainService` 的其它预设，及不涉及这些预设的 CRUD 操作。

迁移建议：升级至 2.5.2.a 及以上版本。

### BLACKLIST-20260514.1

原因：`JSFixedFastJsonSinkerRelationKey` 中 `sinkerLongId` 字段的 `@JSONField`
缺少 `serializeUsing = ToStringSerializer.class`， 与同类的 `sectionLongId` 不一致。
FastJson 序列化时 `section_id` 以字符串输出、`sinker_id` 以数字输出；
在 JavaScript 等运行环境中将 `sinker_id` 按 JSON 数字解析时，受双精度浮点精度限制，无法精确表示 Java `Long` 范围内的整型值，
较大 ID 会发生舍入或失真，进而导致主键不准确、关联查询或更新指向错误对象等严重问题。
还有可能因为字段格式与同契约下其它 JSFixed 长整型 ID 不一致，导致对接方严格格式校验失败，属于同一缺陷下的次要表现。

- 受影响模块/类：
  - `com.dwarfeng.judge.sdk.bean.key.JSFixedFastJsonSinkerRelationKey`
  - `com.dwarfeng.judge.sdk.bean.entity.JSFixedFastJsonSinkerRelation`
  - `com.dwarfeng.judge.sdk.bean.BeanMapper`（涉及上述类型的 JSFixed 转换方法）
- 典型触发条件：
  - 使用 FastJson 序列化/反序列化 `JSFixedFastJsonSinkerRelationKey` 或嵌套该主键的 `JSFixedFastJsonSinkerRelation`；
  - 通过 HTTP API 等传输含 `key.section_id` / `key.sinker_id` 的 JSON；
  - 对接方假定 JSFixed 长整型 ID 字段统一以字符串形式出现（避免 JavaScript 精度问题）。
- 典型症状：
  - JavaScript 客户端将 `sinker_id` 解析为 Number 时发生精度丢失，得到的 ID 与服务器实际值不一致；
  - 基于失真 ID 的查询、绑定、更新等操作命中错误数据或失败，可能导致数据错误等严重后果；
  - 序列化 JSON 中 `section_id` 为字符串、`sinker_id` 为数字，与同实体其它 JSFixed 长整型 ID 字段的约定不一致；
  - 对接方若对字段类型或格式做严格校验，可能报错或拒绝请求。
- 影响范围：
  - 直接或间接对上述类型进行 JSFixed FastJson 序列化/反序列化的功能与接口。
  - Redis 缓存路径使用 `FastJsonSinkerRelation`，不经过该 JSFixed 主键类，通常不命中此问题。

迁移建议：升级至 2.5.1.a 及以上版本。

### BLACKLIST-20251221.1

原因：`application-context-dubbo.xml` 中缺失 `JudgementMaintainService` 的 Dubbo 服务注册配置。

- 受影响模块/类：
  - `com.dwarfeng.judge.stack.service.JudgementMaintainService`
  - `judge-node/src/main/resources/spring/application-context-dubbo.xml`
- 典型触发条件：
  - 通过 Dubbo RPC 调用 `JudgementMaintainService` 接口；
  - 使用 Dubbo 服务发现机制查找 `JudgementMaintainService` 服务。
- 典型症状：
  - 无法通过 Dubbo 调用 `JudgementMaintainService` 的方法；
  - 服务注册中心中找不到 `JudgementMaintainService` 服务；
  - RPC 调用失败，抛出服务未找到异常。
- 影响范围：
  - 所有需要通过 Dubbo RPC 调用 `JudgementMaintainService` 的功能与接口。

迁移建议：升级至 2.4.1.a 及以上版本。

### BLACKLIST-20251210.1

原因：FastJson 实体字段注解错误，`defaultValue` 字段的 `@JSONField` 注解 name 属性错误。

- 受影响模块/类：
  - `com.dwarfeng.judge.sdk.bean.entity.FastJsonSinkerMetaIndicator`
- 典型触发条件：
  - 使用 FastJson 序列化/反序列化 `FastJsonSinkerMetaIndicator` 对象；
  - 通过 HTTP API 传输包含 `defaultValue` 字段的数据。
- 典型症状：
  - JSON 序列化时字段名错误（`initial_value` 而非 `default_value`）；
  - JSON 反序列化时字段无法正确映射；
  - API 接口数据不一致。
- 影响范围：
  - 直接或间接使用 `FastJsonSinkerMetaIndicator` 进行 JSON 序列化/反序列化的功能与接口。

迁移建议：升级至 2.3.1.a 及以上版本。

### BLACKLIST-LEGACY-2.2.0.a

详细版本号: Release_2.2.0_20250920_build_A。

原因： 存在严重 bug，导致核心功能无法正常使用。

- 作业完成后未取消心跳任务。
- 部分 Hibernate 实体中的字段缺失。

迁移建议：升级至 2.2.1.a 及以上版本。

### BLACKLIST-LEGACY-2.1.0.a

详细版本号: Beta_2.1.0_20250918_build_A。

原因： Beta 版本，与现行接口差异较大。

- 修复错误的方法、字段、变量拼写，造成接口不兼容。
  - `ayalysises` -> `analyses`。

迁移建议：升级至 2.1.0.a 及以上版本（正式版）。

### BLACKLIST-LEGACY-2.0.0.a

详细版本号: Beta_2.1.0_20250915_build_A。

原因： Beta 版本，与现行接口差异较大。

- 存在与现行版本差异较大的报警机制。
  - 该机制在后续版本中被整体移除。

迁移建议：升级至 2.0.0.a 及以上版本（正式版）。

## 注意事项

- 黑名单用于风险提示，并非强制禁用。是否升级或规避，应以是否命中“详细原因”所述的模块与场景为依据。
- 建议遵循生产变更流程：评估 -> 测试 -> 灰度 -> 观测 -> 全量，过程中启用充分的日志与指标监控。
- 如发现新问题或修复版本，请同步更新本黑名单与变更记录，保持信息一致性。

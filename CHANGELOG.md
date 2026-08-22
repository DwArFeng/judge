# ChangeLog

## Release_3.2.1_20260822_build_A

### 功能构建

- `judge-node-all-he` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.judge.node.all.he.configuration.CacheConfiguration。
  - com.dwarfeng.judge.node.all.he.configuration.DaoConfiguration。
  - com.dwarfeng.judge.node.all.he.configuration.ServiceConfiguration。

- `judge-impl` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.judge.impl.configuration.CacheConfiguration。
  - com.dwarfeng.judge.impl.bean.entity.HibernateAlarmHistory。
  - com.dwarfeng.judge.impl.bean.entity.HibernateAlarm。
  - com.dwarfeng.judge.impl.bean.entity.HibernateVerdict。
  - com.dwarfeng.judge.impl.bean.entity.HibernateAlarmTypeIndicator。
  - com.dwarfeng.judge.impl.configuration.ServiceConfiguration。
  - com.dwarfeng.judge.impl.dao.preset.AlarmModalPresetCriteriaMaker。
  - com.dwarfeng.judge.impl.dao.preset.AlarmPresetCriteriaMaker。
  - com.dwarfeng.judge.impl.configuration.DaoConfiguration。
  - com.dwarfeng.judge.impl.bean.entity.HibernateAlarmModal。

- 依赖升级。
  - 升级 `fastjson` 依赖版本为 `1.2.84` 以规避漏洞。
  - 升级 `aspectj` 依赖版本为 `1.8.14` 以规避漏洞。
  - 升级 `jackson` 依赖版本为 `2.21.5` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.8.4.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `2.0.3.a` 以规避漏洞。

### Bug 修复

- 修复任务检查时死亡任务状态转移的逻辑问题。
  - com.dwarfeng.judge.impl.handler.TaskCheckHandlerImpl。

- 修复任务在特定的状态转移时行为异常的 bug。
  - com.dwarfeng.judge.impl.handler.TaskOperateHandlerImpl。

### 功能移除

- (无)

---

## Release_3.2.0_20260725_build_A

### 功能构建

- Wiki 编写。
  - docs/wiki/zh-CN/Judger.md。
  - docs/wiki/zh-CN/PresetJudgerImplements.md。

- Wiki 更新。
  - docs/wiki/zh-CN/Provide.md。
  - docs/wiki/zh-CN/SystemRequirements.md。
  - docs/wiki/zh-CN/VersionBlacklist.md。
  - docs/wiki/zh-CN/Analyser.md。
  - docs/wiki/zh-CN/CoreConcepts.md。

- 推送机制新增系统事件。
  - com.dwarfeng.judge.stack.handler.PushHandler.alarmAppeared。
  - com.dwarfeng.judge.stack.handler.PushHandler.alarmLevelIncreased。
  - com.dwarfeng.judge.stack.handler.PushHandler.alarmLevelDecreased。
  - com.dwarfeng.judge.stack.handler.PushHandler.alarmDisappeared。

- 增加预设的运维指令。
  - com.dwarfeng.judge.impl.service.telqos.AlarmModalCommand。
  - com.dwarfeng.judge.impl.service.telqos.VerdictCommand。

- 实现核心机制。
  - 报警机制。

- 新建实体以及维护服务，并通过单元测试。
  - com.dwarfeng.judge.stack.bean.entity.Alarm。
  - com.dwarfeng.judge.stack.bean.entity.AlarmHistory。
  - com.dwarfeng.judge.stack.bean.entity.AlarmModal。
  - com.dwarfeng.judge.stack.bean.entity.AlarmSetting。
  - com.dwarfeng.judge.stack.bean.entity.AlarmTypeIndicator。
  - com.dwarfeng.judge.stack.bean.entity.Verdict。

- `judge-node-all-he` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.judge.node.all.he.configuration.CacheConfiguration。
  - com.dwarfeng.judge.node.all.he.configuration.DaoConfiguration。

- `judge-impl` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.judge.impl.bean.BeanMapper。
  - com.dwarfeng.judge.impl.dao.SinkerMetaIndicatorDaoImpl。
  - com.dwarfeng.judge.impl.handler.analyser.valllstset.ValueLookupListSetAnalyserConfig。
  - com.dwarfeng.judge.impl.handler.judger.identity.IdentityExecutor。
  - com.dwarfeng.judge.impl.handler.provider.date.DateProviderSession。
  - com.dwarfeng.judge.impl.handler.sinker.kafka.nati.NativeKafkaSinkerSession。
  - com.dwarfeng.judge.impl.service.AdapterSupportMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.FilterSupportMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.ProviderInfoMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.ProviderSupportMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.SinkerMetaIndicatorMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.SinkerRelationMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.SinkerSupportMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.telqos.ProvideCommand。
  - com.dwarfeng.judge.impl.configuration.CacheConfiguration。
  - com.dwarfeng.judge.impl.configuration.DaoConfiguration。
  - com.dwarfeng.judge.impl.service.SinkerRelationMaintainServiceImplLookupTest。

- `judge-sdk` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.judge.sdk.bean.BeanMapper。
  - com.dwarfeng.judge.sdk.bean.dto.JSFixedFastJsonSinkInfo。
  - com.dwarfeng.judge.sdk.bean.entity.JSFixedFastJsonAnalysisFileInfo。
  - com.dwarfeng.judge.sdk.bean.entity.JSFixedFastJsonAnalysisPictureInfo。

- `judge-stack` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.judge.stack.dao.ProviderSupportDao。
  - com.dwarfeng.judge.stack.dao.SinkerMetaIndicatorDao。
  - com.dwarfeng.judge.stack.dao.SinkerRelationDao。
  - com.dwarfeng.judge.stack.service。
  - com.dwarfeng.judge.stack.service.ProviderSessionQosService。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Release_3.1.0_20260720_build_A

### 功能构建

- Wiki 编写。
  - docs/wiki/zh-CN/Analyser.md。
  - docs/wiki/zh-CN/PresetAnalyserImplements.md。

- Wiki 更新。
  - docs/wiki/zh-CN/ConfDirectory.md。
  - docs/wiki/zh-CN/Provider.md。
  - docs/wiki/zh-CN/QuickStart.md。
  - docs/wiki/zh-CN/TelqosCommands.md。

- 新增 telqos 指令。
  - com.dwarfeng.judge.impl.service.telqos.ProvideCommand。

- 新增服务。
  - com.dwarfeng.judge.stack.service.ProvideService。

- 优化核心机制。
  - 推送机制。
  - 重置机制。
  - 判断机制。
  - 可视化机制。

- 重构核心机制。
  - 提供机制。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Release_3.0.1_20260718_build_A

### 功能构建

- Wiki 编写。
  - docs/wiki/zh-CN/Provider.md。
  - docs/wiki/zh-CN/PresetProviderImplements.md。

- 优化核心机制。
  - 重置机制。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Release_3.0.0_20260716_build_A

### 功能构建

- Wiki 编写。
  - docs/wiki/zh-CN/Provide.md。

- Wiki 更新。
  - docs/wiki/zh-CN/QuickStart.md。
  - docs/wiki/zh-CN/OptDirectory.md。
  - docs/wiki/zh-CN/ConfDirectory.md。
  - docs/wiki/zh-CN/CompileBySource.md。

- `judge-distribute` 模块新增。
  - 新增 `judge-distribute` 模块，负责各个构型的产物分发。

- `judge-node` 模块重构。
  - 将 `judge-node` 调整为聚合模块。
  - 新建 `judge-node-all-he` 模块，迁移项目原有内容，并形成项目的 `he` 构型。
  - 其它相关文件路径、包路径、配置路径等同步调整。

- 配置命名空间全局唯一化改造。
  - 为 `judge-impl` 模块配置键统一增加全球唯一前缀，以消除跨微服务同名配置冲突。
  - 为 `judge-node` 模块配置键统一增加全球唯一前缀，以消除跨微服务同名配置冲突。
  - 处理 `redis/prefix.properties` 缓存前缀配置的实体命名空间。
  - 同步调整 `*.java` 中的配置读取占位符。
  - 同步调整 `*application-context-*.xml` 中的配置读取占位符。

- 部分代理类实现中的字段类型提升为对应的接口，与具体实现解耦。
  - com.dwarfeng.judge.impl.cache.AdapterInfoCacheImpl。
  - com.dwarfeng.judge.impl.cache.AdapterSupportCacheImpl。
  - com.dwarfeng.judge.impl.cache.AnalyserInfoCacheImpl。
  - com.dwarfeng.judge.impl.cache.AnalyserSupportCacheImpl。
  - com.dwarfeng.judge.impl.cache.AnalyserVariableCacheImpl。
  - com.dwarfeng.judge.impl.cache.AnalysisCacheImpl。
  - com.dwarfeng.judge.impl.cache.AnalysisFileInfoCacheImpl。
  - com.dwarfeng.judge.impl.cache.AnalysisFilePackCacheImpl。
  - com.dwarfeng.judge.impl.cache.AnalysisFilePackItemInfoCacheImpl。
  - com.dwarfeng.judge.impl.cache.AnalysisPictureInfoCacheImpl。
  - com.dwarfeng.judge.impl.cache.AnalysisPicturePackCacheImpl。
  - com.dwarfeng.judge.impl.cache.AnalysisPicturePackItemInfoCacheImpl。
  - com.dwarfeng.judge.impl.cache.DriverInfoCacheImpl。
  - com.dwarfeng.judge.impl.cache.DriverSupportCacheImpl。
  - com.dwarfeng.judge.impl.cache.FilterInfoCacheImpl。
  - com.dwarfeng.judge.impl.cache.FilterSupportCacheImpl。
  - com.dwarfeng.judge.impl.cache.JudgementCacheImpl。
  - com.dwarfeng.judge.impl.cache.JudgerInfoCacheImpl。
  - com.dwarfeng.judge.impl.cache.JudgerSupportCacheImpl。
  - com.dwarfeng.judge.impl.cache.JudgerVariableCacheImpl。
  - com.dwarfeng.judge.impl.cache.ProviderInfoCacheImpl。
  - com.dwarfeng.judge.impl.cache.ProviderSupportCacheImpl。
  - com.dwarfeng.judge.impl.cache.SectionCacheImpl。
  - com.dwarfeng.judge.impl.cache.SinkerInfoCacheImpl。
  - com.dwarfeng.judge.impl.cache.SinkerMetaCacheImpl。
  - com.dwarfeng.judge.impl.cache.SinkerMetaIndicatorCacheImpl。
  - com.dwarfeng.judge.impl.cache.SinkerRelationCacheImpl。
  - com.dwarfeng.judge.impl.cache.SinkerSupportCacheImpl。
  - com.dwarfeng.judge.impl.cache.SinkerVariableCacheImpl。
  - com.dwarfeng.judge.impl.cache.TaskCacheImpl。
  - com.dwarfeng.judge.impl.cache.TaskEventCacheImpl。
  - com.dwarfeng.judge.impl.cache.VisualizeDataCacheImpl。
  - com.dwarfeng.judge.impl.cache.VisualizerInfoCacheImpl。
  - com.dwarfeng.judge.impl.cache.VisualizerSupportCacheImpl。
  - com.dwarfeng.judge.impl.dao.AdapterInfoDaoImpl。
  - com.dwarfeng.judge.impl.dao.AdapterSupportDaoImpl。
  - com.dwarfeng.judge.impl.dao.AnalyserInfoDaoImpl。
  - com.dwarfeng.judge.impl.dao.AnalyserSupportDaoImpl。
  - com.dwarfeng.judge.impl.dao.AnalyserVariableDaoImpl。
  - com.dwarfeng.judge.impl.dao.AnalysisDaoImpl。
  - com.dwarfeng.judge.impl.dao.AnalysisFileInfoDaoImpl。
  - com.dwarfeng.judge.impl.dao.AnalysisFilePackDaoImpl。
  - com.dwarfeng.judge.impl.dao.AnalysisFilePackItemInfoDaoImpl。
  - com.dwarfeng.judge.impl.dao.AnalysisPictureInfoDaoImpl。
  - com.dwarfeng.judge.impl.dao.AnalysisPicturePackDaoImpl。
  - com.dwarfeng.judge.impl.dao.AnalysisPicturePackItemInfoDaoImpl。
  - com.dwarfeng.judge.impl.dao.DriverInfoDaoImpl。
  - com.dwarfeng.judge.impl.dao.DriverSupportDaoImpl。
  - com.dwarfeng.judge.impl.dao.FilterInfoDaoImpl。
  - com.dwarfeng.judge.impl.dao.FilterSupportDaoImpl。
  - com.dwarfeng.judge.impl.dao.JudgementDaoImpl。
  - com.dwarfeng.judge.impl.dao.JudgerInfoDaoImpl。
  - com.dwarfeng.judge.impl.dao.JudgerSupportDaoImpl。
  - com.dwarfeng.judge.impl.dao.JudgerVariableDaoImpl。
  - com.dwarfeng.judge.impl.dao.ProviderInfoDaoImpl。
  - com.dwarfeng.judge.impl.dao.ProviderSupportDaoImpl。
  - com.dwarfeng.judge.impl.dao.SectionDaoImpl。
  - com.dwarfeng.judge.impl.dao.SinkerInfoDaoImpl。
  - com.dwarfeng.judge.impl.dao.SinkerMetaDaoImpl。
  - com.dwarfeng.judge.impl.dao.SinkerMetaIndicatorDaoImpl。
  - com.dwarfeng.judge.impl.dao.SinkerRelationDaoImpl。
  - com.dwarfeng.judge.impl.dao.SinkerSupportDaoImpl。
  - com.dwarfeng.judge.impl.dao.SinkerVariableDaoImpl。
  - com.dwarfeng.judge.impl.dao.TaskDaoImpl。
  - com.dwarfeng.judge.impl.dao.TaskEventDaoImpl。
  - com.dwarfeng.judge.impl.dao.VisualizeDataDaoImpl。
  - com.dwarfeng.judge.impl.dao.VisualizerInfoDaoImpl。
  - com.dwarfeng.judge.impl.dao.VisualizerSupportDaoImpl。
  - com.dwarfeng.judge.impl.service.AdapterInfoMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.AdapterSupportMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.AnalyserInfoMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.AnalyserSupportMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.AnalyserVariableMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.AnalysisFileInfoMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.AnalysisFilePackItemInfoMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.AnalysisFilePackMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.AnalysisMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.AnalysisPictureInfoMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.AnalysisPicturePackItemInfoMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.AnalysisPicturePackMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.DriverInfoMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.DriverSupportMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.FilterInfoMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.FilterSupportMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.JudgementMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.JudgerInfoMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.JudgerSupportMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.JudgerVariableMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.ProviderInfoMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.ProviderSupportMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.SectionMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.SinkerInfoMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.SinkerMetaIndicatorMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.SinkerMetaMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.SinkerRelationMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.SinkerSupportMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.SinkerVariableMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.TaskEventMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.TaskMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.VisualizeDataMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.VisualizerInfoMaintainServiceImpl。
  - com.dwarfeng.judge.impl.service.VisualizerSupportMaintainServiceImpl。

- 优化项目的异常处理机制。
  - `judge-sdk` 子模块新增 `ServiceExceptionHelper` 工具类，统一维护项目自身的异常映射关系。
  - `judge-impl` 子模块 `ServiceExceptionMapperConfiguration` 配置类的异常映射处理逻辑优化。
  - `judge-node` 子模块 `ServiceExceptionMapperConfiguration` 配置类的异常映射处理逻辑优化。

- 依赖升级。
  - 升级 `spring-telqos` 依赖版本为 `2.0.2.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `spring-terminator` 依赖版本为 `2.0.2.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `dwarfeng-datamark` 依赖版本为 `2.2.0.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `dwarfeng-ftp` 依赖版本为 `2.1.0.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `dcti` 依赖版本为 `3.0.1.a` 并解决兼容性问题，以应用其新功能。
  - 增加依赖 `snowflake` 以应用其新功能，版本为 `2.0.2.a`。

- 优化文件格式。
  - 优化 `assembly.xml` 文件的格式。
  - 优化 `*.properties` 文件的格式。
  - 优化 `opt-*.xml` 文件的格式。
  - 优化 `application-context-*.xml` 文件的格式。
  - 优化 `pom.xml` 文件的格式。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## 更早的版本

[View all changelogs](./changelogs)

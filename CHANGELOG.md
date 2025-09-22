# ChangeLog

## Release_2.2.1_20250921_build_A

### 功能构建

- Wiki 编写。
  - docs/wiki/zh-CN/CompileBySource.md。

- Wiki 更新。
  - docs/wiki/zh-CN/VersionBlacklist.md。

- 为提供器相关实体增加数据标记机制。
  - com.dwarfeng.judge.impl.bean.entity.HibernateProviderInfo。

- 优化数据标记机制的部分处理器的关键字，使其与其它处理器保持一致。

- 优化部分本地缓存处理器的存在性判定逻辑，以规避潜在的非使能实体抓取问题。
  - com.dwarfeng.judge.impl.handler.DriveLocalCacheHandlerImpl。
  - com.dwarfeng.judge.impl.handler.JobLocalCacheHandlerImpl。
  - com.dwarfeng.judge.impl.handler.ProviderLocalCacheHandlerImpl。
  - com.dwarfeng.judge.impl.handler.SectionBindingLocalCacheHandlerImpl。
  - com.dwarfeng.judge.impl.handler.SinkerBindingLocalCacheHandlerImpl。
  - com.dwarfeng.judge.impl.handler.SinkerLocalCacheHandlerImpl。

- 优化部分本地缓存处理器的抓取逻辑，以规避潜在的非使能实体抓取问题。
  - com.dwarfeng.judge.impl.handler.SectionBindingLocalCacheHandlerImpl。
  - com.dwarfeng.judge.impl.handler.SinkerBindingLocalCacheHandlerImpl。

- 完善部分本地缓存处理器的文档注释。
  - com.dwarfeng.judge.stack.handler.DriveLocalCacheHandler。
  - com.dwarfeng.judge.stack.handler.JobLocalCacheHandler。
  - com.dwarfeng.judge.stack.handler.ProviderLocalCacheHandler。
  - com.dwarfeng.judge.stack.handler.SectionBindingLocalCacheHandler。
  - com.dwarfeng.judge.stack.handler.SinkerBindingLocalCacheHandler。
  - com.dwarfeng.judge.stack.handler.SinkerLocalCacheHandler。

- 完善部分 struct 结构体的文档注释。
  - com.dwarfeng.judge.stack.struct.SectionBinding。
  - com.dwarfeng.judge.stack.struct.SinkerBinding。

- 优化部分 DTO 的文档注释。
  - com.dwarfeng.judge.stack.bean.dto.DataLookupInfo。
  - com.dwarfeng.judge.stack.bean.dto.DataLookupResult。

### Bug 修复

- 修正部分下沉器中的逻辑错误。
  - 修正 `kafka.native` 下沉器 `opt` 目录内配置文件缺失的问题。
  - 修正 `kafka.native` 下沉器常量类中的错误。

- 修正部分判断器中的逻辑错误。
  - 修正 `groovy` 判断器 `groovy/ExampleJudgerProcessor.groovy` 中生成的判断值越界的问题。

- 作业逻辑 bug 修复。
  - 修复作业完成后未取消心跳任务的 bug。

- 修复部分 Hibernate 实体中的字段缺失 bug。
  - com.dwarfeng.judge.impl.bean.entity.HibernateJudgerInfo。

### 功能移除

- (无)

---

## Release_2.2.0_20250920_build_A

### 功能构建

- 更新 README.md。

- Wiki 更新。
  - docs/wiki/zh-CN/BatchScripts.md。
  - docs/wiki/zh-CN/ShellScripts.md。
  - docs/wiki/zh-CN/VersionBlacklist.md。
  - docs/wiki/zh-CN/Introduction.md。

- 增加部分实体维护服务的预设查询。
  - com.dwarfeng.judge.stack.service.TaskMaintainService.TO_PURGED。
  - com.dwarfeng.judge.stack.service.ProviderSupportMaintainService.ID_LIKE。
  - com.dwarfeng.judge.stack.service.ProviderSupportMaintainService.LABEL_LIKE。

- 优化作业逻辑。
  - 关键作业节点开始和结束时更新任务模态，并创建任务事件。
  - 新增可视化数据下沉。

- 实现预设可视化器。
  - com.dwarfeng.judge.impl.handler.visualizer.GroovyVisualizerRegistry。

- 实现核心机制。
  - 清除机制。
  - 可视化机制。

- 新建实体以及维护服务，并通过单元测试。
  - com.dwarfeng.judge.stack.bean.entity.VisualizeData。
  - com.dwarfeng.judge.stack.bean.entity.VisualizerInfo。
  - com.dwarfeng.judge.stack.bean.entity.VisualizerSupport。

- 优化部分接口方法的名称，使其更加符合方法的功能。
  - `Analyser.Context.provide` -> `Analyser.Context.lookupData`。
  - `ProvideHandler.provide` -> `ProvideHandler.lookupData`。
  - `ProviderSession.provide` -> `ProviderSession.lookupData`。

- 实现运维指令。
  - com.dwarfeng.judge.impl.service.telqos.PurgeCommand。
  - com.dwarfeng.judge.impl.service.telqos.ConsumeCommand。

### Bug 修复

- 修正代码文档注释中错误的版本标记。
  - `@since 2.1.0` -> `@since 2.1.0-beta`。

- 修复错误的方法、字段、变量拼写。
  - `ayalysises` -> `analyses`。

- 修复 CHANGELOG.md 中意外添加的字符。

### 功能移除

- (无)

---

## Beta_2.1.0_20250918_build_A

### 功能构建

- Wiki 编写。
  - docs/wiki/zh-CN/SystemRequirements.md。

- Wiki 更新。
  - docs/wiki/zh-CN/Contents.md。
  - docs/wiki/zh-CN/VersionBlacklist.md。

- 优化分析机制。
  - 为分析器上下文增加提供数据方法，将数据获取功能委托给提供器，解除分析器与数据获取功能的耦合。

- 实现运维指令。
  - com.dwarfeng.judge.impl.service.telqos.SinkerBindingLocalCacheCommand。
  - com.dwarfeng.judge.impl.service.telqos.SinkerLocalCacheCommand。
  - com.dwarfeng.judge.impl.service.telqos.SinkerSessionCommand。

- 实现预设提供器。
  - com.dwarfeng.judge.impl.handler.provider.groovy.GroovyProviderRegistry。

- 实现预设下沉器。
  - com.dwarfeng.judge.impl.handler.sinker.kafka.nati.NativeKafkaSinkerRegistry。
  - com.dwarfeng.judge.impl.handler.sinker.mock.MockSinkerRegistry。

- 实现核心机制。
  - 提供机制。
  - 下沉机制。

- 新增实体操作服务。
  - com.dwarfeng.judge.stack.service.SinkerMetaOperateService。

- 建立实体以及维护服务，并通过单元测试。
  - com.dwarfeng.judge.stack.bean.entity.ProviderInfo。
  - com.dwarfeng.judge.stack.bean.entity.ProviderSupport。
  - com.dwarfeng.judge.stack.bean.entity.SinkerInfo。
  - com.dwarfeng.judge.stack.bean.entity.SinkerMeta。
  - com.dwarfeng.judge.stack.bean.entity.SinkerMetaIndicator。
  - com.dwarfeng.judge.stack.bean.entity.SinkerRelation。
  - com.dwarfeng.judge.stack.bean.entity.SinkerSupport。
  - com.dwarfeng.judge.stack.bean.entity.SinkerVariable。

- 优化作业逻辑。
  - 分析判断完毕后，使用下沉器下沉当前任务对应的下沉信息。
  - 调整作业过程中判断器的使用方式，由单独使用变为联合使用。

- 调整判断机制。
  - 优化判断器接口方法，使判断器支持多维度判断。
  - 调整对应的预设判断器实现。

- 新建实体以及维护服务，并通过单元测试。
  - com.dwarfeng.judge.stack.service.JudgementMaintainService。

### Bug 修复

- 修复部分重置器中缺失的主管功能重置逻辑。
  - com.dwarfeng.judge.impl.handler.resetter.CronResetter。
  - com.dwarfeng.judge.impl.handler.resetter.DubboResetter。
  - com.dwarfeng.judge.impl.handler.resetter.FixedDelayResetter。
  - com.dwarfeng.judge.impl.handler.resetter.FixedRateResetter。

- 优化部分处理器实现的构造器方法，以规避潜在的空指针异常。
  - com.dwarfeng.judge.impl.handler.SupportHandlerImpl。

- 修正代码文档注释中错误的版本标记。
  - `@since 2.0.0` -> `@since 2.0.0-beta`。

### 功能移除

- 移除实体及其维护服务。
  - com.dwarfeng.judge.stack.bean.entity.JudgementHistory。
  - com.dwarfeng.judge.stack.bean.entity.JudgementModal。
  - com.dwarfeng.judge.stack.bean.entity.AlarmHistory。
  - com.dwarfeng.judge.stack.bean.entity.AlarmLevelIndicator。
  - com.dwarfeng.judge.stack.bean.entity.AlarmModal。
  - com.dwarfeng.judge.stack.bean.entity.AlarmSetting。

- 移除功能。
  - 报警功能的耦合会使该服务的泛用性大幅减少，故移除相关功能。

---

## Beta_2.0.0_20250915_build_A

### 功能构建

- Wiki 编写。
  - docs/wiki/zh_CN/UsingTelqos.md。

- 实现运维指令。
  - com.dwarfeng.judge.impl.service.telqos.SuperviseCommand。
  - com.dwarfeng.judge.impl.service.telqos.DriveCommand。
  - com.dwarfeng.judge.impl.service.telqos.DriveLocalCacheCommand。
  - com.dwarfeng.judge.impl.service.telqos.DispatchCommand。
  - com.dwarfeng.judge.impl.service.telqos.DispatcherCommand。
  - com.dwarfeng.judge.impl.service.telqos.ReceiveCommand。
  - com.dwarfeng.judge.impl.service.telqos.ReceiverCommand。
  - com.dwarfeng.judge.impl.service.telqos.TaskCheckCommand。
  - com.dwarfeng.judge.impl.service.telqos.JobLocalCacheCommand。

- 实现预设驱动器。
  - com.dwarfeng.judge.impl.handler.driver.CronDriverProvider。
  - com.dwarfeng.judge.impl.handler.driver.CronDriverSupporter。
  - com.dwarfeng.judge.impl.handler.driver.DctiKafkaDriverProvider。
  - com.dwarfeng.judge.impl.handler.driver.DctiKafkaDriverSupporter。
  - com.dwarfeng.judge.impl.handler.driver.FixedDelayDriverProvider。
  - com.dwarfeng.judge.impl.handler.driver.FixedDelayDriverSupporter。
  - com.dwarfeng.judge.impl.handler.driver.FixedRateDriverProvider。
  - com.dwarfeng.judge.impl.handler.driver.FixedRateDriverSupporter。

- 实现预设调度器。
  - com.dwarfeng.judge.impl.handler.dispatcher.DrainDispatcher。
  - com.dwarfeng.judge.impl.handler.dispatcher.DubboDispatcher。
  - com.dwarfeng.judge.impl.handler.dispatcher.InjvmDispatcher。
  - com.dwarfeng.judge.impl.handler.dispatcher.KafkaDispatcher。

- 实现预设接收器。
  - com.dwarfeng.judge.impl.handler.receiver.DoNothingReceiver。
  - com.dwarfeng.judge.impl.handler.receiver.DubboReceiver。
  - com.dwarfeng.judge.impl.handler.receiver.InjvmReceiver。
  - com.dwarfeng.judge.impl.handler.receiver.KafkaReceiver。

- 实现预设判断器。
  - com.dwarfeng.judge.impl.handler.judger.groovy.GroovyJudgerRegistry。

- 实现预设分析器。
  - com.dwarfeng.judge.impl.handler.analyser.groovy.GroovyAnalyserRegistry。

- 实现核心机制。
  - 主管机制。
  - 驱动机制。
  - 调度机制。
  - 接收机制。
  - 任务检查机制。
  - 作业机制。
  - 判断机制。
  - 分析机制。

- 增加操作服务。
  - com.dwarfeng.judge.stack.service.AnalysisFileFileOperateService。
  - com.dwarfeng.judge.stack.service.AnalysisFilePackItemFileOperateService。
  - com.dwarfeng.judge.stack.service.AnalysisPictureFileOperateService。
  - com.dwarfeng.judge.stack.service.AnalysisPicturePackItemFileOperateService。

- 增加依赖。
  - 增加依赖 `hessian` 以应用其新功能，版本为 `4.0.38`。
  - 增加依赖 `javax.servlet-api` 以应用其新功能，版本为 `4.0.1`。
  - 增加依赖 `jetty` 以应用其新功能，版本为 `9.4.57.v20241219`。
  - 增加依赖 `thumbnailator` 以应用其新功能，版本为 `0.4.20`。
  - 增加依赖 `dwarfeng-ftp` 以应用其新功能，版本为 `1.3.4.a`。

- 重构核心机制。
  - 支持机制。

- 重构实体以及维护服务，并通过单元测试。
  - com.dwarfeng.judge.stack.bean.entity.AlarmLevelIndicator。
  - com.dwarfeng.judge.stack.service.AlarmHistoryMaintainService。
  - com.dwarfeng.judge.stack.service.AlarmModalMaintainService。
  - com.dwarfeng.judge.stack.service.AlarmSettingMaintainService。
  - com.dwarfeng.judge.stack.service.AnalyserInfoMaintainService。
  - com.dwarfeng.judge.stack.service.AnalyserSupportMaintainService。
  - com.dwarfeng.judge.stack.service.AnalyserVariableMaintainService。
  - com.dwarfeng.judge.stack.service.AnalysisMaintainService。
  - com.dwarfeng.judge.stack.service.DriverInfoMaintainService。
  - com.dwarfeng.judge.stack.service.DriverSupportMaintainService。
  - com.dwarfeng.judge.stack.service.JudgementHistoryMaintainService。
  - com.dwarfeng.judge.stack.service.JudgementModalMaintainService。
  - com.dwarfeng.judge.stack.service.JudgerInfoMaintainService。
  - com.dwarfeng.judge.stack.service.JudgerSupportMaintainService。
  - com.dwarfeng.judge.stack.service.JudgerVariableMaintainService。
  - com.dwarfeng.judge.stack.service.SectionMaintainService。
  - com.dwarfeng.judge.stack.service.TaskEventMaintainService。
  - com.dwarfeng.judge.stack.service.TaskMaintainService。
  - com.dwarfeng.judge.stack.service.AnalysisFileInfoMaintainService。
  - com.dwarfeng.judge.stack.service.AnalysisFilePackItemInfoMaintainService。
  - com.dwarfeng.judge.stack.service.AnalysisFilePackMaintainService。
  - com.dwarfeng.judge.stack.service.AnalysisPictureInfoMaintainService。
  - com.dwarfeng.judge.stack.service.AnalysisPicturePackItemInfoMaintainService。
  - com.dwarfeng.judge.stack.service.AnalysisPicturePackMaintainService。

### Bug 修复

- (无)

### 功能移除

- 删除旧的使能缓存相关服务。
  - com.dwarfeng.judge.stack.service.EnabledDriverInfoLookupService.java。
  - com.dwarfeng.judge.stack.service.EnabledJudgerInfoLookupService.java。

- 删除旧的数据评价相关机制。
  - 驱动机制。
  - 判断机制。
  - 下沉机制。

---

## 更早的版本

[View all changelogs](./changelogs)

# ChangeLog

## Beta_2.1.0_20250915_build_A

### 功能构建

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

### Bug 修复****

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

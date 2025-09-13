# ChangeLog

## Beta_2.0.0_20250828_build_A

### 功能构建

- 实现运维指令。
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

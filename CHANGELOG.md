# ChangeLog

## Beta_2.0.0_20250828_build_A

### 功能构建

- 重构核心机制。
  - 支持机制。

- 重构实体以及维护服务，并通过单元测试。
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

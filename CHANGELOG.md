# ChangeLog

## Release_2.4.2_20261229_build_A

### 功能构建

- 新增 FastJson 以及 WebInput DTO 实体。
  - com.dwarfeng.judge.sdk.bean.dto.FastJsonAnalyserVariableInspectResult。
  - com.dwarfeng.judge.sdk.bean.dto.FastJsonJudgerVariableInspectResult。
  - com.dwarfeng.judge.sdk.bean.dto.FastJsonSinkerVariableInspectResult。
  - com.dwarfeng.judge.sdk.bean.dto.WebInputAnalyserVariableInspectInfo。
  - com.dwarfeng.judge.sdk.bean.dto.WebInputAnalyserVariableRemoveInfo。
  - com.dwarfeng.judge.sdk.bean.dto.WebInputAnalyserVariableUpsertInfo。
  - com.dwarfeng.judge.sdk.bean.dto.WebInputJudgerVariableInspectInfo。
  - com.dwarfeng.judge.sdk.bean.dto.WebInputJudgerVariableRemoveInfo。
  - com.dwarfeng.judge.sdk.bean.dto.WebInputJudgerVariableUpsertInfo。
  - com.dwarfeng.judge.sdk.bean.dto.WebInputSinkerVariableInspectInfo。
  - com.dwarfeng.judge.sdk.bean.dto.WebInputSinkerVariableRemoveInfo。
  - com.dwarfeng.judge.sdk.bean.dto.WebInputSinkerVariableUpsertInfo。

- 新增实体操作服务。
  - com.dwarfeng.judge.stack.service.AnalyserVariableOperateService。
  - com.dwarfeng.judge.stack.service.JudgerVariableOperateService。
  - com.dwarfeng.judge.stack.service.SinkerVariableOperateService。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Release_2.4.1_20251226_build_A

### 功能构建

- Wiki 更新。
  - docs/wiki/zh-CN/VersionBlacklist.md。

- 优化部分类中的字段的注解。
  - com.dwarfeng.judge.impl.service.telqos.DispatchCommand。
  - com.dwarfeng.judge.impl.service.telqos.AdapterLocalCacheCommand。
  - com.dwarfeng.judge.impl.service.telqos.ConsumeCommand。
  - com.dwarfeng.judge.impl.service.telqos.DispatcherCommand。
  - com.dwarfeng.judge.impl.service.telqos.DriveCommand。
  - com.dwarfeng.judge.impl.service.telqos.DriveLocalCacheCommand。
  - com.dwarfeng.judge.impl.service.telqos.JobLocalCacheCommand。
  - com.dwarfeng.judge.impl.service.telqos.ProviderLocalCacheCommand。
  - com.dwarfeng.judge.impl.service.telqos.ProviderSessionCommand。
  - com.dwarfeng.judge.impl.service.telqos.PurgeCommand。
  - com.dwarfeng.judge.impl.service.telqos.ReceiveCommand。
  - com.dwarfeng.judge.impl.service.telqos.ReceiverCommand。
  - com.dwarfeng.judge.impl.service.telqos.ResetCommand。
  - com.dwarfeng.judge.impl.service.telqos.SinkerBindingLocalCacheCommand。
  - com.dwarfeng.judge.impl.service.telqos.SinkerLocalCacheCommand。
  - com.dwarfeng.judge.impl.service.telqos.SinkerSessionCommand。
  - com.dwarfeng.judge.impl.service.telqos.SuperviseCommand。
  - com.dwarfeng.judge.impl.service.telqos.SupportCommand。
  - com.dwarfeng.judge.impl.service.telqos.TaskCheckCommand。

### Bug 修复

- 修复 `application-context-dubbo.xml` 中缺失的服务注册。
  - com.dwarfeng.judge.stack.service.JudgementMaintainService。

### 功能移除

- (无)

---

## Release_2.4.0_20251221_build_A

### 功能构建

- Wiki 编写。
  - docs/wiki/zh-CN/ConfDirectory.md。

### Bug 修复

- 修复接口中错误的方法命名。
  - 修复 Filter 接口及相关实现类的方法名拼写错误。
  - 修复 FilterSupporter 接口中错误的方法命名。
  - 修复 AdapterSupporter 接口中错误的方法命名。

### 功能移除

- (无)

---

## Release_2.3.2_20251220_build_A

### 功能构建

- Wiki 编写。
  - docs/wiki/zh-CN/TelqosCommands.md。

- 优化文件格式。
  - 优化 `ftp/connection.properties` 文件的格式。
  - 优化 `application-context-*.xml` 文件的格式。

- 增加部分实体维护服务的预设查询。
  - com.dwarfeng.judge.stack.service.TaskMaintainService.STATUS_EQ。
  - com.dwarfeng.judge.stack.service.TaskMaintainService.STATUS_EQ_CREATE_DATE_DESC。

### Bug 修复

- 补全 `judge-impl` 模块测试目录下缺失的配置文件。
  - spring/application-context-terminator.xml。

- 修复部分 JSFixed、FastJson 映射错误。
  - com.dwarfeng.judge.sdk.bean.entity.FastJsonTask.anchorMessage。
  - com.dwarfeng.judge.sdk.bean.entity.JSFixedFastJsonTask.anchorMessage。

### 功能移除

- (无)

---

## Release_2.3.1_20251210_build_A

### 功能构建

- Wiki 更新。
  - docs/wiki/zh-CN/VersionBlacklist.md。

- 优化 `pom.xml` 文件的格式。

- 增加预设查询。
  - com.dwarfeng.judge.stack.service.SinkerSupportMaintainService.ID_LIKE。
  - com.dwarfeng.judge.stack.service.SinkerSupportMaintainService.LABEL_LIKE。
  - com.dwarfeng.judge.stack.service.SinkerInfoMaintainService.KEY_NOT_IN。

- 优化部分类的日志输出。
  - com.dwarfeng.judge.impl.handler.AnalysisOperateHandlerImpl。

- 优化部分操作处理器的方法签名。
  - com.dwarfeng.judge.stack.handler.AnalysisFileFileOperateHandler。
  - com.dwarfeng.judge.stack.handler.AnalysisFilePackItemFileOperateHandler。
  - com.dwarfeng.judge.stack.handler.AnalysisPictureFileOperateHandler。
  - com.dwarfeng.judge.stack.handler.AnalysisPicturePackItemFileOperateHandler。

### Bug 修复

- 修复部分 Hibernate 实体中的字段错误。
  - com.dwarfeng.judge.impl.bean.entity.HibernateSinkerVariable。

- 修复部分 FastJson 实体字段注解中的错误。
  - com.dwarfeng.judge.sdk.bean.entity.FastJsonSinkerMetaIndicator。

### 功能移除

- (无)

---

## Release_2.3.0_20251205_build_A

### 功能构建

- Wiki 更新。
  - docs/wiki/zh-CN/BatchScripts.md。
  - docs/wiki/zh-CN/ShellScripts.md。
  - docs/wiki/zh-CN/images/SystemArchitecture.png。
  - docs/wiki/en-US/README.md。
  - docs/wiki/zh-CN/README.md。

- 优化启停脚本注释，以规避潜在的字符集问题。
  - binres/settingrepo-start.bat。
  - binres/settingrepo-start.sh。
  - binres/settingrepo-stop.sh。

- 优化部分类中的文本内容。
  - com.dwarfeng.judge.impl.service.telqos.SinkerBindingLocalCacheCommand。

- 优化部分类中的文档注释。
  - com.dwarfeng.judge.stack.handler.JobLocalCacheHandler。

- 实现运维指令。
  - com.dwarfeng.judge.impl.service.telqos.SectionBindingLocalCacheCommand。
  - com.dwarfeng.judge.impl.service.telqos.ProviderLocalCacheCommand。
  - com.dwarfeng.judge.impl.service.telqos.ProviderSessionCommand。
  - com.dwarfeng.judge.impl.service.telqos.AdapterLocalCacheCommand。

- 建立实体以及维护服务，并通过单元测试。
  - com.dwarfeng.judge.stack.bean.entity.FilterInfo。
  - com.dwarfeng.judge.stack.bean.entity.FilterSupport。
  - com.dwarfeng.judge.stack.bean.entity.AdapterInfo。
  - com.dwarfeng.judge.stack.bean.entity.AdapterSupport。

- 实现预设过滤器。
  - com.dwarfeng.judge.impl.handler.filter.groovy.GroovyFilterRegistry。

- 实现预设适配器。
  - com.dwarfeng.judge.impl.handler.adapter.groovy.GroovyAdapterRegistry。

- 实现核心机制。
  - 适配机制。
  - 过滤机制。

- 重构核心机制。
  - 提供机制。

- 增加预设的运维指令。
  - com.dwarfeng.springtelqos.api.integration.system.UptimeCommand。
  - com.dwarfeng.springtelqos.api.integration.system.JmxRemoteCommand。

- 优化开发环境支持。
  - 在 .gitignore 中添加 VSCode 相关文件的忽略规则。
  - 在 .gitignore 中添加 Cursor IDE 相关文件的忽略规则。

- 依赖升级。
  - 升级 `zookeeper` 依赖版本为 `3.9.4` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.7.2.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.6.0.a` 以规避漏洞。
  - 升级 `telqos` 依赖版本为 `1.1.15.a` 以规避漏洞。
  - 升级 `dcti` 依赖版本为 `1.1.13.a` 以规避漏洞。
  - 升级 `dwarfeng-datamark` 依赖版本为 `1.0.4.a` 以规避漏洞。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.3.5.a` 以规避漏洞。

### Bug 修复

- 修复项目打包后输出的可执行制品中 `docs` 目录下图片损坏的问题。

- 补全 judge-sdk 模块 BeanMapper 中缺失的接口方法。
  - com.dwarfeng.judge.sdk.bean.BeanMapper.purgeFinishedResultToFastJson。
  - com.dwarfeng.judge.sdk.bean.BeanMapper.purgeFinishedResultFromFastJson。

- 修正部分运维指令中的代码错误。
  - com.dwarfeng.judge.impl.service.telqos.SinkerSessionCommand。

### 功能移除

- 移除 judge-sdk 模块 BeanMapper 中多余的接口方法。
  - BeanMapper.sinkerMetaCompleteInfoToWebInput(WebInputSinkerMetaCompleteInfo)。

---

## Release_2.2.1_20250922_build_A

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

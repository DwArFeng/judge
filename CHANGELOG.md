# ChangeLog

### Release_1.5.1_20220416_build_A

#### 功能构建

- (无)

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.5.0_20210622_build_A

#### 功能构建

- 使用轻量级的 VariableRepository 代替之前的重量级的 Repository。
- 增加部分字段 final 修饰符。
- 更正部分测试文件中不合理的文本值。
- 修正 AssignQosServiceImpl 中错误的日志文本。
- 将 application-context-task.xml 中的全部参数设置为可配置参数。
- 将工具类 CommandUtils 的修饰符设置为。
- 改进 judge-impl 模块单元测试中的 log4j2.xml 配置文件。

#### Bug修复

- (无)

#### 功能移除

- ~~移除 Repository 及其处理器、配置。~~
- ~~关闭 dubbo 的 qos 功能~~（相关功能使用统一的 QOS 框架）。

---

### Release_1.4.2_20201023_build_A

#### 功能构建

- 优化不合理的 pom.xml 配置。
- 补充 judge-sdk 模块下部分实体的注释。

#### Bug修复

- 修复 Section 删除时使能缓存数据不能及时清除的 bug。
- 修正 HibernateSection 可能引起 StackOverflow 的 toString 方法。

#### 功能移除

- (无)

---

### Release_1.4.1_20201016_build_A

#### 功能构建

- 优化消费者日志输出语句。
- 增加消费者缓存容量监视。
- 升级 spring-terminator 并优化启动器代码。
- 引入 spring-telqos 框架，增加 QOS 功能。
- 扩展与补充 QOS 服务接口。
  - com.dwarfeng.judge.stack.service.AssignQosService
  - com.dwarfeng.judge.stack.service.EvaluateQosService
  - com.dwarfeng.judge.stack.service.JudgeQosService
- 添加自定义 QOS 指令。
  - com.dwarfeng.judge.impl.service.telqos.AssignCommand
  - com.dwarfeng.judge.impl.service.telqos.AssignLocalCacheCommand
  - com.dwarfeng.judge.impl.service.telqos.EvaluateCommand
  - com.dwarfeng.judge.impl.service.telqos.EvaluateLocalCacheCommand
  - com.dwarfeng.judge.impl.service.telqos.JudgeCommand
  - com.dwarfeng.judge.impl.service.telqos.JudgeLocalCacheCommand
  - com.dwarfeng.judge.impl.service.telqos.EvaluateConsumerCommand
  - com.dwarfeng.judge.impl.service.telqos.JudgeConsumerCommand
- 优化 task 配置文件，关键属性可配置化。

#### Bug修复

- 修复 com.dwarfeng.judge.stack.handler.AssignHandler 没有继承 Handler 接口的 bug。

#### 功能移除

- (无)

---

### Release_1.3.1_20200828_build_A

#### 功能构建

- 修正sink.properties中sink.native.kafka.topic的默认值。
- 修正程序在dubbo中注册的应用名称。

#### Bug修复

- (无)

#### 功能移除

- ~~取消 Driver 的代码结构优化。~~
  - ~~com.dwarfeng.judge.impl.handler.driver.AbstractDriverRegistry~~
  - 该结构优化会导致 judge-node-maintain 模块无法正常启动。

---

### Release_1.3.0_20200720_build_A

#### 功能构建

- RepositoryHandler新增方法。
  - com.dwarfeng.judge.stack.handler.RepositoryHandler.putData
- 优化 Driver, Judger, Repository, Sink 的代码结构。
  - com.dwarfeng.judge.impl.handler.driver.AbstractDriverRegistry
  - com.dwarfeng.judge.impl.handler.judger.AbstractJudgerRegistry
  - com.dwarfeng.judge.impl.handler.repository.AbstractRepository
  - com.dwarfeng.judge.impl.handler.repository.ReadOnlyRepository
  - com.dwarfeng.judge.impl.handler.sink.AbstractSink
- (不兼容) 更改 com.dwarfeng.judge.stack.bean.dto.JudgedValue 实体结构。
- (不兼容) 更改实体对象的结构。
  - 该改动包含对框架分配任务、评估任务的更改。
  - com.dwarfeng.judge.stack.bean.entity.Section
- 为实体对象新增属性。
  - com.dwarfeng.judge.stack.bean.entity.Section
- 新增 DTO。
  - com.dwarfeng.judge.stack.bean.dto.JudgerReport
  - com.dwarfeng.judge.stack.bean.dto.JudgerResult
  - com.dwarfeng.judge.stack.bean.dto.SectionReport
  - com.dwarfeng.judge.sdk.bean.dto.FastJsonJudgerReport
  - com.dwarfeng.judge.sdk.bean.dto.FastJsonSectionReport
  - com.dwarfeng.judge.sdk.bean.dto.JSFixedFastJsonJudgerReport
  - com.dwarfeng.judge.sdk.bean.dto.JSFixedFastJsonSectionReport
- (不兼容) 重写数据判断逻辑。
- 修改节中点启动器的日志文本的错误。
- 补充 README.md
- 细化 com.dwarfeng.judge.stack.exception.RepositoryException 报警分类。
  - com.dwarfeng.judge.stack.exception.UnsupportedRepositoryCategoryException: 当Category不支持时抛出的异常
- 优化部分异常的构造器方法。
  - com.dwarfeng.judge.stack.exception.UnsupportedDriverTypeException
  - com.dwarfeng.judge.stack.exception.UnsupportedJudgerTypeException
  - com.dwarfeng.judge.stack.exception.UnsupportedRepositoryCategoryException

**注意：此版本还有大量的改动会导致该版本与低版本不兼容，所有不兼容的改动使用 (不兼容) 单独标注。**

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.2.1_20200714_build_A

#### 功能构建

- 升级subgrade依赖至1.1.2.a。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.2.0_20200708_build_A

#### 功能构建

- 升级subgrade依赖版本至1.1.1.b。
- 更改 RepositoryHandler 的接口结构。
  - 这将导致该版本与上一个版本不兼容。
  - 因此改动，程序的部分功能被去除，详见功能移除列表。
- 规范停止脚本 judge-stop.sh。
- 增强 com.dwarfeng.judge.impl.handler.repository.RandomRepository。
- 新建 com.dwarfeng.judge.impl.handler.repository.RoutedRepository。

#### Bug修复

- (无)

#### 功能移除

- ~~com.dwarfeng.judge.impl.mock.MockEvaluateService~~
- ~~com.dwarfeng.judge.impl.mock.MockRepository~~
- ~~com.dwarfeng.judge.impl.mock.ThresholdJudgerMaker~~
- ~~com.dwarfeng.judge.impl.mock.ThresholdJudgerSupporter~~
- ~~com.dwarfeng.judge.impl.mock.TruncationJudgerMaker~~
- ~~com.dwarfeng.judge.impl.mock.TruncationJudgerSupporter~~

---

### Release_1.1.5_20200512_build_A

#### 功能构建

- 完善@Transactional注解的回滚机制。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.4_20200505_build_A

#### 功能构建

- 升级subgrade依赖至1.0.1.a，以避免潜在的RedisDao的分页bug。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.3_20200426_build_A

#### 功能构建

- 升级subgrade依赖至1.0.0.a，修复轻微不兼容的错误。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.2_20200425_build_A

#### 功能构建

- 将部分实体的Crud服务升级为BatchCrud服务。
  - com.dwarfeng.judge.stack.service.SectionMaintainService
  - com.dwarfeng.judge.stack.service.DriverInfoMaintainService
  - com.dwarfeng.judge.stack.service.JudgerInfoMaintainService

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.1_20200419_build_A

#### 功能构建

- 添加实体 com.dwarfeng.judge.sdk.bean.entity.WebInputDriverSupport。
- 添加实体 com.dwarfeng.judge.sdk.bean.entity.WebInputJudgerSupport。

#### Bug修复

- 修复 WebInputJudgerInfo.toStackBean 的参数错误。
- 修复项目中的警告。
  - com.dwarfeng.judge.impl.handler.AssignLocalCacheHandlerImpl
  - com.dwarfeng.judge.impl.handler.EvaluateLocalCacheHandlerImpl

#### 功能移除

- (无)

---

### Release_1.1.0_20200407_build_A

#### 功能构建

- 将可放宽装配的组件列表设置为 @Autowired(required = false)
  - com.dwarfeng.judge.impl.handler.DriverHandlerImpl
  - com.dwarfeng.judge.impl.handler.JudgerHandlerImpl
  - com.dwarfeng.judge.impl.handler.RepositoryHandlerImpl
  - com.dwarfeng.judge.impl.handler.SinkHandlerImpl
  - com.dwarfeng.judge.impl.service.DriverSupportMaintainServiceImpl
  - com.dwarfeng.judge.impl.service.JudgerSupportMaintainServiceImpl
- 更改优化node模块的程序结构。
  - node-all
  - node-assign
  - node-evaluate
  - node-maintain
- Driver,Judger相关结构实现功能分离。
  - com.dwarfeng.judge.impl.handler.DriverSupporter
  - com.dwarfeng.judge.impl.handler.JudgerSupporter

#### Bug修复

- (无)

#### 功能移除

- ~~删除api模块以及解除fdr依赖~~

---

### Release_1.0.1_20200404_build_A

#### 功能构建

- 实现com.dwarfeng.judge.impl.handler.judger.GroovyJudgerMaker。
- 实现com.dwarfeng.judge.impl.handler.judger.ThresholdJudgerMaker。
- 优化com.dwarfeng.judge.impl.handler.judger.TruncationJudgerMaker。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.0.0_20200403_build_B

#### 功能构建

- 调整com.dwarfeng.judge.impl.handler.repository.RandomNumberRepository的Autowired方式。
- 调整com.dwarfeng.judge.impl.handler.Repository接口方法异常的类型。
- 调整com.dwarfeng.judge.impl.handler.Repository接口中部分方法的返回值。
- 调整com.dwarfeng.judge.stack.handler.RepositoryHandler接口中部分方法的返回值。
- 删除DaoConfiguration中不使用的字段。
- 升级dcti依赖至1.1.0.a。
- 删除项目自身TimedValue，使用dcti的TimedValue对象。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.0.0_20200402_build_A

#### 功能构建

- dcti.kafka水槽开发完成并通过测试。
- 开发 judge-api 模块。
  - 编写 JudgedValueFilterMaker。
  - 编写 JudgedValueTriggerMaker。
- 完善多种Driver。
  - com.dwarfeng.judge.impl.handler.driver.CronDriverProvider
  - com.dwarfeng.judge.impl.handler.driver.DctiKafkaDriverProvider
  - com.dwarfeng.judge.impl.handler.driver.FixedDelayDriverProvider
  - com.dwarfeng.judge.impl.handler.driver.FixedRateDriverProvider
- 完善RandomNumberRepository。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Beta_1.1.0_20200401_build_A

#### 功能构建

- 优化项目结构。
- 添加DriverSupport以及其服务。
- 添加JudgerSupport以及其服务。
- 完成node-maintain模块以及测试通过。
- 完成node-assign模块以及测试通过。
- 完成node-evaluate模块以及测试通过。
- 编写完成项目的assembly文件。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Beta_1.0.0_20200331_build_A

#### 功能构建

- 基础实体维护服务建立，测试通过。
- Sink机制建立。
- Repository机制建立。
- Drive机制建立。
- Judge机制建立。
- node-all模块搭建完毕，并且正常运行。

#### Bug修复

- (无)

#### 功能移除

- (无)

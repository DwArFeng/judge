# ChangeLog

### Release_1.3.0_20200715_build_A

#### 功能构建

- (不兼容) 更改 com.dwarfeng.judge.stack.bean.dto.JudgedValue 实体结构。
- (不兼容) 更改实体对象的结构。
  - 该改动包含对框架分配任务、评估任务的更改。
  - com.dwarfeng.judge.stack.bean.entity.DriverInfo
  - com.dwarfeng.judge.stack.bean.entity.JudgerInfo
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
  - 这将导致该版本与上一个版本直接的不兼容。
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

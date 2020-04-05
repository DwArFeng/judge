# ChangeLog

### Release_1.1.0_20200405_build_A

#### 功能构建

- 细分judge-api模块。
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

#### Bug修复

- (无)

#### 功能移除

- (无)

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

# ChangeLog

### Release_1.0.0_20200401_build_A

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

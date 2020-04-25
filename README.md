# judge

一款开箱即用的用于评判数据的软件架构。

## 安装说明

1. 下载源码

   使用git进行源码下载。
   ```
   git clone git@github.com:DwArFeng/judge.git
   ```
   对于中国用户，可以使用gitee进行高速下载。
   ```
   git clone git@gitee.com:dwarfeng/judge.git
   ```
   
2. 项目打包

   进入项目根目录，执行maven命令
   ```
   mvn clean package
   ```
   
3. 解压

   找到打包后的目标文件 
   ```
   judge-node/judge-node-all/target/judge-node-all-[version]-release.tar.gz
   judge-node/judge-node-assign/target/judge-node-assign-[version]-release.tar.gz
   judge-node/judge-node-evaluate/target/judge-node-evaluate-[version]-release.tar.gz
   judge-node/judge-node-maintain/target/judge-node-maintain-[version]-release.tar.gz
   ```
   将其解压至windows系统或者linux系统
   
4. 配置

   1. 进入工程下的`bin`目录，修改所有执行脚本的`basedir`和`logdir`
      
   2. 修改conf文件夹下的配置文件，着重修改各连接的url与密码。
   
5. enjoy it

---

## 分布式说明

该项目使用`dubbo`作为RPC框架，本身支持分布式，但是在实际使用过程中，由于项目本身的逻辑所致，该项目分布式部署应该遵守如下规则，
否则会导致报警处理流程的行为不正常：

1. 在一个独立的项目中，`node-all`与`node-assign`的数量加起来不大于1个，即一个项目中，只保留一个数据分析任务下达模块。
2. 在任何情况下，`node-maintain`模块的数量允许部署任意多个。
2. 在任何情况下，`node-evaluate`模块的数量允许部署任意多个。

---

## 特性

- 部件实体关联多个驱动信息与判断信息，整个数据评估步骤如下进行。
  1. 任务分配模块在启动后注册所有驱动。
  2. 驱动按照一定的规则定期给评估模块下发评估任务。
  3. 评估模块接收到评估任务后，调取部件的所有判断器，放进评估消费者中。
  4. 消费者调用判断器中的判断方法，该方法会拉取数据仓库中的数据，根据数据评估出一个介于0-1之间的数。
  5. 被评估后生成的评估信息通过水槽进行下沉，最终逃出该架构，进入消息队列或其它组件。
- 可配置的部件、驱动信息、判断信息。
- 一个系统中只能有一个任务分配模块，但评估模块是分布式的。
- 相关的驱动器、判断器、数据仓库、水槽均可以自己定义并扩展。

---

## 项目的工作流程

1. 项目运行后，数据指派框架会读取框架内的所有部件，并且取出这些部件的驱动器，进行注册。

2. 驱动器判断是否满足数据判断条件，一旦有个驱动器满足数据判断条件后，就会向数据评估模块下发数据评估任务。

3. 数据评估模块接收到评估任务后，从数据仓库中读取响应的数据，进行评估，并返回一个评估信息。

4. 下沉模块将获得的数据评估信息下沉，使得数据离开该框架，进入下一个环节。

---

## 项目的使用

### 部件的定义

部件是judge框架中的根实体，judge中，所有的数据评价功能都是以部件为单位的。

部件具有 主键，名称，备注 三个字段。一个部件拥有多个驱动器和多个判断器。

### 驱动器的定义

驱动器负责向数据评价模块下达评价任务。当一个部件有多个驱动器时，每个驱动器会独立工作，例如如果一个部件同时具有数据变更驱动器和
定时驱动器的话，则这个部件会在间隔一段时间就下达评价任务的同时，每次数据改变也会下达数据评价任务。

驱动器的字段如下：

|字段名称|备注|
|---|---|
|key|主键|
|sectionKey|所属的部件的主键|
|enabled|是否启用|
|type|驱动器类型|
|content|驱动器内容|
|remark|备注|

其中，驱动器的类型和内容根据一定的规则开发，可以扩展。

*详细的用法可以查阅驱动器支持。*

### 判断器的定义

判断器负责运行数据判断任务，当数据判断任务运行时，判断器会从程序中的数据仓库中取出特定的数据，进行判断，并返回JudgementInfo
对象，其中最重要的属性是value，一个介于0.0-1.0之间的双精度浮点数，用于评价数据的好坏。一般而言，定义0.0为数据最好，1.0为数据最坏。

判断器的字段如下：

|字段名称|备注|
|---|---|
|key|主键|
|sectionKey|所属的部件的主键|
|enabled|是否启用|
|type|判断器类型|
|content|判断器内容|
|remark|备注|

其中，判断器的类型和内容根据一定的规则开发，可以扩展。

*详细的用法可以查阅判断器支持。*

---

## 插件

该项目可以进行插件扩展，将自己编写的插件放在项目的 `libext` 文件夹中，并且在 `optext` 中编写spring加载文件，以实现插件的加载。

---

## 项目的扩展

1. 驱动器的扩展

   实现接口 `com.dwarfeng.judge.impl.handler.DriverProvider` 并将实现类注入到spring的IoC容器中。
   
   实现接口 `com.dwarfeng.judge.impl.handler.DriverSupporter` 并将实现类注入到spring的IoC容器中。

2. 判断器的扩展

   实现接口 `com.dwarfeng.judge.impl.handler.JudgerMaker` 并将实现类注入到spring的IoC容器中。
   
   实现接口 `com.dwarfeng.judge.impl.handler.JudgerSupporter` 并将实现类注入到spring的IoC容器中。

3. 数据仓库的扩展

   实现接口 `com.dwarfeng.judge.impl.handler.Repository` 并将实现类注入到spring的IoC容器中。
   
   设定配置文件 `conf/judge/repository.properties`
   ```properties
   ###################################################
   #                     global                      #
   ###################################################
   # 当前的仓库类型。
   # 目前该项目支持的仓库类型有:
   #   random_number: 实时数据返回一个介于给定区间的随机数，持久数据返回空值的仓库。
   #
   # 对于一个具体的项目，很可能只用一个仓库。此时希望加载
   # 仓库时只加载需要的那个，其余的仓库不加载。这个需求
   # 可以通过编辑 application-context-scan.xml 实现。
   repository.type=random_number
   ```
   将 repository.type 改为扩展的 repository。

5. 水槽的扩展

   实现接口 `com.dwarfeng.judge.impl.handler.Sink` 并将实现类注入到spring的IoC容器中。
   
   设定配置文件 `conf/judge/sink.properties`
   ```properties
   ###################################################
   #                     global                      #
   ###################################################
   # 当前的水槽类型。
   # 目前该项目支持的水槽类型有:
   #   drain: 简单的丢弃掉所有消息的水槽。
   #   multi: 同时将消息推送给所有代理的多重水槽。
   #   native.kafka: 生成原生数据的水槽，使用Kafka消息队列发送数据。
   #   dcti.kafka: 生成dcti标准数据的水槽，使用Kafka消息队列发送数据。
   #
   # 对于一个具体的项目，很可能只用一个水槽。此时希望加载
   # 水槽时只加载需要的那个，其余的水槽不加载。这个需求
   # 可以通过编辑 application-context-scan.xml 实现。
   sink.type=drain
   ```
   将 sink.type 改为扩展的 sink。

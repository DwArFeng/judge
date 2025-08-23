# judge

Judge 是一款开箱即用的用于数据评价的调度框架与算法集合，能够对各种数据进行评价，并分布式调度数据评价任务。

Judge 可以用于搭建规则引擎、数据质量评价系统、数据监控系统等、预测性维护系统等。

---

## 特性

- 基于驱动器-评价器-水槽的架构设计，实现数据评价算法及任务调度能力。
- 提供 Telqos 运维平台，能够在没有 GUI 的环境下使用本服务的功能。
- 支持主流关系型数据库（基于 Hibernate）。
- 支持分布式部署。

## 文档

该项目的文档位于 [docs](../../../docs) 目录下，包括：

### wiki

wiki 为项目的开发人员为本项目编写的详细文档，包含不同语言的版本，主要入口为：

1. [简介](./Introduction.md) - 即本文件。
2. [目录](./Contents.md) - 文档目录。

## 安装说明

1. 下载源码

   使用 git 进行源码下载。

   ```shell
   git clone git@github.com:DwArFeng/judge.git
   ```

   对于中国用户，可以使用 gitee 进行高速下载。

   ```shell
   git clone git@gitee.com:dwarfeng/judge.git
   ```

2. 项目打包

   进入项目根目录，执行 maven 命令。

   ```
   mvn clean package
   ```

3. 解压

   找到打包后的目标文件。

   ```
   judge-node/target/judge-node-[version]-release.tar.gz
   ```

   将其解压至 windows 系统或者 linux 系统。

4. 配置

   1. 修改 `conf` 文件夹下的配置文件，着重修改各连接的 url 与密码。

5. enjoy it

## 分布式说明

该项目使用 `dubbo` 作为 RPC 框架，本身支持分布式，您可以在实际使用时，部署该项目任意数量，以进行分布式运算。

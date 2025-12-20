# Telqos Commands - Telqos 命令

## 命令列表

Judge 2.3.2.a 版本提供的 Telqos 命令如下所示：

| 命令                           | 说明           | 可用版本    |
|------------------------------|--------------|---------|
| [alc](#alc-命令)               | 适配器本地缓存操作    | 2.3.2.a |
| [csu](#csu-命令)               | 消费者操作        | 2.3.2.a |
| [datamark](#datamark-命令)     | 数据标记服务       | 2.3.2.a |
| [dispatch](#dispatch-命令)     | 调度处理器操作/查看   | 2.3.2.a |
| [dispatcher](#dispatcher-命令) | 调度器处理器操作/查看  | 2.3.2.a |
| [dlc](#dlc-命令)               | 驱动器本地缓存操作    | 2.3.2.a |
| [drive](#drive-命令)           | 驱动处理器操作/查看   | 2.3.2.a |
| [dubbo](#dubbo-命令)           | 分布式服务上线/下线   | 2.3.2.a |
| [jlc](#jlc-命令)               | 作业器本地缓存操作    | 2.3.2.a |
| [jmxremote](#jmxremote-命令)   | JMX 远程管理操作   | 2.3.2.a |
| [lc](#lc-命令)                 | 列出指令         | 2.3.2.a |
| [log4j2](#log4j2-命令)         | Log4j2 命令    | 2.3.2.a |
| [man](#man-命令)               | 显示指令的详细信息    | 2.3.2.a |
| [memory](#memory-命令)         | 内存监视         | 2.3.2.a |
| [plc](#plc-命令)               | 提供器本地缓存操作    | 2.3.2.a |
| [ps](#ps-命令)                 | 提供器会话操作      | 2.3.2.a |
| [purge](#purge-命令)           | 清除处理器操作/查看   | 2.3.2.a |
| [quit](#quit-命令)             | 退出           | 2.3.2.a |
| [receive](#receive-命令)       | 接收功能上线/下线    | 2.3.2.a |
| [receiver](#receiver-命令)     | 接收器处理器操作/查看  | 2.3.2.a |
| [reset](#reset-命令)           | 重置处理器操作/查看   | 2.3.2.a |
| [sblc](#sblc-命令)             | 下沉绑定本地缓存操作   | 2.3.2.a |
| [scblc](#scblc-命令)           | 部件绑定本地缓存操作   | 2.3.2.a |
| [shutdown](#shutdown-命令)     | 关闭/重启程序      | 2.3.2.a |
| [slc](#slc-命令)               | 下沉器本地缓存操作    | 2.3.2.a |
| [ss](#ss-命令)                 | 下沉会话操作       | 2.3.2.a |
| [supervise](#supervise-命令)   | 主管处理器操作/查看   | 2.3.2.a |
| [support](#support-命令)       | 支持操作         | 2.3.2.a |
| [tc](#tc-命令)                 | 任务检查处理器操作/查看 | 2.3.2.a |
| [uptime](#uptime-命令)         | 系统运行时间       | 2.3.2.a |

鉴于所有指令都可以实际操作验证，因此本文对于较长的输出将予以省略，省略的部分将会使用 `etc...` 进行标注。

## alc 命令

适配器本地缓存操作。

### 语法

```text
usage: alc -l adapter-info-id
alc -c
适配器本地缓存操作
 -c                 清除缓存
 -l <adapter-info-id>   查看指定适配器的详细信息，如果本地缓存中不存在，则尝试抓取
```

### 示例

#### 查看适配器

```text
alc -l 1
adapterInfo: AdapterInfo{key=LongIdKey{longId=1}, type='test', label='测试适配器', etc...}
adapter: com.dwarfeng.judge.impl.handler.adapter.TestAdapter@etc...
OK
```

#### 清除缓存

```text
alc -c
本地缓存已清除
OK
```

## csu 命令

消费者操作。

用于查看消费者状态和设置消费者参数。

### 语法

```text
usage: csu -l [-h]
csu -s [-b val] [-t val]
消费者操作
 -b <buffer-size>   缓冲器的大小
 -h                 持续输出
 -l                 查看消费者状态
 -s                 设置消费者参数
 -t <thread>        消费者的线程数量
```

输入 `csu -l` 指令时，会输出一次当前消费者状态。如果需要持续监视消费者状态，请输入 `csu -l -h` 指令。

`-h` 参数可以使消费者状态每秒输出一次，直到用户输入任意键退出。

### 示例

#### 监视消费者状态

```text
csu -l -h
输入任意字符停止持续输出
buffered-size:12      buffer-size:1000    thread:1   idle:false
buffered-size:46      buffer-size:1000    thread:1   idle:false
buffered-size:125     buffer-size:1000    thread:1   idle:false
buffered-size:0       buffer-size:1000    thread:1   idle:false
q
OK
```

#### 设置消费者参数

```text
csu -s -b 2000 -t 2
设置完成，消费者新的参数为:
buffered-size:0       buffer-size:2000    thread:2   idle:false
OK
```

## datamark 命令

数据标记服务。

该命令来自 `com.dwarfeng.datamark.service.telqos` 包，用于数据标记相关的操作。

### 语法

```text
usage: datamark -lh
datamark -ua [-hn handler-name]
datamark -get [-hn handler-name]
datamark -refresh [-hn handler-name]
datamark -update [-hn handler-name] [-dv datamark-value]
数据标记服务
 -dv <arg>              数据标记值
 -get                   获取数据标记值
 -hn <arg>              数据服务 ID
 -lh,--list-handlers    列出所有可用的数据标记处理器
 -refresh               刷新数据标记值
 -ua,--update-allowed   返回处理器是否允许更新
 -update                更新数据标记值
```

### 示例

#### 列出所有可用的数据标记处理器

```text
datamark -lh
可用的处理器名称:
    1: adapterDatamarkHandler
    2: analyserDatamarkHandler
    3: driverDatamarkHandler
    4: filterDatamarkHandler
    5: judgerDatamarkHandler
    6: providerDatamarkHandler
    7: sectionDatamarkHandler
    8: sinkerDatamarkHandler
    9: visualizerDatamarkHandler
OK
```

#### 获取数据标记值

```text
datamark -get -hn adapterDatamarkHandler
处理器名称: adapterDatamarkHandler, 数据标记值: judge-node
OK
```

## dispatch 命令

调度处理器操作/查看。

### 语法

```text
usage: dispatch -status
调度处理器操作/查看
 -status   查看调度处理器状态
```

### 示例

#### 查看调度处理器状态

```text
dispatch -status
started: true.
OK
```

## dispatcher 命令

调度器处理器操作/查看。

用于查看当前生效的调度器，以及查看所有可用的调度器。

### 语法

```text
usage: dispatcher -current
dispatcher -all
调度器处理器操作/查看
 -all      查看全部调度器
 -current  查看当前调度器
```

### 示例

#### 查看当前调度器

```text
dispatcher -current
current dispatcher:
  com.dwarfeng.judge.impl.handler.dispatcher.TestDispatcher@etc...
OK
```

#### 查看全部调度器

```text
dispatcher -all
all dispatchers:
  0/3: com.dwarfeng.judge.impl.handler.dispatcher.TestDispatcher@etc...
  1/3: com.dwarfeng.judge.impl.handler.dispatcher.AnotherDispatcher@etc...
  2/3: com.dwarfeng.judge.impl.handler.dispatcher.ThirdDispatcher@etc...
OK
```

## dlc 命令

驱动器本地缓存操作。

### 语法

```text
usage: dlc -l id
dlc -c
驱动器本地缓存操作
 -c              清除缓存
 -l <id>   查看指定部件的驱动器本地缓存，如果本地缓存中不存在，则尝试抓取
```

### 示例

#### 查看驱动器本地缓存

```text
dlc -l 1
section: Section{key=LongIdKey{longId=1}, name='测试部件', etc...}

drivers:
  1 DriverInfo{key=LongIdKey{longId=1}, type='test', label='测试驱动器', etc...}
  1 com.dwarfeng.judge.impl.handler.driver.TestDriver@etc...
OK
```

#### 清除缓存

```text
dlc -c
本地缓存已清除
OK
```

## drive 命令

驱动处理器操作/查看。

### 语法

```text
usage: drive -status
驱动处理器操作/查看
 -status   查看驱动处理器状态
```

### 示例

#### 查看驱动处理器状态

```text
drive -status
started: true.
OK
```

## dubbo 命令

分布式服务查询/上线/下线。

### 语法

```text
usage: dubbo -online [service-name]
dubbo -offline [service-name]
dubbo -ls
分布式服务上线/下线
 -ls              列出服务
 -offline <arg>   下线服务
 -online <arg>    上线服务
```

`[service-name]` 参数为正则表达式，只有服务的全名称匹配正则表达式时，才会被上线/下线。

如果想要上线/下线名称中包含 `FooBar` 的服务，则可以使用 `.*FooBar.*` 作为正则表达式。

如果不指定 `[service-name]` 参数，则使所有服务上线/下线。

### 示例

#### 列出服务

```text
dubbo -ls
As Provider side:
+---------------------------------------------------------------------------+---+
|                           Provider Service Name                           |PUB|
+---------------------------------------------------------------------------+---+
|     com.dwarfeng.judge.stack.service.VisualizerSupportMaintainService     | Y |
+---------------------------------------------------------------------------+---+
etc...
As Consumer side:
+-----------------------------------------------+---+
|             Consumer Service Name             |NUM|
+-----------------------------------------------+---+
|com.dwarfeng.sfds.stack.service.GenerateService| 3 |
+-----------------------------------------------+---+
OK
```

#### 上线服务

```text
dubbo -online .*MaintainService.*
OK
```

随后可以列出服务观察上线效果。

#### 下线服务

```text
dubbo -offline .*MaintainService.*
OK
```

随后可以列出服务观察下线效果。

## jlc 命令

作业器本地缓存操作。

### 语法

```text
usage: jlc -l id
jlc -c
作业器本地缓存操作
 -c              清除缓存
 -l <id>   查看指定部件的作业器本地缓存，如果本地缓存中不存在，则尝试抓取
```

### 示例

#### 查看作业器本地缓存

```text
jlc -l 1
section: Section{key=LongIdKey{longId=1}, name='测试部件', etc...}

analysers:
  1 AnalyserInfo{key=LongIdKey{longId=1}, type='test', label='测试分析器', etc...}
  1 com.dwarfeng.judge.impl.handler.analyser.TestAnalyser@etc...

judgers:
  1 JudgerInfo{key=LongIdKey{longId=1}, type='test', label='测试判断器', etc...}
  1 com.dwarfeng.judge.impl.handler.judger.TestJudger@etc...

visualizers:
  1 VisualizerInfo{key=LongIdKey{longId=1}, type='test', label='测试可视化器', etc...}
  1 com.dwarfeng.judge.impl.handler.visualizer.TestVisualizer@etc...
OK
```

#### 清除缓存

```text
jlc -c
本地缓存已清除
OK
```

## jmxremote 命令

JMX 远程管理操作。

### 语法

```text
usage: jmxremote -start [-p port]
jmxremote -stop
jmxremote -status
JMX 远程管理操作
 -p <arg>   JMX 远程管理端口号
 -start     启动 JMX 远程管理
 -status    查看 JMX 远程管理状态
 -stop      停止 JMX 远程管理

OK
```

### 示例

#### 查看 JMX 远程管理状态

```text
jmxremote -status
JMX 远程管理未启动
OK
```

#### 启动 JMX 远程管理

```text
jmxremote -start -p 24000
JMX 远程管理启动成功
  端口: 24000
  服务地址: service:jmx:rmi:///jndi/rmi://:24000/jmxrmi
OK
```

#### 停止 JMX 远程管理

```text
jmxremote -stop
JMX 远程管理已停止
OK
```

## lc 命令

列出 Telqos 支持的所有命令。

### 语法

```text
usage: lc [-p prefix|--prefix prefix]
列出指令
 -p,--prefix <prefix>   列出包含指定前缀的命令
```

### 示例

```text
lc -p r
1   receive    接收功能上线/下线
2   receiver   接收器处理器操作/查看
3   reset      重置处理器操作/查看
--------------------------
共 3 条
OK
```

## log4j2 命令

Log4j2 命令。

用于管理 Log4j2 日志配置。

### 语法

```text
usage: log4j2 -reconfigure
Log4j2 命令
 -reconfigure   重新配置 Log4j2
```

## man 命令

显示指定命令的详细信息。

### 语法

```text
usage: man [command]
显示指令的详细信息
```

### 示例

```text
man man
usage: man [command]
显示指令的详细信息
```

## memory 命令

内存监视

### 语法

```text
usage: memory [-u unit]
内存监视
 -u <arg>   显示单位
```

### 示例

```text
memory -u mib
JVM 最大内存: 7212.50MiB
JVM 分配内存: 1223.00MiB
JVM 可用内存: 1120.00MiB
OK
```

## plc 命令

提供器本地缓存操作。

### 语法

```text
usage: plc -l provider-info-id
plc -c
提供器本地缓存操作
 -c                 清除缓存
 -l <provider-info-id>   查看指定提供器的详细信息，如果本地缓存中不存在，则尝试抓取
```

### 示例

#### 查看提供器

```text
plc -l 1
providerInfo: ProviderInfo{key=LongIdKey{longId=1}, type='test', label='测试提供器', etc...}
provider: com.dwarfeng.judge.impl.handler.provider.TestProvider@etc...
OK
```

#### 清除缓存

```text
plc -c
本地缓存已清除
OK
```

## ps 命令

提供器会话操作。

### 语法

```text
usage: ps -l provider-info-id
ps -cnc
提供器会话操作
 -cnc       关闭并清除提供器会话
 -l <arg>   查询提供器会话
```

### 示例

#### 查看提供器会话

```text
ps -l 1
providerInfo: ProviderInfo{key=LongIdKey{longId=1}, type='test', label='测试提供器', etc...}
provider: com.dwarfeng.judge.impl.handler.provider.TestProvider@etc...
providerSession: com.dwarfeng.judge.impl.handler.provider.TestProviderSession@etc...
OK
```

#### 关闭并清除提供器会话

```text
ps -cnc
本地缓存已清除
OK
```

## purge 命令

清除处理器操作/查看。

### 语法

```text
usage: purge -online
purge -offline
purge -start
purge -stop
purge -status
清除处理器操作/查看
 -offline   下线清除处理器
 -online    上线清除处理器
 -start     启动清除处理器
 -status    查看清除处理器状态
 -stop      停止清除处理器
```

### 示例

#### 查看清除处理器状态

```text
purge -status
online: true, latch holding: true, started: true, working: true.
OK
```

#### 上线清除处理器

```text
purge -online
清除处理器已上线!
OK
```

#### 下线清除处理器

```text
purge -offline
清除处理器已下线!
OK
```

#### 启动清除处理器

```text
purge -start
清除处理器已启动!
OK
```

#### 停止清除处理器

```text
purge -stop
清除处理器已停止!
OK
```

## quit 命令

退出 Telqos 运维平台。

### 语法

```text
usage: quit
退出
```

### 示例

```text
quit
Bye
服务端主动与您中断连接
再见!


遗失对主机的连接。
```

## receive 命令

接收功能上线/下线。

### 语法

```text
usage: receive -status
receive -online
receive -offline
接收功能上线/下线
 -offline   下线服务
 -online    上线服务
 -status    查看接收功能当前状态
```

### 示例

#### 查看接收功能状态

```text
receive -status
接收功能当前状态: 上线
OK
```

#### 接收功能上线

```text
receive -online
接收功能已上线!
OK
```

#### 接收功能下线

```text
receive -offline
接收功能已下线!
OK
```

## receiver 命令

接收器处理器操作/查看。

用于查看当前生效的接收器，以及查看所有可用的接收器。

### 语法

```text
usage: receiver -current
receiver -all
接收器处理器操作/查看
 -all      查看全部接收器
 -current  查看当前接收器
```

### 示例

#### 查看当前接收器

```text
receiver -current
current receiver:
  com.dwarfeng.judge.impl.handler.receiver.TestReceiver@etc...
OK
```

#### 查看全部接收器

```text
receiver -all
all receivers:
  0/3: com.dwarfeng.judge.impl.handler.receiver.TestReceiver@etc...
  1/3: com.dwarfeng.judge.impl.handler.receiver.AnotherReceiver@etc...
  2/3: com.dwarfeng.judge.impl.handler.receiver.ThirdReceiver@etc...
OK
```

## reset 命令

重置处理器查看与操作。

用于查看当前生效的重置器，以及基于指令手动重置服务功能。

### 语法

```text
usage: reset -l
reset -start
reset -stop
reset -status
reset --reset-job
reset --reset-supervise
reset --reset-sink
reset --reset-provide
重置处理器操作/查看
    --l              查看重置处理器
    --reset-job      作业重置作业功能操作
    --reset-provide  执行重置提供功能操作
    --reset-sink     执行重置下沉功能操作
    --reset-supervise 执行重置主管功能操作
    --start          启动重置处理器
    --status         查看重置处理器状态
    --stop           停止重置处理器
```

`reset --reset-job` 指令重置作业功能，会执行以下操作：

1. 停止作业处理器。
2. 清空作业本地缓存处理器。
3. 启动作业处理器。

`reset --reset-supervise` 指令重置主管功能，会执行以下操作：

1. 停止主管处理器。
2. 清空主管本地缓存处理器。
3. 启动主管处理器。

`reset --reset-sink` 指令重置下沉功能，会执行以下操作：

1. 停止下沉处理器。
2. 清空下沉本地缓存处理器。
3. 启动下沉处理器。

`reset --reset-provide` 指令重置提供功能，会执行以下操作：

1. 停止提供处理器。
2. 清空提供本地缓存处理器。
3. 启动提供处理器。

这些方法会消耗一定的时间，尤其是在消费者积压数据较多的情况下。

### 示例

#### 查看当前生效的重置器

```text
reset -l
01. FixedDelayResetter{delay=43200000}
OK
```

#### 查看重置处理器的启停状态

```text
reset -status
started: true
OK
```

#### 启动重置处理器

```text
reset -start
重置处理器已启动!
OK
```

#### 停止重置处理器

```text
reset -stop
重置处理器已停止!
OK
```

#### 重置作业功能

```text
reset --reset-job
重置成功!
OK
```

#### 重置主管功能

```text
reset --reset-supervise
重置成功!
OK
```

#### 重置下沉功能

```text
reset --reset-sink
重置成功!
OK
```

#### 重置提供功能

```text
reset --reset-provide
重置成功!
OK
```

## sblc 命令

下沉绑定本地缓存操作。

### 语法

```text
usage: sblc -l sinker-info-id
sblc -c
下沉绑定本地缓存操作
 -c                 清除缓存
 -l <sinker-info-id>   查看指定下沉器的绑定信息，如果本地缓存中不存在，则尝试抓取
```

### 示例

#### 查看下沉绑定

```text
sblc -l 1
数据总数: 5
输入 all 查看所有数据
输入 begin-end 查看指定范围的数据(开始于 0)
输入 q 退出

all
索引: 0/5
sectionKey: LongIdKey{longId=1}
  metaId: meta1
    meta: value1
    default: default1
    equivalent: equivalent1
  metaId: meta2
    meta: value2
    default: default2
    equivalent: equivalent2
etc...
OK
```

#### 清除缓存

```text
sblc -c
缓存已清空
OK
```

## scblc 命令

部件绑定本地缓存操作。

### 语法

```text
usage: scblc -l section-info-id
scblc -c
部件绑定本地缓存操作
 -c                 清除缓存
 -l <section-info-id>   查看指定部件的绑定信息，如果本地缓存中不存在，则尝试抓取
```

### 示例

#### 查看部件绑定

```text
scblc -l 1
数据总数: 5
输入 all 查看所有数据
输入 begin-end 查看指定范围的数据(开始于 0)
输入 q 退出

all
索引: 0/5
sinkerKey: LongIdKey{longId=1}
  metaId: meta1
    meta: value1
    default: default1
    equivalent: equivalent1
  metaId: meta2
    meta: value2
    default: default2
    equivalent: equivalent2
etc...
OK
```

#### 清除缓存

```text
scblc -c
缓存已清空
OK
```

## shutdown 命令

关闭/重启程序。

在本项目中，重启程序功能不可用，只能使用关闭程序功能。

### 语法

```text
usage: shutdown [-s/-r] [-e exit-code] [-c comment]
关闭/重启程序
 -c <comment>     备注
 -e <exit-code>   退出代码
 -r               重启程序
 -s               退出程序
```

增加 `-c` 参数，可以在关闭程序时添加备注信息，备注信息将会被记录到日志中。

`-r` 参数不可用。

### 示例

```text
shutdown
服务将会关闭，您可能需要登录远程主机才能重新启动该服务，是否继续? Y/N
Y
已确认请求，服务即将关闭...
服务端主动与您中断连接
再见!
```

## slc 命令

下沉器本地缓存操作。

### 语法

```text
usage: slc -l sinker-info-id
slc -c
下沉器本地缓存操作
 -c                 清除缓存
 -l <sinker-info-id>   查看指定下沉器的详细信息，如果本地缓存中不存在，则尝试抓取
```

### 示例

#### 查看下沉器

```text
slc -l 1
sinkerInfo: SinkerInfo{key=LongIdKey{longId=1}, type='test', label='测试下沉器', etc...}
sinker: com.dwarfeng.judge.impl.handler.sinker.TestSinker@etc...
OK
```

#### 清除缓存

```text
slc -c
本地缓存已清除
OK
```

## ss 命令

下沉会话操作。

### 语法

```text
usage: ss -l sinker-info-id
ss -cnc
下沉会话操作
 -cnc                 关闭并清除下沉会话
 -l <sinker-info-id>   查看指定下沉器的会话信息
```

### 示例

#### 查看下沉会话

```text
ss -l 1
sinkerInfo: SinkerInfo{key=LongIdKey{longId=1}, type='test', label='测试下沉器', etc...}
sinker: com.dwarfeng.judge.impl.handler.sinker.TestSinker@etc...
sinkerSession: com.dwarfeng.judge.impl.handler.sinker.TestSinkerSession@etc...
OK
```

#### 关闭并清除下沉会话

```text
ss -cnc
本地缓存已清除
OK
```

## supervise 命令

主管处理器操作/查看。

### 语法

```text
usage: supervise -online
supervise -offline
supervise -start
supervise -stop
supervise -status
主管处理器操作/查看
 -offline   下线主管处理器
 -online    上线主管处理器
 -start     启动主管处理器
 -status    查看主管处理器状态
 -stop      停止主管处理器
```

### 示例

#### 查看主管处理器状态

```text
supervise -status
online: true, latch holding: false, started: true, working: false.
OK
```

#### 上线主管处理器

```text
supervise -online
主管处理器已上线!
OK
```

#### 下线主管处理器

```text
supervise -offline
主管处理器已下线!
OK
```

#### 启动主管处理器

```text
supervise -start
主管处理器已启动!
OK
```

#### 停止主管处理器

```text
supervise -stop
主管处理器已停止!
OK
```

## support 命令

支持操作。

重置支持实体。

### 语法

```text
usage: support --reset-analyser
support --reset-driver
support --reset-judger
support --reset-sinker
support --reset-provider
support --reset-visualizer
support --reset-adapter
support --reset-filter
支持操作
    --reset-adapter    重置适配器
    --reset-analyser   重置分析器支持
    --reset-driver     重置驱动器支持
    --reset-filter     重置过滤器
    --reset-judger     重置判断器支持
    --reset-provider   重置提供器
    --reset-sinker     重置下沉器
    --reset-visualizer 重置可视化器支持
```

### 示例

#### 重置分析器支持

```text
support --reset-analyser
重置分析器支持成功。
OK
```

#### 重置驱动器支持

```text
support --reset-driver
重置驱动器支持成功。
OK
```

#### 重置判断器支持

```text
support --reset-judger
重置判断器支持成功。
OK
```

#### 重置下沉器

```text
support --reset-sinker
重置下沉器成功。
OK
```

#### 重置提供器

```text
support --reset-provider
重置提供器成功。
OK
```

#### 重置可视化器支持

```text
support --reset-visualizer
重置可视化器支持成功。
OK
```

#### 重置适配器

```text
support --reset-adapter
重置适配器成功。
OK
```

#### 重置过滤器

```text
support --reset-filter
重置过滤器成功。
OK
```

## tc 命令

任务检查处理器操作/查看。

### 语法

```text
usage: tc -online
tc -offline
tc -start
tc -stop
tc -status
任务检查处理器操作/查看
 -offline   下线任务检查处理器
 -online    上线任务检查处理器
 -start     启动任务检查处理器
 -status    查看任务检查处理器状态
 -stop      停止任务检查处理器
```

### 示例

#### 查看任务检查处理器状态

```text
tc -status
online: true, latch holding: false, started: true, working: false.
OK
```

#### 上线任务检查处理器

```text
tc -online
任务检查处理器已上线!
OK
```

#### 下线任务检查处理器

```text
tc -offline
任务检查处理器已下线!
OK
```

#### 启动任务检查处理器

```text
tc -start
任务检查处理器已启动!
OK
```

#### 停止任务检查处理器

```text
tc -stop
任务检查处理器已停止!
OK
```

## uptime 命令

系统运行时间。

### 语法

```text
usage: uptime
系统运行时间
```

### 示例

```text
uptime
00:06:55 up 0 days, 00:15
OK
```

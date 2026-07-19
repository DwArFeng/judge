# Quick Start - 快速开始

## 确认系统需求

- CPU：2 核以上。
- 内存：4G 以上。
- 硬盘：100G 以上。
- CentOS 7。
- JRE 1.8。
- MySQL 8.0.19。
- Redis 5.0.7。
- Zookeeper 3.5.5。
- snowflake-distributed-service 1.6.4.a。

## 获取软件包

从 Github 上获取软件包，软件包可以从 Github 的 Release 页面下载。

## 解压软件包

软件包的名称格式为 `judge-node-${version}-release.tar.gz`，其中 `${version}` 为软件包的版本号。

使用工具软件，将软件包上传至服务器 `/usr/local` 目录下，解压软件包。

```shell
cd /usr/local
tar -zxvf judge-node-${version}-release.tar.gz
mv judge-node-${version}-release/judge-node-${version} judge
```

## 数据库初始化

连接到 MySQL 数据库，执行如下 SQL 语句：

```sql
SET NAMES utf8mb4;

CREATE DATABASE IF NOT EXISTS `judge` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin;

USE `judge`;

CREATE TABLE IF NOT EXISTS `tbl_section` (
  `id` bigint NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `name` varchar(50) COLLATE utf8mb4_0900_bin NOT NULL,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_driver_info` (
  `id` bigint NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `param` text COLLATE utf8mb4_0900_bin,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `section_id` bigint DEFAULT NULL,
  `type` varchar(50) COLLATE utf8mb4_0900_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiegyxlrs2nokaod9hlwsq9wdr` (`section_id`),
  CONSTRAINT `FKiegyxlrs2nokaod9hlwsq9wdr` FOREIGN KEY (`section_id`) REFERENCES `tbl_section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_provider_info` (
  `id` bigint NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `param` text COLLATE utf8mb4_0900_bin,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `type` varchar(50) COLLATE utf8mb4_0900_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_analyser_info` (
  `id` bigint NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `column_index` int NOT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `param` text COLLATE utf8mb4_0900_bin,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `section_id` bigint DEFAULT NULL,
  `type` varchar(50) COLLATE utf8mb4_0900_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn6jd1ipq7fh3u8yh79haxhivc` (`section_id`),
  CONSTRAINT `FKn6jd1ipq7fh3u8yh79haxhivc` FOREIGN KEY (`section_id`) REFERENCES `tbl_section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_judger_info` (
  `id` bigint NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `column_index` int NOT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `param` text COLLATE utf8mb4_0900_bin,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `section_id` bigint DEFAULT NULL,
  `type` varchar(50) COLLATE utf8mb4_0900_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd8hnvgdwoqq8gpaldwfp8qy5a` (`section_id`),
  CONSTRAINT `FKd8hnvgdwoqq8gpaldwfp8qy5a` FOREIGN KEY (`section_id`) REFERENCES `tbl_section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_visualizer_info` (
  `id` bigint NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `column_index` int NOT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `param` text COLLATE utf8mb4_0900_bin,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `section_id` bigint DEFAULT NULL,
  `type` varchar(50) COLLATE utf8mb4_0900_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcwhyyge23ww4ek9odfhu735l8` (`section_id`),
  CONSTRAINT `FKcwhyyge23ww4ek9odfhu735l8` FOREIGN KEY (`section_id`) REFERENCES `tbl_section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_sinker_info` (
  `id` bigint NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `param` text COLLATE utf8mb4_0900_bin,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `type` varchar(50) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_sinker_relation` (
  `section_id` bigint NOT NULL,
  `sinker_id` bigint NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  PRIMARY KEY (`section_id`,`sinker_id`),
  KEY `FKansh94vlovk0s21t99hmfbymo` (`sinker_id`),
  CONSTRAINT `FKansh94vlovk0s21t99hmfbymo` FOREIGN KEY (`sinker_id`) REFERENCES `tbl_sinker_info` (`id`),
  CONSTRAINT `FKk7wgwl50ndy6efh782vrcrf1i` FOREIGN KEY (`section_id`) REFERENCES `tbl_section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_sinker_meta` (
  `meta_id` varchar(100) COLLATE utf8mb4_0900_bin NOT NULL,
  `section_id` bigint NOT NULL,
  `sinker_id` bigint NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `value` text COLLATE utf8mb4_0900_bin,
  PRIMARY KEY (`meta_id`,`section_id`,`sinker_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_task` (
  `id` bigint NOT NULL,
  `anchor_message` varchar(200) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `died_date` datetime(6) DEFAULT NULL,
  `duration` bigint DEFAULT NULL,
  `ended_date` datetime(6) DEFAULT NULL,
  `expired_date` datetime(6) DEFAULT NULL,
  `section_id` bigint DEFAULT NULL,
  `should_die_date` datetime(6) DEFAULT NULL,
  `should_expire_date` datetime(6) DEFAULT NULL,
  `started_date` datetime(6) DEFAULT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8def55ow2kwy16n0ql932706v` (`section_id`),
  CONSTRAINT `FK8def55ow2kwy16n0ql932706v` FOREIGN KEY (`section_id`) REFERENCES `tbl_section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_task_event` (
  `id` bigint NOT NULL,
  `happened_date` datetime(6) NOT NULL,
  `message` varchar(200) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `task_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK20oju8e2sq2kc14dt9b1fyss2` (`task_id`),
  CONSTRAINT `FK20oju8e2sq2kc14dt9b1fyss2` FOREIGN KEY (`task_id`) REFERENCES `tbl_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_analysis` (
  `data_id` varchar(100) COLLATE utf8mb4_0900_bin NOT NULL,
  `task_id` bigint NOT NULL,
  `boolean_value` bit(1) DEFAULT NULL,
  `data_type` int NOT NULL,
  `date_value` datetime(6) DEFAULT NULL,
  `double_value` double DEFAULT NULL,
  `file_pack_value` bigint DEFAULT NULL,
  `file_value` bigint DEFAULT NULL,
  `long_value` bigint DEFAULT NULL,
  `picture_pack_value` bigint DEFAULT NULL,
  `picture_value` bigint DEFAULT NULL,
  `string_value` text COLLATE utf8mb4_0900_bin,
  PRIMARY KEY (`data_id`,`task_id`),
  KEY `FK9t32o3dgtceec3cgtx5dv5bvi` (`task_id`),
  CONSTRAINT `FK9t32o3dgtceec3cgtx5dv5bvi` FOREIGN KEY (`task_id`) REFERENCES `tbl_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_judgement` (
  `data_id` varchar(100) COLLATE utf8mb4_0900_bin NOT NULL,
  `task_id` bigint NOT NULL,
  `message` varchar(200) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `value` double NOT NULL,
  PRIMARY KEY (`data_id`,`task_id`),
  KEY `FKmdhlvwhvnm4dw4sibmrtyjm2a` (`task_id`),
  CONSTRAINT `FKmdhlvwhvnm4dw4sibmrtyjm2a` FOREIGN KEY (`task_id`) REFERENCES `tbl_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_visualize_data` (
  `perspective_id` varchar(100) COLLATE utf8mb4_0900_bin NOT NULL,
  `task_id` bigint NOT NULL,
  `content` text COLLATE utf8mb4_0900_bin,
  PRIMARY KEY (`perspective_id`,`task_id`),
  KEY `FKhxse6mrccj6vv8gnh7cc1npo3` (`task_id`),
  CONSTRAINT `FKhxse6mrccj6vv8gnh7cc1npo3` FOREIGN KEY (`task_id`) REFERENCES `tbl_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

INSERT INTO `tbl_section` (`id`, `created_datamark`, `enabled`, `modified_datamark`, `name`, `remark`)
VALUES (1, 'judge-node', b'1', 'judge-node', 'QuickStart 演示部件', '演示 Provider → Analyser → Judger → Visualizer → Sinker 链路')
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `enabled` = VALUES(`enabled`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `name` = VALUES(`name`),
  `remark` = VALUES(`remark`);

INSERT INTO `tbl_driver_info` (`id`, `created_datamark`, `enabled`, `modified_datamark`, `param`, `remark`, `section_id`, `type`)
VALUES (1, 'judge-node', b'1', 'judge-node', '60000', 'QuickStart 固定间隔驱动器', 1, 'fixed_delay_driver')
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `enabled` = VALUES(`enabled`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `param` = VALUES(`param`),
  `remark` = VALUES(`remark`),
  `section_id` = VALUES(`section_id`),
  `type` = VALUES(`type`);

INSERT INTO `tbl_provider_info` (`id`, `created_datamark`, `enabled`, `modified_datamark`, `param`, `remark`, `type`)
VALUES (1, 'judge-node', b'1', 'judge-node', 'package groovy

import com.dwarfeng.judge.impl.handler.provider.groovy.Processor
import com.dwarfeng.judge.stack.handler.ProviderSession.LookupInfo
import com.dwarfeng.judge.stack.handler.ProviderSession.LookupResult

class QuickStartProviderProcessor implements Processor {

    @Override
    void openSession() throws Exception {
    }

    @Override
    void closeSession() throws Exception {
    }

    @Override
    LookupResult lookup(LookupInfo info) throws Exception {
        final double value = 0.82D
        final Map<String, Object> row = new LinkedHashMap<String, Object>()
        row.put("label", "QuickStart 演示数据")
        row.put("value", value)
        row.put("timestamp", System.currentTimeMillis())

        final List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>()
        datas.add(row)

        final Map<String, Object> meta = new LinkedHashMap<String, Object>()
        meta.put("preset", info.getPreset())
        meta.put("source", "quickstart")
        return new LookupResult(datas, meta)
    }
}
', 'QuickStart Groovy 提供器', 'groovy')
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `enabled` = VALUES(`enabled`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `param` = VALUES(`param`),
  `remark` = VALUES(`remark`),
  `type` = VALUES(`type`);

INSERT INTO `tbl_analyser_info` (`id`, `created_datamark`, `enabled`, `column_index`, `modified_datamark`, `param`, `remark`, `section_id`, `type`)
VALUES (1, 'judge-node', b'1', 0, 'judge-node', 'package groovy

import com.dwarfeng.judge.impl.handler.analyser.groovy.Processor
import com.dwarfeng.judge.sdk.util.Constants
import com.dwarfeng.judge.stack.bean.dto.AnalysisUpsertInfo
import com.dwarfeng.judge.stack.bean.dto.ProvideLookupInfo
import com.dwarfeng.judge.stack.bean.dto.TaskEventCreateInfo
import com.dwarfeng.judge.stack.handler.Analyser
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey

class QuickStartAnalyserProcessor implements Processor {

    @Override
    void analyse(Analyser.Context context) throws Exception {
        final lookupResult = context.lookupProvide(new ProvideLookupInfo(new LongIdKey(1L), null, null, "quickstart", new Object[0]))
        final datas = lookupResult.getDatas()
        if (datas.isEmpty()) {
            throw new IllegalStateException("QuickStart provider returned no data")
        }

        final row = datas.get(0)
        final double value = ((Number) row.get("value")).doubleValue()
        context.upsertAnalysis(new AnalysisUpsertInfo(
                context.getTaskKey(), "value", Constants.ANALYSIS_TYPE_DOUBLE, value
        ))
        context.createTaskEvent(new TaskEventCreateInfo(
                context.getTaskKey(), "QuickStart 分析器已从 Provider 读取 value=" + value
        ))
    }
}
', 'QuickStart Groovy 分析器', 1, 'groovy_analyser')
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `enabled` = VALUES(`enabled`),
  `column_index` = VALUES(`column_index`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `param` = VALUES(`param`),
  `remark` = VALUES(`remark`),
  `section_id` = VALUES(`section_id`),
  `type` = VALUES(`type`);

INSERT INTO `tbl_judger_info` (`id`, `created_datamark`, `enabled`, `column_index`, `modified_datamark`, `param`, `remark`, `section_id`, `type`)
VALUES (1, 'judge-node', b'1', 0, 'judge-node', '{
  "output_judgement_data_id": "score",
  "input_source_type": "analysis",
  "input_constant_value": "0.0",
  "input_analysis_data_id": "value",
  "input_false_value": "0.0",
  "input_true_value": "1.0"
}', 'QuickStart 本征判断器', 1, 'identity_judger')
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `enabled` = VALUES(`enabled`),
  `column_index` = VALUES(`column_index`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `param` = VALUES(`param`),
  `remark` = VALUES(`remark`),
  `section_id` = VALUES(`section_id`),
  `type` = VALUES(`type`);

INSERT INTO `tbl_visualizer_info` (`id`, `created_datamark`, `enabled`, `column_index`, `modified_datamark`, `param`, `remark`, `section_id`, `type`)
VALUES (1, 'judge-node', b'1', 0, 'judge-node', 'package groovy

import com.dwarfeng.judge.impl.handler.visualizer.Processor
import com.dwarfeng.judge.stack.bean.dto.AnalysisInspectInfo
import com.dwarfeng.judge.stack.bean.dto.JudgementInspectInfo
import com.dwarfeng.judge.stack.bean.dto.TaskEventCreateInfo
import com.dwarfeng.judge.stack.bean.dto.VisualizeDataUpsertInfo
import com.dwarfeng.judge.stack.handler.Visualizer

class QuickStartVisualizerProcessor implements Processor {

    @Override
    void visualize(Visualizer.Context context) throws Exception {
        final valueResult = context.inspectAnalysis(new AnalysisInspectInfo(context.getTaskKey(), "value"))
        final scoreResult = context.inspectJudgement(new JudgementInspectInfo(context.getTaskKey(), "score"))
        final value = valueResult == null ? "N/A" : valueResult.getValue()
        final score = scoreResult == null ? "N/A" : scoreResult.getValue()

        final String html = "<section class=\"judge-quickstart-card\">" +
                "<h3>QuickStart 可视化结果</h3>" +
                "<p>Provider 采集值：<strong>" + value + "</strong></p>" +
                "<p>Identity Judger 得分：<strong>" + score + "</strong></p>" +
                "<p>该片段由 Groovy Visualizer 生成。</p>" +
                "</section>"

        context.upsertVisualizeData(new VisualizeDataUpsertInfo(
                context.getTaskKey(), "quickstart.html", html
        ))
        context.createTaskEvent(new TaskEventCreateInfo(
                context.getTaskKey(), "QuickStart 可视化器已生成 HTML 片段"
        ))
    }
}
', 'QuickStart Groovy 可视化器', 1, 'groovy_visualizer')
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `enabled` = VALUES(`enabled`),
  `column_index` = VALUES(`column_index`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `param` = VALUES(`param`),
  `remark` = VALUES(`remark`),
  `section_id` = VALUES(`section_id`),
  `type` = VALUES(`type`);

INSERT INTO `tbl_sinker_info` (`id`, `created_datamark`, `enabled`, `modified_datamark`, `param`, `remark`, `type`)
VALUES (1, 'judge-node', b'1', 'judge-node', '', 'QuickStart 模拟下沉器', 'mock')
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `enabled` = VALUES(`enabled`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `param` = VALUES(`param`),
  `remark` = VALUES(`remark`),
  `type` = VALUES(`type`);

INSERT INTO `tbl_sinker_relation` (`section_id`, `sinker_id`, `created_datamark`, `enabled`, `modified_datamark`, `remark`)
VALUES (1, 1, 'judge-node', b'1', 'judge-node', 'QuickStart 部件与模拟下沉器绑定')
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `enabled` = VALUES(`enabled`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `remark` = VALUES(`remark`);

INSERT INTO `tbl_sinker_meta` (`meta_id`, `section_id`, `sinker_id`, `created_datamark`, `modified_datamark`, `remark`, `value`)
VALUES ('sink_delay', 1, 1, 'judge-node', 'judge-node', 'QuickStart 模拟下沉器下沉延时', '0')
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `remark` = VALUES(`remark`),
  `value` = VALUES(`value`);

INSERT INTO `tbl_sinker_meta` (`meta_id`, `section_id`, `sinker_id`, `created_datamark`, `modified_datamark`, `remark`, `value`)
VALUES ('log_level', 1, 1, 'judge-node', 'judge-node', 'QuickStart 模拟下沉器日志等级', 'info')
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `remark` = VALUES(`remark`),
  `value` = VALUES(`value`);
```

## 最小化配置

下文列出了启动程序需要改动的最少的配置文件，每个配置文件中仅展示需要改动的配置项。

`conf/curator/connection.properties` 文件中配置 curator 连接信息。

```properties
curator.connect.connect_string=your-host-here:2181
```

`conf/database/connection.properties` 文件中配置数据库连接信息。

```properties
jdbc.url=jdbc:mysql://your-host-here:3306/judge?serverTimezone=Asia/Shanghai&autoReconnect=true
jdbc.username=root
jdbc.password=your-password-here
```

`conf/dubbo/connection.properties` 文件中配置 dubbo 连接信息。

```properties
dubbo.registry.zookeeper.address=zookeeper://your-host-here:2181
dubbo.protocol.dubbo.host=your-host-here
```

`conf/redis/connection.properties` 文件中配置 redis 连接信息。

```properties
redis.hostName=your-host-here
redis.port=6379
redis.password=your-password-here
```

`conf/judge/receive.properties` 文件中配置接收器信息。发布包中的默认接收器配置为 `do_nothing`，
按照本文验证最小演示链路时使用默认值即可。

```properties
receiver.type=do_nothing
```

`conf/judge/dispatch.properties` 文件中配置分发器信息。发布包中的默认分发器配置为 `drain`，
按照本文验证最小演示链路时使用默认值即可。

```properties
dispatcher.type=drain
```

`conf/judge/push.properties` 文件中配置推送器信息。发布包中的默认推送器配置为 `drain`，
按照本文验证最小演示链路时使用默认值即可。

```properties
pusher.type=drain
```

## 修改可选配置

下文列出了启动程序需要改动的可选的配置文件，每个配置文件中仅展示需要改动的配置项。

`opt/opt-provider.xml` 提供器可选配置。
发布包中的提供器扩展默认以注释形式提供，如需按照本文使用 Groovy 提供器生成演示数据， 需要取消下列组件扫描配置的注释。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 以下注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringXmlModelInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 加载 GroovyProvider。 -->
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.provider.groovy"/>
</beans>
```

`opt/opt-analyser.xml` 分析器可选配置。
发布包中的分析器扩展默认以注释形式提供，如需按照本文使用 Groovy 分析器调用提供器并生成分析结果，需要取消下列组件扫描配置的注释。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 以下注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringXmlModelInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 加载 GroovyAnalyser -->
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.analyser.groovy"/>
</beans>
```

`opt/opt-judger.xml` 判断器可选配置。
发布包中的判断器扩展默认以注释形式提供，如需按照本文使用本征判断器将分析结果转换为判断结果，需要取消下列组件扫描配置的注释。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 以下注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringXmlModelInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 加载 IdentityJudger -->
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.judger.identity"/>
</beans>
```

`opt/opt-visualizer.xml` 可视化器可选配置。
发布包中的可视化器扩展默认以注释形式提供，如需按照本文使用 Groovy 可视化器生成 HTML 片段，需要取消下列组件扫描配置的注释。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 以下注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringXmlModelInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!--扫描 handler 的实现包。 -->
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.visualizer" use-default-filters="false">
        <!-- 加载 GroovyVisualizer -->
        <context:include-filter
                type="assignable"
                expression="com.dwarfeng.judge.impl.handler.visualizer.GroovyVisualizerRegistry"
        />
    </context:component-scan>
</beans>
```

`opt/opt-sinker.xml` 下沉器可选配置。
发布包中的下沉器扩展默认以注释形式提供，如需按照本文使用 Mock 下沉器模拟数据下沉，需要取消下列组件扫描配置的注释。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 以下注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringXmlModelInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 加载 MockSinker。 -->
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.sinker.mock"/>
</beans>
```

`opt/opt-driver.xml` 驱动器可选配置。
发布包中的驱动器扩展默认以注释形式提供，如需按照本文使用 FixedDelay 驱动器定时触发演示部件，需要取消下列组件扫描配置的注释。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 以下注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringXmlModelInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!--扫描 handler 的实现包。 -->
    <context:component-scan
            base-package="com.dwarfeng.judge.impl.handler.driver" use-default-filters="false"
    >
        <!-- 加载 FixedDelayDriver -->
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.driver.FixedDelayDriverProvider"
        />
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.driver.FixedDelayDriverSupporter"
        />
    </context:component-scan>
</beans>
```

## 启动程序

在 `/usr/local/judge` 目录下执行如下命令：

```shell
sh  bin/judge-start.sh
```

1. 观察数据库，程序会在 `tbl_task` 表中写入演示部件生成的任务数据。
2. 观察数据库，程序会在 `tbl_task_event` 表中写入任务执行过程中的事件数据。
3. 观察数据库，程序会在 `tbl_analysis` 表中写入 data_id 为 `value` 的分析结果。
4. 观察数据库，程序会在 `tbl_judgement` 表中写入 data_id 为 `score` 的判断结果。
5. 观察数据库，程序会在 `tbl_visualize_data` 表中写入 perspective_id 为 `quickstart.html` 的 HTML 可视化片段。
6. 观察程序日志，Mock 下沉器会模拟下沉任务、分析结果、判断结果以及可视化数据。

## 停止程序

在 `/usr/local/judge` 目录下执行如下命令：

```shell
sh  bin/judge-stop.sh
```

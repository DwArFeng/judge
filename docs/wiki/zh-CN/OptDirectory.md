# Opt Directory - 可选配置目录

## 总览

本项目的可选配置位于 `opt/` 目录下，包括：

```text
opt
├─ opt-adapter.xml
├─ opt-analyser.xml
├─ opt-dispatcher.xml
├─ opt-driver.xml
├─ opt-filter.xml
├─ opt-judger.xml
├─ opt-provider.xml
├─ opt-pusher.xml
├─ opt-receiver.xml
├─ opt-resetter.xml
├─ opt-sinker-kafka-native.xml
├─ opt-sinker.xml
└─ opt-visualizer.xml
```

所有的可选配置都为每个单独的可选项提供了加载配置，默认是注释的，如果用户需要使用某个可选项，
只需要将其对应的配置项取消注释即可。

此处展示默认的可选配置文件。

## opt-adapter.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 加载 GroovyAdapter。 -->
    <!--
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.adapter.groovy"/>
    -->
</beans>
```

## opt-analyser.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
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
    <!--
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.analyser.groovy"/>
    -->
</beans>
```

## opt-dispatcher.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
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
            base-package="com.dwarfeng.judge.impl.handler.dispatcher" use-default-filters="false"
    >
        <!-- 加载 DrainDispatcher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.dispatcher.DrainDispatcher"
        />
        -->

        <!-- 加载 InjvmDispatcher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.dispatcher.InjvmDispatcher"
        />
        -->

        <!-- 加载 KafkaDispatcher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.dispatcher.KafkaDispatcher"
        />
        -->

        <!-- 加载 DubboDispatcher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.dispatcher.DubboDispatcher"
        />
        -->
    </context:component-scan>
</beans>
```

## opt-driver.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
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
        <!-- 加载 CronDriver -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.driver.CronDriverProvider"
        />
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.driver.CronDriverSupporter"
        />
        -->

        <!-- 加载 DctiKafkaDriver -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.driver.DctiKafkaDriverProvider"
        />
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.driver.DctiKafkaDriverSupporter"
        />
        -->

        <!-- 加载 FixedDelayDriver -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.driver.FixedDelayDriverProvider"
        />
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.driver.FixedDelayDriverSupporter"
        />
        -->

        <!-- 加载 FixedRateDriver -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.driver.FixedRateDriverProvider"
        />
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.driver.FixedRateDriverSupporter"
        />
        -->
    </context:component-scan>
</beans>
```

## opt-filter.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 加载 GroovyFilter。 -->
    <!--
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.filter.groovy"/>
    -->
</beans>
```

## opt-judger.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 加载 GroovyJudger -->
    <!--
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.judger.groovy"/>
    -->
</beans>
```

## opt-provider.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
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
    <!--
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.provider.groovy"/>
    -->
</beans>
```

## opt-pusher.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
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
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.pusher" use-default-filters="false">
        <!-- 加载 DrainPusher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.pusher.DrainPusher"
        />
        -->

        <!-- 加载 LogPusher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.pusher.LogPusher"
        />
        -->

        <!-- 加载 MultiPusher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.pusher.MultiPusher"
        />
        -->

        <!-- 加载 NativeKafkaPusher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.pusher.NativeKafkaPusher"
        />
        -->
    </context:component-scan>
</beans>
```

## opt-receiver.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
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
            base-package="com.dwarfeng.judge.impl.handler.receiver" use-default-filters="false"
    >
        <!-- 加载 DoNothingReceiver -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.receiver.DoNothingReceiver"
        />
        -->

        <!-- 加载 InjvmReceiver -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.receiver.InjvmReceiver"
        />
        -->

        <!-- 加载 KafkaReceiver -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.receiver.KafkaReceiver"
        />
        -->

        <!-- 加载 DubboReceiver -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.receiver.DubboReceiver"
        />
        -->
    </context:component-scan>
</beans>
```

## opt-resetter.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 扫描 handler 的实现包。 -->
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.resetter" use-default-filters="false">
        <!-- 加载 NeverResetter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.resetter.NeverResetter"
        />
        -->

        <!-- 加载 FixedDelayResetter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.resetter.FixedDelayResetter"
        />
        -->

        <!-- 加载 FixedRateResetter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.resetter.FixedRateResetter"
        />
        -->

        <!-- 加载 CronResetter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.resetter.CronResetter"
        />
        -->

        <!-- 加载 DubboResetter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.judge.impl.handler.resetter.DubboResetter"
        />
        -->
    </context:component-scan>
</beans>
```

## opt-sinker.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
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
    <!--
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.sinker.mock"/>
    -->

    <!-- 加载 NativeKafkaSinker。 -->
    <!--
    <context:component-scan base-package="com.dwarfeng.judge.impl.handler.sinker.kafka.nati"/>
    -->
</beans>
```

## opt-sinker-kafka-native.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!--
            本配置文件为 NativeKafkaSinker 提供必要的 OpcUaHandler 等 bean。
            如果需要使用 NativeKafkaSinker，请取消下方注释，并按照实际情况对下方参数进行配置。

            可以在下方的参数中直接赋值，也可以使用 value placeholder 进行占位，
            并将真正的配置值以 properties 文件的形式放在 confext 目录中。

            如一个 OpcUaHandler 不满足使用需求（如读取多台设备的数据），应该将下方的 bean 定义文件复制多份，分配不同的 id，
            为 ApplicationContext 提供多个 bean。

            ProducerFactory 参数说明：
            bootstrapServers: 引导服务器集群。
            retries: 发送失败重试次数，acks 设置为 0 时不生效。
            linger: 生产者在发送批处理之前等待更多消息加入批处理的时间，单位为毫秒。该值是一条记录被发送之前的最大延迟。
              如果记录在等待中达到了 batch_size 的大小，就会立即把它们发送出去。
            bufferMemory: 生产者的批处理缓冲区大小。
            batchSize: 批处理条数: 当多个记录被发送到同一个分区时，生产者会尝试将记录合并到更少的请求中。
              这有助于客户端和服务器的性能。如果记录等待时间达到了 linger 值，就会被立即发送。
            acks: 生产者与服务器的确认模式，可选值为: 0, 1, all。
              0: 生产者不会等待服务器的确认，继续发送下一条消息。
              1: 生产者会等待服务器的确认，继续发送下一条消息。
              all: 生产者会等待服务器及其所有副本的确认，继续发送下一条消息。
            transactionPrefix: Kafka 事务的前缀。
    -->
    <!--
    <bean
            id="nativeKafkaSinkerProducerFactory"
            class="com.dwarfeng.judge.impl.handler.sinker.kafka.nati.NativeKafkaSinkerFactory"
            factory-method="newProducerFactory"
    >
        <constructor-arg name="bootstrapServers" value="your-ip1:9092,your-ip2:9092,your-ip3:9092"/>
        <constructor-arg name="retries" value="3"/>
        <constructor-arg name="linger" value="10"/>
        <constructor-arg name="bufferMemory" value="40960"/>
        <constructor-arg name="batchSize" value="4096"/>
        <constructor-arg name="acks" value="all"/>
        <constructor-arg name="transactionPrefix" value="judge.sinker.kafka.native"/>
    </bean>
    <bean
            id="nativeKafkaSinkerKafkaTemplate"
            class="com.dwarfeng.judge.impl.handler.sinker.kafka.nati.NativeKafkaSinkerFactory"
            factory-method="newKafkaTemplate"
    >
        <constructor-arg name="producerFactory" ref="nativeKafkaSinkerProducerFactory"/>
    </bean>
    <bean
            id="nativeKafkaSinkerKafkaTransactionManager"
            class="com.dwarfeng.judge.impl.handler.sinker.kafka.nati.NativeKafkaSinkerFactory"
            factory-method="newKafkaTransactionManager"
    >
        <constructor-arg name="producerFactory" ref="nativeKafkaSinkerProducerFactory"/>
    </bean>
    -->
</beans>
```

## opt-visualizer.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
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
        <!--
        <context:include-filter
                type="assignable"
                expression="com.dwarfeng.judge.impl.handler.visualizer.GroovyVisualizerRegistry"
        />
        -->
    </context:component-scan>
</beans>
```

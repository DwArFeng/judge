package com.dwarfeng.judge.impl.configuration;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dwarfeng.judge.sdk.bean.entity.FastJsonDriverInfo;
import com.dwarfeng.judge.sdk.bean.entity.FastJsonJudgerInfo;
import com.dwarfeng.judge.sdk.bean.entity.FastJsonSection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson autotype 白名单");
        //实体对象。
        ParserConfig.getGlobalInstance().addAccept(FastJsonSection.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonDriverInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonJudgerInfo.class.getCanonicalName());
        LOGGER.debug("FastJson autotype 白名单配置完毕");
    }
}

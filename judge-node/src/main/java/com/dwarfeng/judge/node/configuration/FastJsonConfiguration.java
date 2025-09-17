package com.dwarfeng.judge.node.configuration;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dwarfeng.judge.sdk.bean.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson autotype 白名单");
        //实体对象。
        ParserConfig.getGlobalInstance().addAccept(FastJsonAnalyserInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonAnalyserSupport.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonAnalyserVariable.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonTask.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonTaskEvent.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonAnalysis.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonDriverInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonDriverSupport.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonJudgementHistory.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonJudgementModal.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonJudgerInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonJudgerSupport.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonJudgerVariable.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonSection.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonAnalysisFileInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonAnalysisFilePack.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonAnalysisFilePackItemInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonAnalysisPictureInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonAnalysisPicturePack.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonAnalysisPicturePackItemInfo.class.getCanonicalName());
        LOGGER.debug("FastJson autotype 白名单配置完毕");
    }
}

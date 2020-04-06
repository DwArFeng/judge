package com.dwarfeng.judge.impl.handler.judger;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.impl.handler.JudgerSupporter;
import org.springframework.stereotype.Component;

/**
 * 阈值判断器支持器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Component
public class ThresholdJudgerSupporter implements JudgerSupporter {

    public static final String SUPPORT_TYPE = "threshold_judger";

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "阈值判断器";
    }

    @Override
    public String provideDescription() {
        return "获取数据点的实时值，并且与预设的阈值进行比较。如果实时值与阈值满足约束关系，则返回1.0，否则返回0.0.";
    }

    @Override
    public String provideExampleContent() {
        ThresholdJudgerMaker.Config config = new ThresholdJudgerMaker.Config(
                692151005587959809L,
                15.0,
                ThresholdJudgerMaker.Config.GT);
        return JSON.toJSONString(config) +
                '\n' +
                "# 以下的内容为注释，在实际配置时删除这些注释。\n" +
                "# 可用的constraint内容 (可忽略大小写): \n" +
                "#   LE: judgementInfo.value的值 等于 配置的 value\n" +
                "#   LE: judgementInfo.value的值 不等于 配置的 value\n" +
                "#   LE: judgementInfo.value的值 小于等于 配置的 value\n" +
                "#   LT: judgementInfo.value的值 小于 配置的 value\n" +
                "#   GE: judgementInfo.value的值 大于等于 配置的 value\n" +
                "#   GT: judgementInfo.value的值 大于 配置的 value";
    }
}

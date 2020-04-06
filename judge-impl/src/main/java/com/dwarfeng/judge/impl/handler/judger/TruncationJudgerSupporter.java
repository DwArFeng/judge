package com.dwarfeng.judge.impl.handler.judger;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.impl.handler.JudgerSupporter;
import org.springframework.stereotype.Component;

/**
 * 截断判断器智造器
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Component
public class TruncationJudgerSupporter implements JudgerSupporter {

    public static final String SUPPORT_TYPE = "truncation_judger";

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "截断判断器";
    }

    @Override
    public String provideDescription() {
        return "给定一个最大值和最小值，并取出指定数据点的实时值。" +
                "若实时值低于最小值，则返回0.0; " +
                "若实时值高于最大值，则返回1.0;" +
                "若实时值介于最小值和最大值之间，则返回线性的中间值。" +
                "如果反转属性为true，则反过来，低于最小值是1.0，高于最大值是0.0";
    }

    @Override
    public String provideExampleContent() {
        TruncationJudgerMaker.Config config = new TruncationJudgerMaker.Config(692151005587959809L, -50.0, 100.0, false);
        return JSON.toJSONString(config, true);
    }
}

package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dwarfeng.dcti.stack.bean.dto.DataInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgedValue;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Objects;

/**
 * 判断结果工具类。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public final class JudgedValueUtil {

    public static DataInfo toDataInfo(JudgedValue judgedValue) {
        if (Objects.isNull(judgedValue)) {
            return null;
        }

        return new DataInfo(
                judgedValue.getJudgerKey().getLongId(),
                JSON.toJSONString(FastJsonJudgementInfo.of(judgedValue.getJudgementInfo()),
                        SerializerFeature.DisableCircularReferenceDetect),
                judgedValue.getHappenedDate()
        );
    }

    public static JudgedValue fromDataInfo(DataInfo dataInfo) {
        if (Objects.isNull(dataInfo)) {
            return null;
        }

        return new JudgedValue(
                new LongIdKey(dataInfo.getPointLongId()),
                FastJsonJudgementInfo.toStackBean(JSON.parseObject(dataInfo.getValue(), FastJsonJudgementInfo.class)),
                dataInfo.getHappenedDate()
        );
    }

    private JudgedValueUtil() {
        throw new IllegalStateException("禁止外部实例化");
    }
}

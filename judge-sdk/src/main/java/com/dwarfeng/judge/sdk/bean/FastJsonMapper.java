package com.dwarfeng.judge.sdk.bean;

import com.dwarfeng.judge.sdk.bean.entity.*;
import com.dwarfeng.judge.sdk.bean.key.FastJsonVariableKey;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.VariableKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * FastJson Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.6.0
 */
@Mapper
public interface FastJsonMapper {

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    FastJsonVariableKey variableKeyToFastJson(VariableKey variableKey);

    @InheritInverseConfiguration
    VariableKey variableKeyFromFastJson(FastJsonVariableKey fastJsonVariableKey);

    FastJsonDriverInfo driverInfoToFastJson(DriverInfo driverInfo);

    @InheritInverseConfiguration
    DriverInfo driverInfoFromFastJson(FastJsonDriverInfo fastJsonDriverInfo);

    FastJsonDriverSupport driverSupportToFastJson(DriverSupport driverSupport);

    @InheritInverseConfiguration
    DriverSupport driverSupportFromFastJson(FastJsonDriverSupport fastJsonDriverSupport);

    FastJsonJudgerInfo judgerInfoToFastJson(JudgerInfo judgerInfo);

    @InheritInverseConfiguration
    JudgerInfo judgerInfoFromFastJson(FastJsonJudgerInfo fastJsonJudgerInfo);

    FastJsonJudgerSupport judgerSupportToFastJson(JudgerSupport judgerSupport);

    @InheritInverseConfiguration
    JudgerSupport judgerSupportFromFastJson(FastJsonJudgerSupport fastJsonJudgerSupport);

    FastJsonSection sectionToFastJson(Section section);

    @InheritInverseConfiguration
    Section sectionFromFastJson(FastJsonSection fastJsonSection);

    FastJsonVariable variableToFastJson(Variable variable);

    @InheritInverseConfiguration
    Variable variableFromFastJson(FastJsonVariable fastJsonVariable);
}

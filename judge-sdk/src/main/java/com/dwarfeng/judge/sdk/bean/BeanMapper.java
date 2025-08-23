package com.dwarfeng.judge.sdk.bean;

import com.dwarfeng.judge.sdk.bean.dto.FastJsonJudgerReport;
import com.dwarfeng.judge.sdk.bean.dto.FastJsonSectionReport;
import com.dwarfeng.judge.sdk.bean.dto.JSFixedFastJsonJudgerReport;
import com.dwarfeng.judge.sdk.bean.dto.JSFixedFastJsonSectionReport;
import com.dwarfeng.judge.sdk.bean.entity.*;
import com.dwarfeng.judge.sdk.bean.key.FastJsonVariableKey;
import com.dwarfeng.judge.stack.bean.dto.JudgerReport;
import com.dwarfeng.judge.stack.bean.dto.SectionReport;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.VariableKey;
import com.dwarfeng.subgrade.sdk.bean.key.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * Bean 映射器。
 *
 * <p>
 * 该映射器中包含了 <code>sdk</code> 模块中所有实体与 <code>stack</code> 模块中对应实体的映射方法。
 *
 * @author DwArFeng
 * @since 1.7.0
 */
@Mapper
public interface BeanMapper {

    // -----------------------------------------------------------Subgrade Key-----------------------------------------------------------
    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    JSFixedFastJsonLongIdKey longIdKeyToJSFixedFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromJSFixedFastJson(JSFixedFastJsonLongIdKey jSFixedFastJsonLongIdKey);

    WebInputLongIdKey longIdKeyToWebInput(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromWebInput(WebInputLongIdKey webInputLongIdKey);

    WebInputStringIdKey stringIdKeyToWebInput(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromWebInput(WebInputStringIdKey webInputStringIdKey);

    // -----------------------------------------------------------Judge Key-----------------------------------------------------------
    FastJsonVariableKey variableKeyToFastJson(VariableKey variableKey);

    @InheritInverseConfiguration
    VariableKey variableKeyFromFastJson(FastJsonVariableKey fastJsonVariableKey);

    // -----------------------------------------------------------Judge Entity-----------------------------------------------------------
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

    JSFixedFastJsonDriverInfo driverInfoToJSFixedFastJson(DriverInfo driverInfo);

    @InheritInverseConfiguration
    DriverInfo driverInfoFromJSFixedFastJson(JSFixedFastJsonDriverInfo jSFixedFastJsonDriverInfo);

    JSFixedFastJsonJudgerInfo judgerInfoToJSFixedFastJson(JudgerInfo judgerInfo);

    @InheritInverseConfiguration
    JudgerInfo judgerInfoFromJSFixedFastJson(JSFixedFastJsonJudgerInfo jSFixedFastJsonJudgerInfo);

    JSFixedFastJsonSection sectionToJSFixedFastJson(Section section);

    @InheritInverseConfiguration
    Section sectionFromJSFixedFastJson(JSFixedFastJsonSection jSFixedFastJsonSection);

    WebInputDriverInfo driverInfoToWebInput(DriverInfo driverInfo);

    @InheritInverseConfiguration
    DriverInfo driverInfoFromWebInput(WebInputDriverInfo webInputDriverInfo);

    WebInputDriverSupport driverSupportToWebInput(DriverSupport driverSupport);

    @InheritInverseConfiguration
    DriverSupport driverSupportFromWebInput(WebInputDriverSupport webInputDriverSupport);

    WebInputJudgerInfo judgerInfoToWebInput(JudgerInfo judgerInfo);

    @InheritInverseConfiguration
    JudgerInfo judgerInfoFromWebInput(WebInputJudgerInfo webInputJudgerInfo);

    WebInputJudgerSupport judgerSupportToWebInput(JudgerSupport judgerSupport);

    @InheritInverseConfiguration
    JudgerSupport judgerSupportFromWebInput(WebInputJudgerSupport webInputJudgerSupport);

    WebInputSection sectionToWebInput(Section section);

    @InheritInverseConfiguration
    Section sectionFromWebInput(WebInputSection webInputSection);

    // -----------------------------------------------------------Judge DTO-----------------------------------------------------------
    FastJsonJudgerReport judgerReportToFastJson(JudgerReport judgerReport);

    @InheritInverseConfiguration
    JudgerReport judgerReportFromFastJson(FastJsonJudgerReport fastJsonJudgerReport);

    FastJsonSectionReport sectionReportToFastJson(SectionReport sectionReport);

    @InheritInverseConfiguration
    SectionReport sectionReportFromFastJson(FastJsonSectionReport fastJsonSectionReport);

    JSFixedFastJsonJudgerReport judgerReportToJSFixedFastJson(JudgerReport judgerReport);

    @InheritInverseConfiguration
    JudgerReport judgerReportFromJSFixedFastJson(JSFixedFastJsonJudgerReport jSFixedFastJsonJudgerReport);

    JSFixedFastJsonSectionReport sectionReportToJSFixedFastJson(SectionReport sectionReport);

    @InheritInverseConfiguration
    SectionReport sectionReportFromJSFixedFastJson(JSFixedFastJsonSectionReport jSFixedFastJsonSectionReport);
}

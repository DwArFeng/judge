package com.dwarfeng.judge.impl.bean;

import com.dwarfeng.judge.impl.bean.entity.*;
import com.dwarfeng.judge.impl.bean.key.HibernateAnalyserVariableKey;
import com.dwarfeng.judge.impl.bean.key.HibernateAnalysisKey;
import com.dwarfeng.judge.impl.bean.key.HibernateJudgementKey;
import com.dwarfeng.judge.impl.bean.key.HibernateJudgerVariableKey;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.judge.stack.bean.key.JudgementKey;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Bean 映射器。
 *
 * <p>
 * 该映射器中包含了 <code>impl</code> 模块中所有实体与 <code>stack</code> 模块中对应实体的映射方法。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
@Mapper
public interface BeanMapper {

    // -----------------------------------------------------------Subgrade Key-----------------------------------------------------------
    HibernateLongIdKey longIdKeyToHibernate(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromHibernate(HibernateLongIdKey hibernateLongIdKey);

    HibernateStringIdKey stringIdKeyToHibernate(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromHibernate(HibernateStringIdKey hibernateStringIdKey);

    // -----------------------------------------------------------Judge Key-----------------------------------------------------------
    HibernateAnalyserVariableKey analyserVariableKeyToHibernate(AnalyserVariableKey analyserVariableKey);

    @InheritInverseConfiguration
    AnalyserVariableKey analyserVariableKeyFromHibernate(HibernateAnalyserVariableKey hibernateAnalyserVariableKey);

    HibernateAnalysisKey analysisKeyToHibernate(AnalysisKey analysisKey);

    @InheritInverseConfiguration
    AnalysisKey analysisKeyFromHibernate(HibernateAnalysisKey hibernateAnalysisKey);

    HibernateJudgerVariableKey judgerVariableKeyToHibernate(JudgerVariableKey judgerVariableKey);

    @InheritInverseConfiguration
    JudgerVariableKey judgerVariableKeyFromHibernate(HibernateJudgerVariableKey hibernateJudgerVariableKey);

    HibernateJudgementKey judgementKeyToHibernate(JudgementKey judgementKey);

    @InheritInverseConfiguration
    JudgementKey judgementKeyFromHibernate(HibernateJudgementKey hibernateJudgementKey);

    // -----------------------------------------------------------Judge Entity-----------------------------------------------------------
    @Mapping(target = "sectionLongId", ignore = true)
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    @Mapping(target = "analyserVariables", ignore = true)
    HibernateAnalyserInfo analyserInfoToHibernate(AnalyserInfo analyserInfo);

    @InheritInverseConfiguration
    AnalyserInfo analyserInfoFromHibernate(HibernateAnalyserInfo hibernateAnalyserInfo);

    @Mapping(target = "stringId", ignore = true)
    HibernateAnalyserSupport analyserSupportToHibernate(AnalyserSupport analyserSupport);

    @InheritInverseConfiguration
    AnalyserSupport analyserSupportFromHibernate(HibernateAnalyserSupport hibernateAnalyserSupport);

    @Mapping(target = "variableStringId", ignore = true)
    @Mapping(target = "analyserInfoLongId", ignore = true)
    @Mapping(target = "analyserInfo", ignore = true)
    HibernateAnalyserVariable analyserVariableToHibernate(AnalyserVariable analyserVariable);

    @InheritInverseConfiguration
    AnalyserVariable analyserVariableFromHibernate(HibernateAnalyserVariable hibernateAnalyserVariable);

    @Mapping(target = "taskEvents", ignore = true)
    @Mapping(target = "sectionLongId", ignore = true)
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "judgements", ignore = true)
    @Mapping(target = "analyses", ignore = true)
    HibernateTask taskToHibernate(Task task);

    @InheritInverseConfiguration
    Task taskFromHibernate(HibernateTask hibernateTask);

    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "taskLongId", ignore = true)
    @Mapping(target = "task", ignore = true)
    HibernateTaskEvent taskEventToHibernate(TaskEvent taskEvent);

    @InheritInverseConfiguration
    TaskEvent taskEventFromHibernate(HibernateTaskEvent hibernateTaskEvent);

    @Mapping(target = "dataStringId", ignore = true)
    @Mapping(target = "taskLongId", ignore = true)
    @Mapping(target = "task", ignore = true)
    HibernateAnalysis analysisToHibernate(Analysis analysis);

    @InheritInverseConfiguration
    Analysis analysisFromHibernate(HibernateAnalysis hibernateAnalysis);

    @Mapping(target = "sectionLongId", ignore = true)
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    HibernateDriverInfo driverInfoToHibernate(DriverInfo driverInfo);

    @InheritInverseConfiguration
    DriverInfo driverInfoFromHibernate(HibernateDriverInfo hibernateDriverInfo);

    @Mapping(target = "stringId", ignore = true)
    HibernateDriverSupport driverSupportToHibernate(DriverSupport driverSupport);

    @InheritInverseConfiguration
    DriverSupport driverSupportFromHibernate(HibernateDriverSupport hibernateDriverSupport);

    @Mapping(target = "sectionLongId", ignore = true)
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "judgerVariables", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    HibernateJudgerInfo judgerInfoToHibernate(JudgerInfo judgerInfo);

    @InheritInverseConfiguration
    JudgerInfo judgerInfoFromHibernate(HibernateJudgerInfo hibernateJudgerInfo);

    @Mapping(target = "stringId", ignore = true)
    HibernateJudgerSupport judgerSupportToHibernate(JudgerSupport judgerSupport);

    @InheritInverseConfiguration
    JudgerSupport judgerSupportFromHibernate(HibernateJudgerSupport hibernateJudgerSupport);

    @Mapping(target = "variableStringId", ignore = true)
    @Mapping(target = "judgerInfoLongId", ignore = true)
    @Mapping(target = "judgerInfo", ignore = true)
    HibernateJudgerVariable judgerVariableToHibernate(JudgerVariable judgerVariable);

    @InheritInverseConfiguration
    JudgerVariable judgerVariableFromHibernate(HibernateJudgerVariable hibernateJudgerVariable);

    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "judgerInfos", ignore = true)
    @Mapping(target = "driverInfos", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    @Mapping(target = "analyserInfos", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    HibernateSection sectionToHibernate(Section section);

    @InheritInverseConfiguration
    Section sectionFromHibernate(HibernateSection hibernateSection);

    @Mapping(target = "longId", ignore = true)
    HibernateAnalysisFileInfo analysisFileInfoToHibernate(AnalysisFileInfo analysisFileInfo);

    @InheritInverseConfiguration
    AnalysisFileInfo analysisFileInfoFromHibernate(HibernateAnalysisFileInfo hibernateAnalysisFileInfo);

    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "items", ignore = true)
    HibernateAnalysisFilePack analysisFilePackToHibernate(AnalysisFilePack analysisFilePack);

    @InheritInverseConfiguration
    AnalysisFilePack analysisFilePackFromHibernate(HibernateAnalysisFilePack hibernateAnalysisFilePack);

    @Mapping(target = "packLongId", ignore = true)
    @Mapping(target = "pack", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateAnalysisFilePackItemInfo analysisFilePackItemInfoToHibernate(
            AnalysisFilePackItemInfo analysisFilePackItemInfo
    );

    @InheritInverseConfiguration
    AnalysisFilePackItemInfo analysisFilePackItemInfoFromHibernate(
            HibernateAnalysisFilePackItemInfo hibernateAnalysisFilePackItemInfo
    );

    @Mapping(target = "longId", ignore = true)
    HibernateAnalysisPictureInfo analysisPictureInfoToHibernate(AnalysisPictureInfo analysisPictureInfo);

    @InheritInverseConfiguration
    AnalysisPictureInfo analysisPictureInfoFromHibernate(HibernateAnalysisPictureInfo hibernateAnalysisPictureInfo);

    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "items", ignore = true)
    HibernateAnalysisPicturePack analysisPicturePackToHibernate(AnalysisPicturePack analysisPicturePack);

    @InheritInverseConfiguration
    AnalysisPicturePack analysisPicturePackFromHibernate(HibernateAnalysisPicturePack hibernateAnalysisPicturePack);

    @Mapping(target = "packLongId", ignore = true)
    @Mapping(target = "pack", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateAnalysisPicturePackItemInfo analysisPicturePackItemInfoToHibernate(
            AnalysisPicturePackItemInfo analysisPicturePackItemInfo
    );

    @InheritInverseConfiguration
    AnalysisPicturePackItemInfo analysisPicturePackItemInfoFromHibernate(
            HibernateAnalysisPicturePackItemInfo hibernateAnalysisPicturePackItemInfo
    );

    @Mapping(target = "dataStringId", ignore = true)
    @Mapping(target = "taskLongId", ignore = true)
    @Mapping(target = "task", ignore = true)
    HibernateJudgement judgementToHibernate(Judgement judgement);

    @InheritInverseConfiguration
    Judgement judgementFromHibernate(HibernateJudgement hibernateJudgement);
}

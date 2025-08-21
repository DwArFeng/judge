package com.dwarfeng.judge.impl.bean;

import com.dwarfeng.judge.impl.bean.entity.*;
import com.dwarfeng.judge.impl.bean.key.HibernateVariableKey;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.VariableKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Hibernate Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.6.0
 */
@Mapper
public interface HibernateMapper {

    HibernateLongIdKey longIdKeyToHibernate(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromHibernate(HibernateLongIdKey hibernateLongIdKey);

    HibernateStringIdKey stringIdKeyToHibernate(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromHibernate(HibernateStringIdKey hibernateStringIdKey);

    HibernateVariableKey variableKeyToHibernate(VariableKey variableKey);

    @InheritInverseConfiguration
    VariableKey variableKeyFromHibernate(HibernateVariableKey hibernateVariableKey);

    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    @Mapping(target = "sectionId", ignore = true)
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateDriverInfo driverInfoToHibernate(DriverInfo driverInfo);

    @InheritInverseConfiguration
    DriverInfo driverInfoFromHibernate(HibernateDriverInfo hibernateDriverInfo);

    @Mapping(target = "stringId", ignore = true)
    HibernateDriverSupport driverSupportToHibernate(DriverSupport driverSupport);

    @InheritInverseConfiguration
    DriverSupport driverSupportFromHibernate(HibernateDriverSupport hibernateDriverSupport);

    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    @Mapping(target = "sectionId", ignore = true)
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateJudgerInfo judgerInfoToHibernate(JudgerInfo judgerInfo);

    @InheritInverseConfiguration
    JudgerInfo judgerInfoFromHibernate(HibernateJudgerInfo hibernateJudgerInfo);

    @Mapping(target = "stringId", ignore = true)
    HibernateJudgerSupport judgerSupportToHibernate(JudgerSupport judgerSupport);

    @InheritInverseConfiguration
    JudgerSupport judgerSupportFromHibernate(HibernateJudgerSupport hibernateJudgerSupport);

    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "judgerInfos", ignore = true)
    @Mapping(target = "driverInfos", ignore = true)
    HibernateSection sectionToHibernate(Section section);

    @InheritInverseConfiguration
    Section sectionFromHibernate(HibernateSection hibernateSection);

    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateVariable variableToHibernate(Variable variable);

    @InheritInverseConfiguration
    Variable variableFromHibernate(HibernateVariable hibernateVariable);
}

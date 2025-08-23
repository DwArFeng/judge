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
 * Bean 映射器。
 *
 * <p>
 * 该映射器中包含了 <code>impl</code> 模块中所有实体与 <code>stack</code> 模块中对应实体的映射方法。
 *
 * @author DwArFeng
 * @since 1.7.0
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
    HibernateVariableKey variableKeyToHibernate(VariableKey variableKey);

    @InheritInverseConfiguration
    VariableKey variableKeyFromHibernate(HibernateVariableKey hibernateVariableKey);

    // -----------------------------------------------------------Judge Entity-----------------------------------------------------------
    @Mapping(target = "sectionId", ignore = true)
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

    @Mapping(target = "sectionId", ignore = true)
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    HibernateJudgerInfo judgerInfoToHibernate(JudgerInfo judgerInfo);

    @InheritInverseConfiguration
    JudgerInfo judgerInfoFromHibernate(HibernateJudgerInfo hibernateJudgerInfo);

    @Mapping(target = "stringId", ignore = true)
    HibernateJudgerSupport judgerSupportToHibernate(JudgerSupport judgerSupport);

    @InheritInverseConfiguration
    JudgerSupport judgerSupportFromHibernate(HibernateJudgerSupport hibernateJudgerSupport);

    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "judgerInfos", ignore = true)
    @Mapping(target = "driverInfos", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    HibernateSection sectionToHibernate(Section section);

    @InheritInverseConfiguration
    Section sectionFromHibernate(HibernateSection hibernateSection);

    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateVariable variableToHibernate(Variable variable);

    @InheritInverseConfiguration
    Variable variableFromHibernate(HibernateVariable hibernateVariable);
}

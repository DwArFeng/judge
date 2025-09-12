package com.dwarfeng.judge.sdk.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分析结果文件包插入或更新类型条目。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnalysisFilePackUpsertTypeItem {
}

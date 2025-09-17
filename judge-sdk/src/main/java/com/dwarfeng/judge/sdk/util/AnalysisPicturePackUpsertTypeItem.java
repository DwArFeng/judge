package com.dwarfeng.judge.sdk.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分析结果图片包插入或更新类型条目。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnalysisPicturePackUpsertTypeItem {
}

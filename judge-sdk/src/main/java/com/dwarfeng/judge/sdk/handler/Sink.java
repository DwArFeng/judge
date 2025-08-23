package com.dwarfeng.judge.sdk.handler;

import com.dwarfeng.judge.stack.bean.dto.SectionReport;
import com.dwarfeng.judge.stack.exception.SinkException;

/**
 * 水槽
 *
 * @author DwArFeng
 * @since 1.7.0
 */
public interface Sink {

    /**
     * 返回水槽是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 水槽是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 下沉部件报告。
     *
     * @param sectionReport 部件报告。
     * @throws SinkException 水槽异常。
     */
    void sinkData(SectionReport sectionReport) throws SinkException;
}

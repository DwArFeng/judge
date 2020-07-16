package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.SectionReport;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 水槽处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface SinkHandler extends Handler {

    /**
     * 下沉部件报告。
     *
     * @param sectionReport 指定的部件报告。
     * @throws HandlerException 处理器异常。
     */
    void sinkData(SectionReport sectionReport) throws HandlerException;
}

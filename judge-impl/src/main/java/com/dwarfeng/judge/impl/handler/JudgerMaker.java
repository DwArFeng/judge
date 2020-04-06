package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.judge.stack.handler.Judger;

/**
 * 判断器构造器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface JudgerMaker {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 根据指定的判断器信息生成一个判断器对象。
     * <p>可以保证传入的判断器信息中的类型是支持的。</p>
     *
     * @param judgerInfo 指定的判断器信息。
     * @return 制造出的判断器。
     * @throws JudgerException 判断器异常。
     */
    Judger makeJudger(JudgerInfo judgerInfo) throws JudgerException;
}

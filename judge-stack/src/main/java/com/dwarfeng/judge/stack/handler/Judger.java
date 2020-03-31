package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.JudgedValue;
import com.dwarfeng.judge.stack.exception.JudgerException;

/**
 * 判断器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface Judger {

    /**
     * 对仓库处理器中的数据做出判断，并生成判断值。
     *
     * <pre>数据信息有如下要求
     * 1. 主键是对应的判断器信息的主键。
     * 2. 发生时间是该数据的生成时间。
     * 3. 数据值是介于0.0-1.0之间的浮点数。
     * </pre>
     *
     * @param repositoryHandler 指定的仓库处理器。
     * @return 判断值。
     * @throws JudgerException 判断异常。
     */
    JudgedValue judge(RepositoryHandler repositoryHandler) throws JudgerException;
}

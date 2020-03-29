package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.exception.JudgerException;

/**
 * 判断器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface Judger {

    /**
     * 对仓库处理器中的数据做出判断。
     *
     * @param repositoryHandler 指定的仓库处理器。
     * @return 介于0.0-1.0之间的浮点数。
     * @throws JudgerException 判断异常。
     */
    double judge(RepositoryHandler repositoryHandler) throws JudgerException;
}

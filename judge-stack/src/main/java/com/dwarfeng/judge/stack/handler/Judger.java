package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.JudgerResult;
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
     * @param repositoryHandler 指定的仓库处理器。
     * @return 判断值。
     * @throws JudgerException 判断异常。
     */
    JudgerResult judge(RepositoryHandler repositoryHandler) throws JudgerException;
}

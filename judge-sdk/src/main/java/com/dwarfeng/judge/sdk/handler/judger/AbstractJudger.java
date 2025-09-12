package com.dwarfeng.judge.sdk.handler.judger;

import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.judge.stack.handler.Judger;

/**
 * 判断器的抽象实现。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public abstract class AbstractJudger implements Judger {

    @Override
    public Executor newExecutor() throws JudgerException {
        try {
            return doNewExecutor();
        } catch (JudgerException e) {
            throw e;
        } catch (Exception e) {
            throw new JudgerException(e);
        }
    }

    protected abstract Executor doNewExecutor() throws Exception;

    @Override
    public String toString() {
        return "AbstractJudger{}";
    }
}

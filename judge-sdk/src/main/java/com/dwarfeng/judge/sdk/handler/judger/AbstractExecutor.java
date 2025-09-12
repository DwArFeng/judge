package com.dwarfeng.judge.sdk.handler.judger;

import com.dwarfeng.judge.stack.handler.Judger.Context;
import com.dwarfeng.judge.stack.handler.Judger.Executor;

/**
 * 判断器执行器的抽象实现。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public abstract class AbstractExecutor implements Executor {

    protected Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "AbstractExecutor{" +
                "context=" + context +
                '}';
    }
}

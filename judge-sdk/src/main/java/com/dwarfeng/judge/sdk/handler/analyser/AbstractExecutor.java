package com.dwarfeng.judge.sdk.handler.analyser;

import com.dwarfeng.judge.stack.handler.Analyser.Context;
import com.dwarfeng.judge.stack.handler.Analyser.Executor;

/**
 * 分析器执行器的抽象实现。
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

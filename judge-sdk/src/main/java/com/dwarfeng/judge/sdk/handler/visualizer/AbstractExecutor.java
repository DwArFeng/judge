package com.dwarfeng.judge.sdk.handler.visualizer;

import com.dwarfeng.judge.stack.handler.Visualizer.Context;
import com.dwarfeng.judge.stack.handler.Visualizer.Executor;

/**
 * 可视化器执行器的抽象实现。
 *
 * @author DwArFeng
 * @since 2.2.0
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

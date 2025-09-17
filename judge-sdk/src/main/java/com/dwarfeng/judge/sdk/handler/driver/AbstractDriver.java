package com.dwarfeng.judge.sdk.handler.driver;

import com.dwarfeng.judge.stack.handler.Driver;

/**
 * 驱动器的抽象实现。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public abstract class AbstractDriver implements Driver {

    protected Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "AbstractDriver{" +
                "context=" + context +
                '}';
    }
}

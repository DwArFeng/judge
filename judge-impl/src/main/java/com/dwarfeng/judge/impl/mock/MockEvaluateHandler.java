package com.dwarfeng.judge.impl.mock;

import com.dwarfeng.judge.stack.handler.EvaluateHandler;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

public class MockEvaluateHandler implements EvaluateHandler {

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public void evaluate(LongIdKey sectionKey) {

    }
}

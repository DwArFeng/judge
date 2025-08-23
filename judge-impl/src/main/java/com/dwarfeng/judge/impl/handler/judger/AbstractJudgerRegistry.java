package com.dwarfeng.judge.impl.handler.judger;

/**
 * 抽象判断器注册。
 *
 * @author DwArFeng
 * @see com.dwarfeng.judge.sdk.handler.judger.AbstractJudgerRegistry
 * @since 1.3.0
 * @deprecated 该对象已经被废弃，请使用 sdk 模块下的对应对象代替。
 */
@Deprecated
public abstract class AbstractJudgerRegistry extends com.dwarfeng.judge.sdk.handler.judger.AbstractJudgerRegistry {

    public AbstractJudgerRegistry() {
    }

    public AbstractJudgerRegistry(String judgerType) {
        super(judgerType);
    }
}

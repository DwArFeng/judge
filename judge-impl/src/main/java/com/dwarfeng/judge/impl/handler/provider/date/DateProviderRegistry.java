package com.dwarfeng.judge.impl.handler.provider.date;

import com.dwarfeng.judge.sdk.handler.provider.AbstractProviderRegistry;
import com.dwarfeng.judge.stack.exception.ProviderException;
import com.dwarfeng.judge.stack.exception.ProviderMakeException;
import com.dwarfeng.judge.stack.handler.Provider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 日期提供器注册。
 *
 * <p>
 * 注册类型为 <code>date</code> 的提供器，使 Judge 可以通过固定预设生成时间点、时间范围和时间序列。
 * 调用方在 <code>ProviderSession.LookupInfo</code> 中指定预设（<code>preset</code> 字段），
 * 并通过固定长度的 <code>objs</code> 传入参数；会话实现见 {@link DateProviderSession}。
 *
 * <p>
 * 本提供器不支持日期字符串解析与格式化，所有输出统一为毫秒时间戳 <code>Long</code>。
 * 可默认参数使用字符串 <code>"-"</code> 占位，时区默认 <code>Asia/Shanghai</code>，
 * 基准时间默认当前系统时间。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component
public class DateProviderRegistry extends AbstractProviderRegistry {

    public static final String PROVIDER_TYPE = "date";

    private final ApplicationContext ctx;

    public DateProviderRegistry(ApplicationContext ctx) {
        super(PROVIDER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "日期提供器";
    }

    @Override
    public String provideDescription() {
        return "基于预设提供时间点、时间范围和时间序列。";
    }

    @Override
    public String provideExampleParam() {
        return "";
    }

    @Override
    public Provider makeProvider(String type, String param) throws ProviderException {
        try {
            return ctx.getBean(DateProvider.class, ctx);
        } catch (Exception e) {
            throw new ProviderMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "DateProviderRegistry{" +
                "ctx=" + ctx +
                ", providerType='" + providerType + '\'' +
                '}';
    }
}

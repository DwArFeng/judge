package com.dwarfeng.judge.impl.handler.sink;

import com.dwarfeng.judge.impl.handler.Sink;
import com.dwarfeng.judge.stack.bean.dto.JudgedValue;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * 同时将消息推送给所有代理的多重水槽。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class MultiSink implements Sink {

    public static final String SUPPORT_TYPE = "multi";
    private static final Logger LOGGER = LoggerFactory.getLogger(MultiSink.class);

    @Autowired
    private List<Sink> sinks;

    @Value("${sink.multi.delegate_types}")
    private String delegetTypes;

    private final List<Sink> delegates = new ArrayList<>();

    @PostConstruct
    public void init() throws HandlerException {
        StringTokenizer st = new StringTokenizer(delegetTypes, ",");
        while (st.hasMoreTokens()) {
            String delegateType = st.nextToken();
            delegates.add(sinks.stream().filter(p -> p.supportType(delegateType)).findAny()
                    .orElseThrow(() -> new HandlerException("未知的 sink 类型: " + delegateType)));
        }
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public void sinkData(JudgedValue judgedValue) {
        for (Sink delegate : delegates) {
            try {
                delegate.sinkData(judgedValue);
            } catch (Exception e) {
                LOGGER.warn("代理水槽推送数据失败，异常信息如下: ", e);
            }
        }
    }
}

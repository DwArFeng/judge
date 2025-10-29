package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.handler.FilterMaker;
import com.dwarfeng.judge.stack.exception.FilterException;
import com.dwarfeng.judge.stack.exception.UnsupportedFilterTypeException;
import com.dwarfeng.judge.stack.handler.Filter;
import com.dwarfeng.judge.stack.handler.FilterHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class FilterHandlerImpl implements FilterHandler {

    private final List<FilterMaker> filterMakers;

    public FilterHandlerImpl(List<FilterMaker> filterMakers) {
        this.filterMakers = Optional.ofNullable(filterMakers).orElse(Collections.emptyList());
    }

    @Override
    @BehaviorAnalyse
    public Filter make(String type, String param) throws HandlerException {
        try {
            // 生成过滤器。
            FilterMaker filterMaker = filterMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedFilterTypeException(type));
            return filterMaker.makeFilter(type, param);
        } catch (FilterException e) {
            throw e;
        } catch (Exception e) {
            throw new FilterException(e);
        }
    }
}

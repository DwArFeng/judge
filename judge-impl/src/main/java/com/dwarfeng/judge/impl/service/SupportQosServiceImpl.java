package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.handler.SupportHandler;
import com.dwarfeng.judge.stack.service.SupportQosService;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import org.springframework.stereotype.Service;

@Service
public class SupportQosServiceImpl implements SupportQosService {

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final SupportHandler supportHandler;

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final ServiceExceptionMapper sem;

    public SupportQosServiceImpl(SupportHandler supportHandler, ServiceExceptionMapper sem) {
        this.supportHandler = supportHandler;
        this.sem = sem;
    }
}

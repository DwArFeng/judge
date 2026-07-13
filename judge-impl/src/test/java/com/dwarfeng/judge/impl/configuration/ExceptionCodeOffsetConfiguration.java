package com.dwarfeng.judge.impl.configuration;

import com.dwarfeng.judge.sdk.util.ServiceExceptionCodes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ExceptionCodeOffsetConfiguration {

    @Value("${com.dwarfeng.judge.judge.exception_code_offset}")
    private int exceptionCodeOffset;
    @Value("${com.dwarfeng.judge.judge.exception_code_offset.subgrade}")
    private int subgradeExceptionCodeOffset;
    @Value("${com.dwarfeng.judge.judge.exception_code_offset.spring_telqos}")
    private int springTelqosExceptionCodeOffset;
    @Value("${com.dwarfeng.judge.judge.exception_code_offset.spring_terminator}")
    private int springTerminatorExceptionCodeOffset;
    @Value("${com.dwarfeng.judge.judge.exception_code_offset.dwarfeng_datamark}")
    private int dwarfengDatamarkExceptionCodeOffset;
    @Value("${com.dwarfeng.judge.judge.exception_code_offset.dwarfeng_ftp}")
    private int dwarfengFtpExceptionCodeOffset;
    @Value("${com.dwarfeng.judge.judge.exception_code_offset.dcti}")
    private int dctiExceptionCodeOffset;

    @PostConstruct
    public void init() {
        ServiceExceptionCodes.setExceptionCodeOffset(exceptionCodeOffset);
        com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.setExceptionCodeOffset(
                subgradeExceptionCodeOffset
        );
        com.dwarfeng.springtelqos.sdk.util.ServiceExceptionCodes.setExceptionCodeOffset(
                springTelqosExceptionCodeOffset
        );
        com.dwarfeng.springterminator.sdk.util.ServiceExceptionCodes.setExceptionCodeOffset(
                springTerminatorExceptionCodeOffset
        );
        com.dwarfeng.datamark.sdk.util.ServiceExceptionCodes.setExceptionCodeOffset(
                dwarfengDatamarkExceptionCodeOffset
        );
        com.dwarfeng.ftp.sdk.util.ServiceExceptionCodes.setExceptionCodeOffset(
                dwarfengFtpExceptionCodeOffset
        );
        com.dwarfeng.dcti.sdk.util.ServiceExceptionCodes.setExceptionCodeOffset(
                dctiExceptionCodeOffset
        );
    }
}

package com.dwarfeng.judge.node.configuration;

import com.dwarfeng.judge.sdk.util.ServiceExceptionCodes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ExceptionCodeOffsetConfiguration {

    @Value("${judge.exception_code_offset}")
    private int exceptionCodeOffset;
    @Value("${judge.exception_code_offset.subgrade}")
    private int subgradeExceptionCodeOffset;
    @Value("${judge.exception_code_offset.snowflake}")
    private int snowflakeExceptionCodeOffset;
    @Value("${judge.exception_code_offset.dwarfeng_datamark}")
    private int dwarfengDatamarkExceptionCodeOffset;
    @Value("${judge.exception_code_offset.dwarfeng_ftp}")
    private int dwarfengFtpExceptionCodeOffset;

    @PostConstruct
    public void init() {
        ServiceExceptionCodes.setExceptionCodeOffset(exceptionCodeOffset);
        com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.setExceptionCodeOffset(subgradeExceptionCodeOffset);
        com.dwarfeng.sfds.sdk.util.ServiceExceptionCodes.setExceptionCodeOffset(snowflakeExceptionCodeOffset);
        com.dwarfeng.datamark.util.ServiceExceptionCodes.setExceptionCodeOffset(dwarfengDatamarkExceptionCodeOffset);
        com.dwarfeng.ftp.util.ServiceExceptionCodes.setExceptionCodeOffset(dwarfengFtpExceptionCodeOffset);
    }
}

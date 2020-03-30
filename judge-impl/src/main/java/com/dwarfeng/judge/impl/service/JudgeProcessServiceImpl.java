package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.handler.JudgeAssignHandler;
import com.dwarfeng.judge.stack.service.JudgeProcessService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JudgeProcessServiceImpl implements JudgeProcessService {

    @Autowired
    private JudgeAssignHandler judgeAssignHandler;
    @Autowired
    private ServiceExceptionMapper sem;

    @Override
    public void judge(LongIdKey sectionKey) throws ServiceException {
        try {
            judgeAssignHandler.judge(sectionKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("判断部件时发生异常",
                    LogLevel.WARN, sem, e
            );
        }
    }
}

package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.TimedValue;
import com.dwarfeng.judge.stack.handler.RepositoryHandler;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Component
public class RepositoryHandlerImpl implements RepositoryHandler {

    @Autowired
    private List<Repository> repositories;

    @Value("${repository.type}")
    private String repositoryType;

    private Repository repository;

    @PostConstruct
    public void init() throws HandlerException {
        this.repository = repositories.stream().filter(p -> p.supportType(repositoryType)).findAny()
                .orElseThrow(() -> new HandlerException("未知的 pusher 类型: " + repositoryType));
    }

    @Override
    public TimedValue realtimeValue(LongIdKey pointKey) throws HandlerException {
        return repository.realtimeValue(pointKey);
    }

    @Override
    public TimedValue realtimeValue(
            LongIdKey pointKey,
            String processPreset, Object[] args) throws HandlerException {
        return repository.realtimeValue(pointKey, processPreset, args);
    }

    @Override
    public List<TimedValue> persistenceValue(
            LongIdKey pointKey, Date startDate, Date endDate) throws HandlerException {
        return repository.persistenceValue(pointKey, startDate, endDate);
    }

    @Override
    public List<TimedValue> persistenceValue(
            LongIdKey pointKey, Date startDate, Date endDate,
            String processPreset, Object[] args) throws HandlerException {
        return repository.persistenceValue(pointKey, startDate, endDate, processPreset, args);
    }
}

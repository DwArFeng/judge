package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.PersistenceValue;
import com.dwarfeng.judge.stack.exception.RepositoryException;
import com.dwarfeng.judge.stack.handler.RepositoryHandler;
import com.dwarfeng.subgrade.stack.bean.dto.PagedData;
import com.dwarfeng.subgrade.stack.bean.dto.PagingInfo;
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
    public String realtimeValue(LongIdKey pointKey) throws RepositoryException {
        return repository.realtimeValue(pointKey);
    }

    @Override
    public PagedData<PersistenceValue> persistenceValue(LongIdKey pointKey, Date startDate, Date endDate) throws RepositoryException {
        return repository.persistenceValue(pointKey, startDate, endDate);
    }

    @Override
    public PagedData<PersistenceValue> persistenceValue(LongIdKey pointKey, Date startDate, Date endDate, PagingInfo pagingInfo) throws RepositoryException {
        return repository.persistenceValue(pointKey, startDate, endDate, pagingInfo);
    }
}

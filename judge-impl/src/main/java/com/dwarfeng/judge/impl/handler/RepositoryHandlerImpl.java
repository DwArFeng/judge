package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.exception.RepositoryException;
import com.dwarfeng.judge.stack.handler.RepositoryHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class RepositoryHandlerImpl implements RepositoryHandler {

    @SuppressWarnings("FieldMayBeFinal")
    @Autowired(required = false)
    private List<Repository> repositories = new ArrayList<>();

    @Value("${repository.type}")
    private String repositoryType;

    private Repository repository;

    @PostConstruct
    public void init() throws HandlerException {
        this.repository = repositories.stream().filter(p -> p.supportType(repositoryType)).findAny()
                .orElseThrow(() -> new HandlerException("未知的 repository 类型: " + repositoryType));
    }

    @Override
    public Object getData(String category, Object... args) throws RepositoryException {
        return repository.getData(category, args);
    }
}

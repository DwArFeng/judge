package com.dwarfeng.judge.impl.handler.repository;

import com.dwarfeng.judge.impl.handler.Repository;

import java.util.Objects;

/**
 * 抽象仓库。
 *
 * <p>
 * 仓库对象的默认实现。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public abstract class AbstractRepository implements Repository {

    protected String repositoryType;

    public AbstractRepository() {
    }

    public AbstractRepository(String repositoryType) {
        this.repositoryType = repositoryType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(repositoryType, type);
    }

    public String getRepositoryType() {
        return repositoryType;
    }

    public void setRepositoryType(String repositoryType) {
        this.repositoryType = repositoryType;
    }

    @Override
    public String toString() {
        return "AbstractRepository{" +
                "repositoryType='" + repositoryType + '\'' +
                '}';
    }
}

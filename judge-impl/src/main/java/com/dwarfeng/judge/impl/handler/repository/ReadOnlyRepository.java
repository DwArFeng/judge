package com.dwarfeng.judge.impl.handler.repository;

import com.dwarfeng.judge.stack.exception.RepositoryException;

/**
 * 只读仓库。
 *
 * <p>
 * 抽象仓库的只读实现。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public abstract class ReadOnlyRepository extends AbstractRepository {

    public ReadOnlyRepository() {
    }

    public ReadOnlyRepository(String repositoryType) {
        super(repositoryType);
    }

    @Override
    public void putData(String category, Object[] args) throws RepositoryException {
        throw new RepositoryException("该仓库是只读仓库");
    }

    @Override
    public String toString() {
        return "ReadOnlyRepository{" +
                "repositoryType='" + repositoryType + '\'' +
                '}';
    }
}

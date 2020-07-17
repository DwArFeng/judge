package com.dwarfeng.judge.stack.exception;

/**
 * 不支持的仓库数据分类异常。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class UnsupportedRepositoryCategoryException extends RepositoryException {

    private static final long serialVersionUID = 4035320694320450045L;

    private final String category;

    public UnsupportedRepositoryCategoryException(String category) {
        this.category = category;
    }

    @Override
    public String getMessage() {
        return "不支持的数据类型: " + category;
    }
}

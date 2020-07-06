package com.dwarfeng.judge.impl.handler.repository;

import com.dwarfeng.judge.impl.handler.Repository;
import com.dwarfeng.judge.stack.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 路径仓库。
 *
 * <p>
 * 根据路径值定位到多个仓库中取值。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class RoutedRepository implements Repository {

    public static final String SUPPORT_TYPE = "routed";

    @SuppressWarnings("FieldMayBeFinal")
    @Autowired(required = false)
    private List<Repository> repositories = new ArrayList<>();

    @Value("${repository.routed.supported_types}")
    private String routedTypes;
    @Value("${repository.routed.delimiter}")
    private String delimiter;

    private final Map<String, Repository> repositoryMap = new HashMap<>();

    @PostConstruct
    public void init() {
        StringTokenizer stringTokenizer = new StringTokenizer(routedTypes, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String repositoryType = stringTokenizer.nextToken();
            if (Objects.equals(SUPPORT_TYPE, repositoryType)) {
                throw new IllegalArgumentException(
                        "RoutedRepository 不能引用自身，请修改 repository.routed.supported_types 属性");
            }
            Repository repository = repositories.stream().filter(p -> p.supportType(repositoryType)).findAny()
                    .orElseThrow(() -> new IllegalArgumentException("未知的 repository 类型: " + repositoryType +
                            "，请修改 repository.routed.supported_types 属性"));
            repositoryMap.put(repositoryType, repository);
        }
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public Object getData(String category, Object... args) throws RepositoryException {
        try {
            String[] split = category.split(delimiter, 2);
            if (!repositoryMap.containsKey(split[0])) {
                throw new RepositoryException("未知的 repository 类型: " + split[0]);
            }
            return repositoryMap.get(split[0]).getData(split[1], args);
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}

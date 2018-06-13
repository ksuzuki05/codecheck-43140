package codecheck.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * {@link RecipesRepository} の実装クラス。
 */
@Component
public class RecipesRepositoryImpl implements RecipesRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

}

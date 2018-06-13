package codecheck.dao;

import codecheck.dao.entity.RecipeEntity;
import codecheck.domain.dto.Recipe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * {@link RecipesRepository} の実装クラス。
 */
@Component
public class RecipesRepositoryImpl implements RecipesRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, Recipe> getAllRecipes() {
        List<RecipeEntity> recipeEntityList = jdbcTemplate.query(
                "SELECT * FROM recipes",
                new RowMapper<RecipeEntity>() {
                    public RecipeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new RecipeEntity(rs.getInt("id"),
                                rs.getString("title"),
                                rs.getString("making_time"),
                                rs.getString("serves"),
                                rs.getString("ingredients"),
                                rs.getInt("cost"),
                                rs.getTimestamp("created_at"),
                                rs.getTimestamp("updated_at"));
                    }
                });
        
        Map<Integer, Recipe> recipeMap = new HashMap<>();
        for (RecipeEntity entity : recipeEntityList) {
            recipeMap.put(entity.getId(), mapRecipeEntityToRecipe(entity));
        }
        
        return recipeMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Recipe getRecipeById(int id) {
        RecipeEntity recipeEntity;
        
        try {
            recipeEntity = jdbcTemplate.queryForObject(
                    "SELECT * FROM recipes WHERE id = ?",
                    new RowMapper<RecipeEntity>() {
                        public RecipeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return new RecipeEntity(rs.getInt("id"),
                                    rs.getString("title"),
                                    rs.getString("making_time"),
                                    rs.getString("serves"),
                                    rs.getString("ingredients"),
                                    rs.getInt("cost"),
                                    rs.getTimestamp("created_at"),
                                    rs.getTimestamp("updated_at"));
                        }
                    }, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        
        return mapRecipeEntityToRecipe(recipeEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean entryRecipe(Recipe recipe) {
        int result = jdbcTemplate.update(
                "INSERT INTO recipes (title, making_time, serves, ingredients, cost) "
              + "VALUES (?, ?, ?, ?, ?)",
                recipe.getTitle(),
                recipe.getMakingTime(),
                recipe.getServes(),
                recipe.getIngredients(),
                recipe.getCost());
        
        return result == 1;
    }
    
    private Recipe mapRecipeEntityToRecipe(RecipeEntity recipeEntity) {
        return new Recipe(recipeEntity.getTitle(),
                recipeEntity.getMakingTime(),
                recipeEntity.getServes(),
                recipeEntity.getIngredients(),
                recipeEntity.getCost());
    }
}

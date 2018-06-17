package codecheck.dao;

import codecheck.dao.entity.RecipeEntity;
import codecheck.domain.model.Recipe;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * {@link RecipesRepository} の実装クラス。
 */
@Repository
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
                new RecipeRowMapper());
        
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
    public Recipe getRecipeById(Integer id) {
        RecipeEntity recipeEntity;
        
        try {
            recipeEntity = jdbcTemplate.queryForObject(
                    "SELECT * FROM recipes WHERE id = ?",
                    new RecipeRowMapper(),
                    id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        
        return mapRecipeEntityToRecipe(recipeEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean createRecipe(Recipe recipe) {
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateRecipeById(Integer id, Recipe recipe) {
        int result = jdbcTemplate.update(
                "UPDATE recipes SET title = ?, "
                + "making_time = ?, "
                + "serves = ?, "
                + "ingredients = ?, "
                + "cost = ?, "
                + "updated_at = ? "
                + "WHERE id = ?",
                recipe.getTitle(),
                recipe.getMakingTime(),
                recipe.getServes(),
                recipe.getIngredients(),
                recipe.getCost(),
                new Date(),
                id);
        
        return result == 1;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteRecipeById(Integer id) {
        int result = jdbcTemplate.update("DELETE FROM recipes WHERE id = ?", id);
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

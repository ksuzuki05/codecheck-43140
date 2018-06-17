package codecheck.domain;

import codecheck.dao.RecipesRepository;
import codecheck.domain.model.Recipe;
import exception.DatabaseProcessFailureException;
import exception.InvalidRecipeException;
import exception.RecipeNotFoundException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link RecipesService} の実装クラス。
 */
@Service
public class RecipesServiceImpl implements RecipesService {

    @Autowired
    RecipesRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecipe(Recipe recipe) {
        if (!recipe.isValidRecipe()) {
            throw new InvalidRecipeException();
        }
        
        boolean result = repository.createRecipe(recipe);
        
        if (!result) {
            throw new DatabaseProcessFailureException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, Recipe> getAllRecipes() {
        return repository.getAllRecipes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Recipe getRecipeById(Integer id) {
        Recipe recipe = repository.getRecipeById(id);
        
        if (recipe == null) {
            throw new RecipeNotFoundException();
        }
        
        return recipe;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecipeById(Integer id, Recipe recipe) {
        boolean result = repository.updateRecipeById(id, recipe);
        
        if (!result) {
            throw new RecipeNotFoundException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteRecipeById(Integer id) {
        boolean result = repository.deleteRecipeById(id);
        
        if (!result) {
            throw new RecipeNotFoundException();
        }
    }
}

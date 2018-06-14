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
    RecipesRepository recipesRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean createRecipe(Recipe recipe) {
        if (!recipe.isValidRecipe()) {
            throw new InvalidRecipeException();
        }
        
        boolean result = recipesRepository.entryRecipe(recipe);
        
        if (!result) {
            throw new DatabaseProcessFailureException();
        }
        
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, Recipe> getAllRecipes() {
        return recipesRepository.getAllRecipes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Recipe getRecipeById(Integer id) {
        Recipe recipe = recipesRepository.getRecipeById(id);
        
        if (recipe == null) {
            throw new RecipeNotFoundException();
        }
        
        return recipe;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateRecipe(Integer id, Recipe recipe) {
        boolean result = recipesRepository.updateRecipe(id, recipe);
        
        if (!result) {
            throw new RecipeNotFoundException();
        }
        
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteRecipeById(Integer id) {
        boolean result = recipesRepository.deleteRecipeById(id);
        
        if (!result) {
            throw new RecipeNotFoundException();
        }
        
        return true;
    }
}

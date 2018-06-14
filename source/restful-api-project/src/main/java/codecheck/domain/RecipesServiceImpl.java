package codecheck.domain;

import codecheck.dao.RecipesRepository;
import codecheck.domain.model.Recipe;
import exception.DatabaseProcessFailureException;
import exception.InvalidRecipeException;
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
}

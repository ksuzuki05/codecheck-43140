package codecheck.domain;

import codecheck.dao.RecipesRepository;
import codecheck.domain.model.Recipe;
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
        return recipesRepository.entryRecipe(recipe);
    }

}

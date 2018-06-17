package codecheck.application;

import codecheck.application.payload.CreateRecipeResponse;
import codecheck.application.payload.RecipePayload;
import codecheck.application.payload.UpdateRecipeRequest;
import codecheck.domain.RecipesService;
import codecheck.domain.model.Recipe;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * レシピ削除 REST API を提供するクラス。
 */
@RestController
public class DeleteRecipeRestController {

    @Autowired
    RecipesService recipesService;

}

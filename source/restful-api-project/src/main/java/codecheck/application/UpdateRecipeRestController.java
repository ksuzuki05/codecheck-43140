package codecheck.application;

import codecheck.application.payload.GetAllRecipeResponse;
import codecheck.application.payload.GetRecipeByIdResponse;
import codecheck.application.payload.RecipePayload;
import codecheck.application.payload.RecipeWithIdPayload;
import codecheck.domain.RecipesService;
import codecheck.domain.model.Recipe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * レシピ更新 REST API を提供するクラス。
 */
@RestController
public class UpdateRecipeRestController {

    @Autowired
    RecipesService recipesService;

}

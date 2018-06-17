package codecheck.application;

import codecheck.application.payload.CreateRecipeErrorResponse;
import codecheck.application.payload.CreateRecipeRequest;
import codecheck.application.payload.CreateRecipeResponse;
import codecheck.application.payload.RecipePayload;
import codecheck.domain.RecipesService;
import codecheck.domain.model.Recipe;
import exception.InvalidRecipeException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * レシピ取得 REST API を提供するクラス。
 */
@RestController
public class GetRecipeRestController {

    @Autowired
    RecipesService recipesService;

}

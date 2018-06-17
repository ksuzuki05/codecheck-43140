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
 * レシピ作成 REST API を提供するクラス。
 */
@RestController
public class CreateRecipeRestController {

    @Autowired
    RecipesService recipesService;

    /**
     * レシピ作成 API を提供します。
     * 
     * <p>インターフェースされたレシピ作成要求のペイロードをもとにレシピを作成します。
     * 
     * @param request 作成要求ペイロード
     * @return レスポンスのペイロード
     */
    @RequestMapping(method = RequestMethod.POST, value = "recipes",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CreateRecipeResponse createRecipe(@RequestBody CreateRecipeRequest request) {
        boolean result = recipesService.createRecipe(mapRecipePayloadToRecipe(request));
        if (result) {
            List<RecipePayload> list = new ArrayList<>();
            list.add(request);
            return new CreateRecipeResponse("Recipe successfully created!", list);
        }
        return null;
    }
    
    /**
     * {@link InvalidRecipeException} が発生した際に
     * エラーメッセージを返却します。
     * 
     * @return エラーレスポンス
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ InvalidRecipeException.class })
    @ResponseBody
    public CreateRecipeErrorResponse handleCreateRecipeError() {
        String message = "Recipe creation failed!";
        String required = "title, making_time, serves, ingredients, cost";
        return new CreateRecipeErrorResponse(message, required);
    }

    private Recipe mapRecipePayloadToRecipe(RecipePayload payload) {
        return new Recipe(payload.getTitle(), payload.getMakingTime(), payload.getServes(),
                payload.getIngredients(), Integer.parseInt(payload.getCost()));
    }
}

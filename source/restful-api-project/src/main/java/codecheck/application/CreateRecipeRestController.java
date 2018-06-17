package codecheck.application;

import codecheck.application.payload.CreateRecipeErrorResponse;
import codecheck.application.payload.CreateRecipeRequest;
import codecheck.application.payload.CreateRecipeResponse;
import codecheck.application.payload.RecipePayload;
import codecheck.application.payload.SystemErrorResponse;
import codecheck.domain.RecipesService;
import codecheck.domain.model.Recipe;
import exception.InvalidPayloadException;
import exception.InvalidRecipeException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    @ResponseStatus(HttpStatus.OK)
    public CreateRecipeResponse createRecipe(@RequestBody CreateRecipeRequest request) {
        if (request == null) {
            throw new InvalidPayloadException();
        }
        
        boolean result = recipesService.createRecipe(mapRecipePayloadToRecipe(request));
        
        if (result) {
            List<RecipePayload> list = new ArrayList<>();
            list.add(request);
            return new CreateRecipeResponse("Recipe successfully created!", list);
        }
        return null;
    }
    
    /**
     * リクエスト不正で例外が発生した際のエラーメッセージを返却します。
     * 
     * @return エラーレスポンス
     */
    @ExceptionHandler({ InvalidRecipeException.class,
                        InvalidPayloadException.class,
                        HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CreateRecipeErrorResponse handleCreateRecipeError() {
        String message = "Recipe creation failed!";
        String required = "title, making_time, serves, ingredients, cost";
        return new CreateRecipeErrorResponse(message, required);
    }
    
    /**
     * 予期せぬ例外が発生した際のエラーメッセージを返却します。
     * 
     * @return エラーレスポンス
     */
    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public SystemErrorResponse handleAnyError(Exception e) {
        String message = "Recipe creation failed!";
        String detail = "something wrong";
        return new SystemErrorResponse(message, detail);
    }
    
    private Recipe mapRecipePayloadToRecipe(RecipePayload payload) {
        return new Recipe(payload.getTitle(),
                          payload.getMakingTime(),
                          payload.getServes(),
                          payload.getIngredients(),
                          Integer.parseInt(payload.getCost()));
    }
}

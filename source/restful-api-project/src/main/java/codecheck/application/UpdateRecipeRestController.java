package codecheck.application;

import static codecheck.common.Utils.mapRecipePayloadToRecipe;

import codecheck.application.payload.CreateRecipeResponse;
import codecheck.application.payload.RecipePayload;
import codecheck.application.payload.UpdateRecipeErrorResponse;
import codecheck.application.payload.UpdateRecipeRequest;
import codecheck.domain.RecipesService;
import exception.RecipeNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * レシピ更新 REST API を提供するクラス。
 */
@RestController
public class UpdateRecipeRestController {

    @Autowired
    RecipesService recipesService;
    
    /**
     * レシピ更新 API を提供します。
     * 
     * @param id 更新対象レシピの ID
     * @param request 更新要求ペイロード
     * @return レスポンスのペイロード
     */
    @RequestMapping(method = RequestMethod.PATCH, value = "recipes/{id}",
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CreateRecipeResponse createRecipe(@PathVariable String id,
                                             @RequestBody UpdateRecipeRequest request) {
        recipesService.updateRecipeById(Integer.parseInt(id), mapRecipePayloadToRecipe(request));
        
        List<RecipePayload> list = new ArrayList<>();
        list.add(request);
        return new CreateRecipeResponse("Recipe successfully updated!", list);
        
    }
    
    /**
     * {@link RecipeNotFoundException} が発生した際に
     * エラーメッセージを返却します。
     * 
     * @return エラーレスポンス
     */
    @ExceptionHandler({ RecipeNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public UpdateRecipeErrorResponse handleUpdateRecipeError() {
        String message = "No Recipe found";
        return new UpdateRecipeErrorResponse(message);
    }

}

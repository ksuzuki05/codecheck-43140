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
 * レシピ更新 REST API を提供するクラス。
 */
@RestController
public class UpdateRecipeRestController {

    @Autowired
    RecipesService recipesService;
    
    /**
     * レシピ更新 API を提供します。
     * 
     * <p>インターフェースされたレシピ作成要求のペイロードをもとにレシピを作成します。
     * 
     * @param request 更新要求ペイロード
     * @return レスポンスのペイロード
     */
    @RequestMapping(method = RequestMethod.PATCH, value = "recipes/{id}",
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CreateRecipeResponse createRecipe(@PathVariable String id,
                                             @RequestBody UpdateRecipeRequest request) {
        boolean result = recipesService.updateRecipe(Integer.parseInt(id),
                                                     mapRecipePayloadToRecipe(request));
        
        if (result) {
            List<RecipePayload> list = new ArrayList<>();
            list.add(request);
            return new CreateRecipeResponse("Recipe successfully updated!", list);
        }
        
        return null;
    }

    private Recipe mapRecipePayloadToRecipe(RecipePayload payload) {
        return new Recipe(payload.getTitle(), payload.getMakingTime(), payload.getServes(),
                payload.getIngredients(), Integer.parseInt(payload.getCost()));
    }
}

package codecheck.application;

import codecheck.application.payload.DeleteRecipeResponse;
import codecheck.domain.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * レシピ削除 API を提供します。
     * 
     * @param id 削除対象レシピの ID
     * @return レスポンスのペイロード
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "recipes/{id}",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeleteRecipeResponse createRecipe(@PathVariable String id) {
        boolean result = recipesService.deleteRecipeById(Integer.parseInt(id));
        
        if (result) {
            return new DeleteRecipeResponse("Recipe successfully removed!");
        }
        
        return null;
    }
}

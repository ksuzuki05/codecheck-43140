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
 * レシピ取得 REST API を提供するクラス。
 */
@RestController
public class GetRecipeRestController {

    @Autowired
    RecipesService recipesService;

    /**
     * レシピ取得（全件） API を提供します。
     * 
     * @return レスポンスのペイロード
     */
    @RequestMapping(method = RequestMethod.GET, value = "recipes",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GetAllRecipeResponse getAllRecipe() {
        GetAllRecipeResponse response = new GetAllRecipeResponse();
        List<RecipeWithIdPayload> list = new ArrayList<>();
        
        Map<Integer, Recipe> recipes = recipesService.getAllRecipes();
        
        Iterator<Integer> iterator = recipes.keySet().iterator();
        while (iterator.hasNext()) {
            Integer id = iterator.next();
            RecipeWithIdPayload payload = mapRecipeToRecipeWithIdPayload(id, recipes.get(id));
            
            list.add(payload);
        }
        response.setRecipes(list);
        
        return response;
        
    }
    
    /**
     * レシピ取得（１件） API を提供します。
     * 
     * @return レスポンスのペイロード
     */
    @RequestMapping(method = RequestMethod.GET, value = "recipes/{id}",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GetRecipeByIdResponse getRecipeById(@PathVariable String id) {
        GetRecipeByIdResponse response = new GetRecipeByIdResponse();

        Recipe recipe = recipesService.getRecipeById(Integer.parseInt(id));

        response.setMessage("Recipe details by id");
        List<RecipePayload> list = new ArrayList<>();
        list.add(mapRecipeToRecipePayload(recipe));
        
        response.setRecipe(list);
        
        return response;
    }
    
    private RecipePayload mapRecipeToRecipePayload(Recipe recipe) {
        return new RecipePayload(recipe.getTitle(),
                                 recipe.getMakingTime(),
                                 recipe.getServes(),
                                 recipe.getIngredients(),
                                 recipe.getCost().toString());
    }
    
    private RecipeWithIdPayload mapRecipeToRecipeWithIdPayload(Integer id, Recipe recipe) {
        RecipeWithIdPayload payload = new RecipeWithIdPayload(id);
        
        payload.setTitle(recipe.getTitle());
        payload.setMakingTime(recipe.getMakingTime());
        payload.setIngredients(recipe.getIngredients());
        payload.setServes(recipe.getServes());
        payload.setCost(recipe.getCost().toString());
        
        return payload;
    }
}

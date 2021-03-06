package codecheck.domain;

import codecheck.domain.model.Recipe;
import java.util.Map;

/**
 * レシピの作成・取得・更新・削除機能を提供するサービスのインタフェース。
 */
public interface RecipesService {

    /**
     * レシピを作成します。
     * 
     * @param recipe 作成するレシピ
     */
    public void createRecipe(Recipe recipe);

    /**
     * 全レシピを取得します。
     * 
     * @return レシピ ID をキー、レシピをバリューとするマップ
     */
    public Map<Integer, Recipe> getAllRecipes();

    /**
     * ID で指定したレシピを取得します。
     * 
     * @param id 取得するレシピの ID
     * @return レシピ
     */
    public Recipe getRecipeById(Integer id);

    /**
     * ID で指定したレシピを更新します。
     * 
     * @param id 更新するレシピの ID
     * @param recipe 更新後のレシピ
     */
    public void updateRecipeById(Integer id, Recipe recipe);

    /**
     * ID で指定したレシピを削除します。
     * 
     * @param id 削除するレシピの ID
     */
    public void deleteRecipeById(Integer id);

}

package codecheck.domain;

import java.util.Map;
import codecheck.domain.model.Recipe;

/**
 * レシピの作成・取得・更新・削除機能を提供するサービスのインタフェース。
 */
public interface RecipesService {

    /**
     * レシピを作成します。
     * 
     * @param recipe 作成するレシピ
     * @return 作成に成功した場合 true, 失敗した場合 false
     */
    public boolean createRecipe(Recipe recipe);

    /**
     * 全レシピを取得します。
     * 
     * @return レシピ ID をキー、レシピをバリューとするマップ
     */
    public Map<Integer, Recipe> getAllRecipes();

}
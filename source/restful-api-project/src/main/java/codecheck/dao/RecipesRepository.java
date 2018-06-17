package codecheck.dao;

import codecheck.domain.model.Recipe;
import java.util.Map;

/**
 * レシピを保持するデータベースへのアクセスを行うインタフェース。
 */
public interface RecipesRepository {

    /**
     * レシピテーブルに存在する全レシピ一覧を取得します。
     * 
     * @return レシピ ID をキー、レシピをバリューとする、全レシピを保持するマップ
     */
    Map<Integer, Recipe> getAllRecipes();
    
    /**
     * ID をキーとして、レシピテーブルからレシピを取得します。
     * 
     * <p>レシピテーブルに対応するレコードが存在しない場合、null を返却します。
     * 
     * @return ID で指定されたレシピ
     */
    Recipe getRecipeById(Integer id);
    
    /**
     * 新しいレシピをレシピテーブルに登録します。
     * 
     * @param recipe 登録するレシピ
     * @return 登録が成功した場合 true, 失敗した場合 false
     */
    boolean createRecipe(Recipe recipe);
    
    /**
     * ID で指定したレシピを更新します。
     * 
     * @param id 更新対象のレシピ ID
     * @param recipe 更新後のレシピ
     * @return 更新が成功した場合 true, 失敗した場合 false
     */
    boolean updateRecipeById(Integer id, Recipe recipe);
    
    /**
     * ID で指定したレシピをレシピテーブルから削除します。
     * 
     * @param id 削除対象のレシピ ID
     * @return 削除が成功した場合 true, 失敗した場合 false
     */
    boolean deleteRecipeById(Integer id);
}

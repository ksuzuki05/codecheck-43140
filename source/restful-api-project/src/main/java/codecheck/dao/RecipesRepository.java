package codecheck.dao;

import codecheck.domain.dto.Recipe;
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
    Recipe getRecipeById(int id);
    
    /**
     * レシピをレシピテーブルに登録します。
     * 
     * @param recipe 登録するレシピ
     * @return 登録が成功した場合 true, 失敗した場合 false
     */
    boolean entryRecipe(Recipe recipe);
}

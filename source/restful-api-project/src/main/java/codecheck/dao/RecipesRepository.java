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
    
}

package codecheck.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピを表すドメインモデル。
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Recipe {

    private String title;
    private String makingTime;
    private String serves;
    private String ingredients;
    private Integer cost;
    
    /**
     * 有効なレシピであるかどうかを判定します。
     * 
     * <p>以下の必須項目がすべて null でない場合のみ、true を返却します。
     * <ul>
     * <li>title
     * <li>makingTime
     * <li>serves
     * <li>ingredients
     * <li>cost
     * </ul>
     * @return
     */
    public boolean isValidRecipe() {
        return title != null 
               && makingTime != null
               && serves != null
               && ingredients != null
               && cost != null;
    }

}
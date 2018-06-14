package codecheck.dao.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピテーブルの１レコードに対応するエンティティクラス。
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipeEntity {

    private Integer id;
    private String title;
    private String makingTime;
    private String serves;
    private String ingredients;
    private Integer cost;
    private Date createdAt;
    private Date updatedAt;
    
}

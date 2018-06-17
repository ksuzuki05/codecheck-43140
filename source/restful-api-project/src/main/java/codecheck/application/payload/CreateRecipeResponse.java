package codecheck.application.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピ作成 API レスポンスのペイロードを表現するクラス。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecipeResponse {
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("recipe")
    private List<RecipePayload> recipes;
    
}

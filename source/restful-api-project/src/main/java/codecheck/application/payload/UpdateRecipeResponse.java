package codecheck.application.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピ更新 API レスポンスのペイロードを表現するクラス。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecipeResponse {

    @JsonProperty("message")
    private String message;
    
    @JsonProperty("recipe")
    private List<RecipePayload> recipes;
}

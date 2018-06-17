package codecheck.application.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピ取得（全件） API レスポンスのペイロードを表現するクラス。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRecipeResponse {
    
    @JsonProperty("recipes")
    private List<RecipeWithIdPayload> recipes;
    
}

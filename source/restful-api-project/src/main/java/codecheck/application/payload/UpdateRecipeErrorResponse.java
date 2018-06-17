package codecheck.application.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピ更新 API エラーレスポンスペイロードを表現するクラス。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecipeErrorResponse {
    
    @JsonProperty("message")
    private String message;
    
}

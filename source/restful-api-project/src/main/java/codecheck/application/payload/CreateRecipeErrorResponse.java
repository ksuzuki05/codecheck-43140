package codecheck.application.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピ作成 API エラーレスポンスペイロードを表現するクラス。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecipeErrorResponse {
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("required")
    private String required;
    
}

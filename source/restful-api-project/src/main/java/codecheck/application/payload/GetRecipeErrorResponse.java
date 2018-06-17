package codecheck.application.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピ取得 API エラーレスポンスペイロードを表現するクラス。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRecipeErrorResponse {
    
    @JsonProperty("message")
    private String message;
    
}

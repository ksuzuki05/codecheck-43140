package codecheck.application.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピ削除 API レスポンスのペイロードを表現するクラス。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRecipeResponse {

    @JsonProperty("message")
    private String message;

}

package codecheck.application.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 予期せぬエラーが発生した際のレスポンスペイロードを表現するクラス。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemErrorResponse {
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("detail")
    private String detail;
}

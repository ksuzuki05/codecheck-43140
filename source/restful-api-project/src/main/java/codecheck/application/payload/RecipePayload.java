package codecheck.application.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピを表すペイロードを表現するクラス。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipePayload {
    
    @JsonProperty("title")
    private String title;
    
    @JsonProperty("making_time")
    private String makingTime;
    
    @JsonProperty("serves")
    private String serves;
    
    @JsonProperty("ingredients")
    private String ingredients;
    
    @JsonProperty("cost")
    private String cost;
    
}

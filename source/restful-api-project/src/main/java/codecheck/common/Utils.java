package codecheck.common;

import codecheck.application.payload.RecipePayload;
import codecheck.domain.model.Recipe;
import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * プロジェクトで共有のユーティリティクラス。
 */
public final class Utils {
    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);
    
    private Utils() {
        // Do nothing
    }
    
    /**
     * 文字列を日付に変換します。
     * 
     * <p>"yyyy-MM-dd HH:mm:ss" の形式で記述された文字列を、Date 型に変換します。<br>
     * 変換に失敗した場合、null を返却します。
     * 
     * @param dateString "yyyy-MM-dd HH:mm:ss" 形式の文字列
     * @return 変換後の日付
     */
    public static Date parseDate(String dateString) {
        Date result = null;
        
        try {
            result = DateUtils.parseDate(dateString, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            LOG.warn("parse exception is occurred.", e);
        }
        return result;
    }
    
    /**
     * レシピを表現するペイロード ({@link RecipePayload}) を
     * レシピのドメインモデル ({@link Recipe}) に変換します。
     * 
     * @param payload レシピを表現するペイロード
     * @return レシピのドメインモデル
     */
    public static Recipe mapRecipePayloadToRecipe(RecipePayload payload) {
        return new Recipe(payload.getTitle(),
                          payload.getMakingTime(),
                          payload.getServes(),
                          payload.getIngredients(),
                          Integer.parseInt(payload.getCost()));
    }
}

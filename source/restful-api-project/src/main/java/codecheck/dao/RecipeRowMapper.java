package codecheck.dao;

import codecheck.dao.entity.RecipeEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * レシピテーブルから取得したレコードを、レシピエンティティにマッピングするクラス。
 */
public class RecipeRowMapper implements RowMapper<RecipeEntity> {

    /**
     * レシピテーブルから取得したレコードを、レシピエンティティにマッピングします。
     * 
     * @return レシピエンティティ
     */
    @Override
    public RecipeEntity mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        return new RecipeEntity(new Integer(resultSet.getInt("id")),
                                resultSet.getString("title"),
                                resultSet.getString("making_time"),
                                resultSet.getString("serves"),
                                resultSet.getString("ingredients"),
                                new Integer(resultSet.getInt("cost")),
                                resultSet.getTimestamp("created_at"),
                                resultSet.getTimestamp("updated_at"));
    }
    
}

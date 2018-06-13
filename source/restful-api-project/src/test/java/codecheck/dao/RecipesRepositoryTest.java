package codecheck.dao;

import static codecheck.common.Utils.parseDate;
import static org.junit.Assert.*;
import java.util.Map;
import javax.sql.DataSource;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.operation.Operation;
import codecheck.domain.dto.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * {@link RecipesRepository} のテストクラス。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RecipesRepositoryTest {
    private static final Operation RESET_TABLE =
            Operations.sequenceOf(
                Operations.sql("DROP TABLE IF EXISTS recipes"),
                Operations.sql("CREATE TABLE IF NOT EXISTS recipes ("
                             + "id SERIAL PRIMARY KEY,"
                             + "title varchar(100) NOT NULL,"
                             + "making_time varchar(100) NOT NULL,"
                             + "serves varchar(100) NOT NULL,"
                             + "ingredients varchar(300) NOT NULL,"
                             + "cost integer NOT NULL,"
                             + "created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                             + "updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP"
                             + ")"),
                Operations.deleteAllFrom("recipes"));
    
    private static final Operation INSERT =
            Operations.insertInto("recipes").columns("id",
                                                     "title",
                                                     "making_time",
                                                     "serves",
                                                     "ingredients",
                                                     "cost",
                                                     "created_at",
                                                     "updated_at")
                    .values(1, "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000,
                            parseDate("2016-01-10 12:10:12"), parseDate("2016-01-10 12:10:12"))
                    .values(2, "オムライス", "30分", "2人", "玉ねぎ,卵,スパイス,醤油", 700,
                            parseDate("2016-01-11 13:10:12"), parseDate("2016-01-11 13:10:12"))
                    .build();

    @Autowired
    RecipesRepository recipesRepository;
    
    @Autowired
    DataSource dataSource;
    
    private void dbSetUp(Operation operation) {
        Destination destination = new DataSourceDestination(dataSource);
        DbSetup dbSetup = new DbSetup(destination, operation);
        dbSetup.launch();
    }

    @Test
    public void test_全レシピ一覧を取得できる() {
        dbSetUp(Operations.sequenceOf(RESET_TABLE, INSERT));
        
        Recipe expected1 = new Recipe("チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
        Recipe expected2 = new Recipe("オムライス", "30分", "2人", "玉ねぎ,卵,スパイス,醤油", 700);
        
        Map<Integer, Recipe> recipes = recipesRepository.getAllRecipes();
        
        assertEquals(2, recipes.size());
        assertEquals(expected1, recipes.get(0));
        assertEquals(expected2, recipes.get(1));
    }

}
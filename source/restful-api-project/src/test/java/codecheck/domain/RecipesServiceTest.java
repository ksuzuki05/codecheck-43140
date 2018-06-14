package codecheck.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.HashMap;
import java.util.Map;
import codecheck.dao.RecipesRepository;
import codecheck.domain.model.Recipe;
import exception.InvalidRecipeException;
import exception.DatabaseProcessFailureException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RecipesServiceTest {
    
    @MockBean
    private RecipesRepository recipesRepository;
    
    @Autowired
    private RecipesService recipesService;
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Test
    public void test_レシピを作成できる() {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        doReturn(true).when(recipesRepository).entryRecipe(recipe);
        
        boolean result = recipesService.createRecipe(recipe);
        
        assertTrue(result);
    }
    
    @Test
    public void test_titleがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe(null, "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        expectedException.expect(InvalidRecipeException.class);

        recipesService.createRecipe(recipe);
    }
    
    @Test
    public void test_makingTimeがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", null, "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        expectedException.expect(InvalidRecipeException.class);

        recipesService.createRecipe(recipe);
    }
    
    @Test
    public void test_servesがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", "15分", null, "玉ねぎ, トマト, スパイス, 水", 450);
        expectedException.expect(InvalidRecipeException.class);

        recipesService.createRecipe(recipe);
    }
    
    @Test
    public void test_ingredientsがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", null, 450);
        expectedException.expect(InvalidRecipeException.class);

        recipesService.createRecipe(recipe);
    }
    
    @Test
    public void test_costがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", null);
        expectedException.expect(InvalidRecipeException.class);

        recipesService.createRecipe(recipe);
    }
    
    @Test
    public void test_想定外の原因でDB登録に失敗した場合にDatabaseProcessFailureExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        expectedException.expect(DatabaseProcessFailureException.class);
        
        doReturn(false).when(recipesRepository).entryRecipe(recipe);
        
        recipesService.createRecipe(recipe);
    }
    
    @Test
    public void test_全レシピ一覧を取得できる() {
        Recipe recipe1 = new Recipe("チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
        Recipe recipe2 = new Recipe("オムライス", "30分", "2人", "玉ねぎ,卵,スパイス,醤油", 700);
        
        Map<Integer, Recipe> map = new HashMap<>();
        map.put(1, recipe1);
        map.put(2, recipe2);
        
        doReturn(map).when(recipesRepository).getAllRecipes();
        
        Map<Integer, Recipe> actual = recipesService.getAllRecipes();
        assertEquals(recipe1, actual.get(1));
        assertEquals(recipe2, actual.get(2));
    }
}

package codecheck.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import codecheck.dao.RecipesRepository;
import codecheck.domain.model.Recipe;
import exception.DatabaseProcessFailureException;
import exception.InvalidRecipeException;
import exception.RecipeNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RecipesServiceTest {
    
    @Mock
    private RecipesRepository repository;
    
    @InjectMocks
    private RecipesService service = new RecipesServiceImpl();
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void test_レシピを作成できる() {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        doReturn(true).when(repository).entryRecipe(recipe);
        
        boolean result = service.createRecipe(recipe);
        
        assertTrue(result);
    }
    
    @Test
    public void test_titleがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe(null, "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        expectedException.expect(InvalidRecipeException.class);

        service.createRecipe(recipe);
    }
    
    @Test
    public void test_makingTimeがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", null, "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        expectedException.expect(InvalidRecipeException.class);

        service.createRecipe(recipe);
    }
    
    @Test
    public void test_servesがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", "15分", null, "玉ねぎ, トマト, スパイス, 水", 450);
        expectedException.expect(InvalidRecipeException.class);

        service.createRecipe(recipe);
    }
    
    @Test
    public void test_ingredientsがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", null, 450);
        expectedException.expect(InvalidRecipeException.class);

        service.createRecipe(recipe);
    }
    
    @Test
    public void test_costがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", null);
        expectedException.expect(InvalidRecipeException.class);

        service.createRecipe(recipe);
    }
    
    @Test
    public void test_想定外の原因でDB登録に失敗した場合にDatabaseProcessFailureExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        expectedException.expect(DatabaseProcessFailureException.class);
        
        doReturn(false).when(repository).entryRecipe(recipe);
        
        service.createRecipe(recipe);
    }
    
    @Test
    public void test_全レシピ一覧を取得できる() {
        Recipe recipe1 = new Recipe("チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
        Recipe recipe2 = new Recipe("オムライス", "30分", "2人", "玉ねぎ,卵,スパイス,醤油", 700);
        
        Map<Integer, Recipe> map = new HashMap<>();
        map.put(1, recipe1);
        map.put(2, recipe2);
        
        doReturn(map).when(repository).getAllRecipes();
        
        Map<Integer, Recipe> actual = service.getAllRecipes();
        assertEquals(recipe1, actual.get(1));
        assertEquals(recipe2, actual.get(2));
    }
    
    @Test
    public void test_idで指定したレシピを取得できる() {
        Recipe recipe = new Recipe("チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
        
        doReturn(recipe).when(repository).getRecipeById(2);
        Recipe actual = service.getRecipeById(2);
        
        assertEquals(recipe, actual);
    }
    
    @Test
    public void test_idで指定したレシピが存在せず取得時にRecipeNotFoundExceptionが発生する() {
        expectedException.expect(RecipeNotFoundException.class);
        
        doReturn(null).when(repository).getRecipeById(2);

        service.getRecipeById(2);
    }
    
    @Test
    public void test_idで指定したレシピを更新できる() {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        
        doReturn(true).when(repository).updateRecipe(2, recipe);
        boolean result = service.updateRecipe(2, recipe);
        
        assertTrue(result);
    }

    @Test
    public void test_idで指定したレシピが存在せず更新時にRecipeNotFoundExceptionが発生する() {
        expectedException.expect(RecipeNotFoundException.class);
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        
        doReturn(false).when(repository).updateRecipe(2, recipe);

        service.updateRecipe(2, recipe);
    }
    
    @Test
    public void test_idで指定したレシピを削除できる() {
        doReturn(true).when(repository).deleteRecipeById(2);
        
        boolean result = service.deleteRecipeById(2);
        
        assertTrue(result);
    }
    
    @Test
    public void test_idで指定したレシピが存在せず削除時にRecipeNotFoundExceptionが発生する() {
        expectedException.expect(RecipeNotFoundException.class);
        
        doReturn(false).when(repository).deleteRecipeById(2);

        service.deleteRecipeById(2);
    }
}

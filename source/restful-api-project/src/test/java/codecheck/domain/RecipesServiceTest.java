package codecheck.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import codecheck.dao.RecipesRepository;
import codecheck.domain.model.Recipe;
import exception.InvalidRecipeException;
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
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", new Integer(450));
        doReturn(true).when(recipesRepository).entryRecipe(recipe);
        
        boolean result = recipesService.createRecipe(recipe);
        
        assertTrue(result);
    }
    
    @Test
    public void test_titleがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe(null, "15分", "5人", "玉ねぎ, トマト, スパイス, 水", new Integer(450));
        expectedException.expect(InvalidRecipeException.class);

        boolean result = recipesService.createRecipe(recipe);
    }
    
    @Test
    public void test_makingTimeがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", null, "5人", "玉ねぎ, トマト, スパイス, 水", new Integer(450));
        expectedException.expect(InvalidRecipeException.class);

        boolean result = recipesService.createRecipe(recipe);
    }
    
    @Test
    public void test_servesがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", "15分", null, "玉ねぎ, トマト, スパイス, 水", new Integer(450));
        expectedException.expect(InvalidRecipeException.class);

        boolean result = recipesService.createRecipe(recipe);
    }
    
    @Test
    public void test_ingredientsがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", null, new Integer(450));
        expectedException.expect(InvalidRecipeException.class);

        boolean result = recipesService.createRecipe(recipe);
    }
    
    @Test
    public void test_costがnullでレシピ作成時にInvalidRecipeExceptionが発生する() {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", null);
        expectedException.expect(InvalidRecipeException.class);

        boolean result = recipesService.createRecipe(recipe);
    }
}

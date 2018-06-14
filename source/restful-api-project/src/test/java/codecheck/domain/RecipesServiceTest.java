package codecheck.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import codecheck.dao.RecipesRepository;
import codecheck.domain.model.Recipe;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RecipesServiceTest {
    
    @MockBean
    private RecipesRepository recipesRepository;
    
    @Autowired
    private RecipesService recipesService;
    
    @Test
    public void test_レシピを作成できる() {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        doReturn(true).when(recipesRepository).entryRecipe(recipe);
        
        boolean result = recipesService.createRecipe(recipe);
        
        assertTrue(result);
    }

}

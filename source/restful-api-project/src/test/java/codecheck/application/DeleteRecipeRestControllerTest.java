package codecheck.application;

import static codecheck.common.TestUtils.readMessageFromFile;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import codecheck.domain.RecipesService;
import exception.RecipeNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * {@link DeleteRecipeRestController} のテストクラス。
 */
public class DeleteRecipeRestControllerTest {

    @Mock
    private RecipesService service;
    
    @InjectMocks
    private DeleteRecipeRestController controller;
    
    private MockMvc mvc;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    @Test
    public void test_idで指定したレシピを削除できる() throws Exception {
        doReturn(true).when(service).deleteRecipeById(2);
        
        mvc.perform(delete("/recipes/2"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().json(
                   readMessageFromFile("deleteRecipe/response_success.json")));
    }
    
    @Test
    public void test_idで指定したレシピが存在せずレシピ削除に失敗してエラーメッセージが返却される() throws Exception {
        doThrow(new RecipeNotFoundException()).when(service).deleteRecipeById(2);
        
        mvc.perform(delete("/recipes/2"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().json(
                   readMessageFromFile("deleteRecipe/response_failure.json")));
    }
}

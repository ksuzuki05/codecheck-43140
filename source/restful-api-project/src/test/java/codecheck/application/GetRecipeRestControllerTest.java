package codecheck.application;

import static codecheck.common.TestUtils.readMessageFromFile;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;
import codecheck.domain.RecipesService;
import codecheck.domain.model.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class GetRecipeRestControllerTest {

    @Mock
    private RecipesService service;
    
    @InjectMocks
    private GetRecipeRestController controller;
    
    private MockMvc mvc;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    @Test
    public void test_全レシピ一覧を取得できる() throws Exception {
        Recipe recipe1 = new Recipe("チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
        Recipe recipe2 = new Recipe("オムライス", "30分", "2人", "玉ねぎ,卵,スパイス,醤油", 700);
        Recipe recipe3 = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        
        Map<Integer, Recipe> map = new HashMap<>();
        map.put(1, recipe1);
        map.put(2, recipe2);
        map.put(3, recipe3);
        
        doReturn(map).when(service).getAllRecipes();
        
        mvc.perform(get("/recipes"))
           .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
           .andExpect(content().json(readMessageFromFile("getRecipe/response_success_all-recipes.json")));
    }
    
    @Test
    public void test_idで指定したレシピを取得できる() throws Exception {
        Recipe recipe = new Recipe("オムライス", "30分", "2人", "玉ねぎ,卵,スパイス,醤油", 700);
        
        doReturn(recipe).when(service).getRecipeById(2);
        
        mvc.perform(get("/recipes/2"))
           .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
           .andExpect(content().json(readMessageFromFile("getRecipe/response_success_recipe-by-id.json")));
    }
}

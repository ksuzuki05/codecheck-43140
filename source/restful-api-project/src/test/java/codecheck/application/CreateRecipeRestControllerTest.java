package codecheck.application;

import static codecheck.common.TestUtils.readMessageFromFile;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import codecheck.domain.RecipesService;
import codecheck.domain.model.Recipe;
import exception.InvalidRecipeException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CreateRecipeRestControllerTest {
    
    @Mock
    private RecipesService service;
    
    @InjectMocks
    private CreateRecipeRestController controller;
    
    private MockMvc mvc;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void test_レシピを作成でき成功メッセージが返却される() throws Exception {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        doReturn(true).when(service).createRecipe(recipe);
        
        mvc.perform(post("/recipes").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                    .content(readMessageFromFile(
                                            "createRecipe/request_success.json")))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().json(readMessageFromFile("createRecipe/response_success.json")));
    }
    
    @Test
    public void test_必須項目が不足しておりレシピ作成に失敗してエラーメッセージが返却される() throws Exception {
        Recipe recipe = new Recipe(null, "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        doThrow(new InvalidRecipeException()).when(service).createRecipe(recipe);
        
        mvc.perform(post("/recipes").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                    .content(readMessageFromFile(
                                            "createRecipe/request_title-is-null.json")))
            .andExpect(status().isBadRequest())
            .andExpect(content().json(readMessageFromFile("createRecipe/response_failure.json")));
    }

}

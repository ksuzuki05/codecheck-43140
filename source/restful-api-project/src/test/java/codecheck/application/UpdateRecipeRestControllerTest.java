package codecheck.application;

import static codecheck.common.TestUtils.readMessageFromFile;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import codecheck.domain.RecipesService;
import codecheck.domain.model.Recipe;

public class UpdateRecipeRestControllerTest {

    @Mock
    private RecipesService service;
    
    @InjectMocks
    private UpdateRecipeRestController controller;
    
    private MockMvc mvc;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    @Test
    public void test_idで指定したレシピを更新できる() throws Exception {
        Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
        doReturn(true).when(service).updateRecipe(2, recipe);
        
        mvc.perform(patch("/recipes/2").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                    .content(readMessageFromFile(
                                            "updateRecipe/request_success.json")))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().json(readMessageFromFile("updateRecipe/response_success.json")));
    }

}

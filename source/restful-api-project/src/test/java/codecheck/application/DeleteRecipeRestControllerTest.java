package codecheck.application;

import static codecheck.common.TestUtils.readMessageFromFile;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
           .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
           .andExpect(content().json(readMessageFromFile("deleteRecipe/response_success.json")));
    }

}

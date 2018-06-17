package codecheck.application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class DefaultRestControllerTest {
    
    private DefaultRestController controller = new DefaultRestController();
    
    private MockMvc mvc;
    
    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void test_ルートパスにアクセスするとHTTPステータス200が返却される() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
        mvc.perform(post("/")).andExpect(status().isOk());
        mvc.perform(patch("/")).andExpect(status().isOk());
        mvc.perform(delete("/")).andExpect(status().isOk());
    }

}

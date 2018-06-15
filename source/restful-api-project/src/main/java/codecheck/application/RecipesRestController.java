package codecheck.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import codecheck.domain.RecipesService;

/**
 * 各種 REST API を提供するクラス。
 */
@RestController
public class RecipesRestController {
    
    @Autowired
    RecipesService recipesService;
    
}

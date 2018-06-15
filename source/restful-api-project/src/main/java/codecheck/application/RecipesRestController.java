package codecheck.application;

import codecheck.domain.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 各種 REST API を提供するクラス。
 */
@RestController
public class RecipesRestController {
    
    @Autowired
    RecipesService recipesService;
    
}

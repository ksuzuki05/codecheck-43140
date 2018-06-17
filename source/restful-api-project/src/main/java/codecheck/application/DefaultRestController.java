package codecheck.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * デフォルトの REST API の挙動を提供するクラス。
 */
@RestController
public class DefaultRestController {

    /**
     * ルートパスにアクセスがきた場合、HTTP ステータス 200 を返却します。
     */
    @RequestMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public void root() {
        // Do Nothing.
    }
    
}

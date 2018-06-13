package codecheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot を起動するためのクラス。
 */
@SpringBootApplication
public class Application {

    /**
     * SpringBoot を起動するための main メソッド。
     * 
     * @param args main メソッドのコマンドライン引数
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

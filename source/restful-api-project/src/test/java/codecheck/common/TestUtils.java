package codecheck.common;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

/**
 * テストで使用されるユーティリティクラス。
 */
public class TestUtils {

    private TestUtils() {
        // Do Nothing.
    }
    
    /**
     * 指定したファイルからメッセージを読み取ります。
     * 
     * @param path src/test/resources 配下のファイルパス
     * @return メッセージ
     * @throws IOException ファイル読み取りに失敗した場合
     */
    public static String readMessageFromFile(String path) throws IOException {
        String result = null;

        FileInputStream input = new FileInputStream("src/test/resources/" + path);
        result = IOUtils.toString(input, "UTF-8");
        IOUtils.closeQuietly(input);

        return result;
    }
}

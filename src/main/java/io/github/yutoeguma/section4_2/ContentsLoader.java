package io.github.yutoeguma.section4_2;

import io.github.yutoeguma.exeptions.ContentsNotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * コンテンツを取得するクラス
 *
 * @author yuto.eguma
 */
public class ContentsLoader {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** contentsLoader の唯一のインスタンス */
    private static ContentsLoader contentsLoader = new ContentsLoader();
    /** コンテンツの配置されているディレクトリ */
    private static String CONTENTS_PATH = new File("./").getAbsoluteFile().getParent() + "/src/main/resources/public";

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    private ContentsLoader() {}

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========

    /**
     * contentsLoader を取得します
     * @return contentsLoader (Singleton)
     */
    public static ContentsLoader get() {
        return contentsLoader;
    }


    /**
     * コンテンツを返します
     *
     * @param contentsPath コンテンツへのPath
     * @return コンテンツ
     */
    public Contents getContents(String contentsPath) throws IOException {

        File file = new File(CONTENTS_PATH + contentsPath);
        if (file.isFile()) {
            // ファイルが指定されていればそれを返す
            String filePath = file.getAbsolutePath();
            byte[] detail = Files.readAllBytes(Paths.get(filePath));
            return new Contents(filePath, detail);

        } else if (file.isDirectory()) {
            // ディレクトリであれば indexファイルを探して返す
            String filePath = file.getAbsolutePath() + "/index.html";
            byte[] detail = Files.readAllBytes(Paths.get(filePath));
            return new Contents(filePath, detail);

        } else {
            // 存在しなければExceptionを返す
            throw new ContentsNotFoundException("対象のファイルが存在しませんでした contentsPath : " + contentsPath);
        }
    }
}

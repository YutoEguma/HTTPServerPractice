package io.github.yutoeguma;

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


    /** コンテンツの配置されているディレクトリへのパス */
    private static String CONTENTS_PATH = new File("./").getAbsoluteFile().getParent() + "/src/main/resources/public";

}

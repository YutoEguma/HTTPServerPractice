package io.github.yutoeguma;

import lombok.Data;

/**
 * @author yuto
 */
@Data
public class Contents {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** ファイルの場所 */
    private String filePath;
    /** コンテンツの内容 */
    private byte[] detail;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Contents(String filePath, byte[] detail) {
        this.filePath = filePath;
        this.detail = detail;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========

    /**
     * コンテンツの拡張子を取得する
     * "." で区切り、一番最後の文字列を抜き出す
     *
     * @return 拡張子を取得する
     */
    public String getExtension() {
        String[] strArray = this.filePath.split("\\.");
        return strArray[strArray.length - 1];
    }
}

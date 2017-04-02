package io.github.yutoeguma.section4_2;


/**
 * @author yuto
 */
public class Contents {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** コンテンツの場所 */
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
     * コンテンツの内容を取得する
     *
     * @return コンテンツの内容
     */
    public byte[] getDetail() {
        return this.detail;
    }

    /**
     * コンテンツの拡張子を取得する<br>
     * "." で区切り、一番最後の文字列を抜き出す
     *
     * @return 拡張子
     */
    public String getExtension() {
        String[] strArray = this.filePath.split("\\.");
        return strArray[strArray.length - 1];
    }
}

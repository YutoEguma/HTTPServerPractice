package io.github.yutoeguma.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * ファイルの拡張子と content-type の対応を管理するEnumです
 *
 * @author yuto.eguma
 */
public enum ContentType {

    textPlain("text/plain; charset=UTF-8", "txt"),
    textCss("text/css; charset=UTF-8", "css"),
    textJavascript("text/javascript; charset=UTF-8", "js"),
    textHtml("text/html; charset=UTF-8", "html"),
    imageJpeg("image/jpeg", "jpg"),
    imagePing("image/ping", "png"),
    applicationPdf("application/pdf", "pdf");

    /** ヘッダーに記述するコンテンツのタイプ */
    private String contentType;
    /** 拡張子 */
    private String extension;

    /** 拡張子によってContentTypeを選択して取得するためのMap */
    private static Map<String, ContentType> EXTENSION_CONTENT_TYPE_MAP = new HashMap<>();
    static {
        for (ContentType value : values()) {
            EXTENSION_CONTENT_TYPE_MAP.put(value.getExtension(), value);
        }
    }

    /**
     * 拡張子から contentType を取得する
     * @param extension 拡張子
     * @return ContentType
     */
    public static ContentType extensionOf(String extension) {
        return EXTENSION_CONTENT_TYPE_MAP.get(extension);
    }

    ContentType(String contentType, String extension) {
        this.contentType = contentType;
        this.extension = extension;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getExtension() {
        return this.extension;
    }
}

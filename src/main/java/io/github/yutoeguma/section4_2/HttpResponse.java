package io.github.yutoeguma.section4_2;

import io.github.yutoeguma.enums.ContentType;
import io.github.yutoeguma.enums.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuto.eguma
 */
public class HttpResponse {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final String CR = "\r";
    private static final String LF = "\n";
    private static final String CRLF = CR + LF;
    private static final String SP = " ";

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH = "Content-Length";

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** レスポンスライン */
    private String httpVersion;
    private HttpStatus httpStatus;
    /** レスポンスヘッダ */
    private Map<String, String> responseHeaderAttr = new HashMap<>();
    /** レスポンスボディ */
    private byte[] body;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========

    /**
     * http status のみでレスポンスを作成
     *
     * @param status Http Status
     */
    public HttpResponse(HttpStatus status) {
        this.httpVersion = "HTTP/1.1";
        this.httpStatus = status;
        this.body = new byte[0];
        this.responseHeaderAttr.put(CONTENT_TYPE, ContentType.textHtml.getContentType());
        this.responseHeaderAttr.put(CONTENT_LENGTH, String.valueOf(this.body.length));
    }

    /**
     * http status と body でレスポンスを作成する
     * @param status
     * @param contents
     */
    public HttpResponse(HttpStatus status, Contents contents) {
        this.httpVersion = "HTTP/1.1";
        this.httpStatus = status;
        this.body = contents.getDetail();
        this.responseHeaderAttr.put(CONTENT_TYPE, ContentType.extensionOf(contents.getExtension()).getContentType());
        this.responseHeaderAttr.put(CONTENT_LENGTH, String.valueOf(this.body.length));
    }

    /**
     * バイナリ型でレスポンスを取得する
     * @return response
     */
    public byte[] getResponse() {
        List<Byte> byteList = new ArrayList<>();

        // レスポンスをByte型のリストに変換する
        StringBuilder sb = new StringBuilder();
        sb.append(this.httpVersion).append(SP).append(this.httpStatus.getStatusCode()).append(SP).append(this.httpStatus).append(CRLF);
        this.responseHeaderAttr.entrySet()
                .forEach(headerLine -> sb.append(headerLine.getKey()).append(":").append(SP).append(headerLine.getValue()).append(CRLF));
        sb.append(CRLF);
        for (byte b: sb.toString().getBytes()) byteList.add(b);
        for (byte b: this.body) byteList.add(b);

        // Byte型のリストからbyte配列へ変換
        byte[] byteArray = new byte[byteList.size() + body.length];
        for (int i = 0; i < byteList.size(); i++) {
            byteArray[i] = byteList.get(i);
        }
        return byteArray;
    }

    /**
     * その他必要な response-header を設定する
     * @param name response-field
     * @param value response-value
     */
    public void setHeader(String name, String value) {
        this.responseHeaderAttr.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // response-line
        sb.append(this.httpVersion).append(SP).append(this.httpStatus.getStatusCode()).append(SP).append(this.httpStatus).append(CRLF);

        // response-header
        this.responseHeaderAttr.forEach((key, value) -> sb.append(key).append(":").append(SP).append(value).append(CRLF));

        sb.append(CRLF);

        // response-body コメントアウト外すの注意！！
        //for (byte b :this.body) {
        //    sb.append(b);
        //}

        return sb.toString();
    }
}

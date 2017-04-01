package io.github.yutoeguma.enums;

/**
 * HTTP通信のレスポンスとそのステータスコードを定義するEnumです
 *
 * @author yuto.eguma
 */
public enum HttpStatus {

    /** 実装するHTTP ステータスコードを増やす場合はここに記述していく */
    OK(200),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    private int statusCode;

    HttpStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}

package io.github.yutoeguma.section3;

import io.github.yutoeguma.enums.ContentType;
import io.github.yutoeguma.enums.HttpStatus;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
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

    private static final String CONTENT_TYPE = "Content-Type: ";
    private static final String CONTENT_LENGTH = "Content-Length: ";

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** レスポンスライン */
    private String httpVersion;
    private HttpStatus httpStatus;
    /** レスポンスヘッダ */
    private Map<String, String> responseHeaderAttr = new HashMap<>();
    /** レスポンスボディ */
    private String body;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========

    /**
     * とりあえずここでは "Sample Response" の文字列だけ返す
     * @param status Http Status
     */
    public HttpResponse(HttpStatus status) {
        this.httpVersion = "HTTP/1.1";
        this.httpStatus = status;
        this.body = "<!DOCTYPE html><html><head></head><body><p>Sample Response</p></body></html>";
        this.responseHeaderAttr.put(CONTENT_TYPE, ContentType.textHtml.getContentType());
        this.responseHeaderAttr.put(CONTENT_LENGTH, String.valueOf(this.body.length()));
    }

    // ===================================================================================
    //                                                                     response writer
    //                                                                     ===============
    /**
     * レスポンスを返す
     */
    public void writeResponse(OutputStream os) throws IOException {
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(os));
        br.write(this.toString());
        br.close();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // request-line
        sb.append(this.httpVersion).append(SP).append(this.httpStatus.getStatusCode()).append(SP).append(this.httpStatus).append(CRLF);

        // header-line
        this.responseHeaderAttr.entrySet()
                .forEach(headerLine -> sb.append(headerLine.getKey()).append(":").append(SP).append(headerLine.getValue()).append(CRLF));

        sb.append(CRLF);

        // request-body
        sb.append(this.body);

        return sb.toString();
    }
}

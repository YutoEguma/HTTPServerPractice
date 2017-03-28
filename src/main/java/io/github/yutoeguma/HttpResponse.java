package io.github.yutoeguma;

import lombok.Data;

/**
 * @author yuto.eguma
 */
@Data
public class HttpResponse {

    private static final String CR = "\r";
    private static final String LF = "\n";
    private static final String CRLF = CR + LF;
    private static final String SP = " ";

    private static final String CONTENT_TYPE = "Content-Type: ";
    private static final String CONTENT_LENGTH = "Content-Length: ";
}

package io.github.yutoeguma.section2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * http request に対してアクセス
 *
 * @author yuto.eguma
 */
public class HttpRequest {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final String CR = "\r";
    private static final String LF = "\n";
    private static final String CRLF = CR + LF;
    private static final String SP = " ";

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** リクエストライン */
    private String method;
    private String requestTarget;
    private String httpVersion;
    /** リクエストヘッダー */
    private Map<String, String> requestHeaderAttr = new HashMap<>();

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public HttpRequest(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        List<String> messageLineList = this.readRequestMessageLineList(reader);
        this.setRequestLine(messageLineList);
        this.setRequestHeader(messageLineList);
    }

    /**
     * リクエストメッセージを1行ごとに分けた文字列のリストに変換する
     *
     * @param reader BufferedReader
     * @return リクエストメッセージの1行ごとのリスト
     */
    private List<String> readRequestMessageLineList(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String headerLine = reader.readLine();
        while (headerLine != null && !headerLine.isEmpty()) {
            sb.append(headerLine).append(CRLF);
            headerLine = reader.readLine();
        }
        return Arrays.asList(sb.toString().split(CRLF));
    }

    /**
     * メッセージリストからリクエストラインを取り出す
     * @param messageLineList リクエストメッセージの1行ごとのリスト
     */
    private void setRequestLine(List<String> messageLineList) {
        this.setRequestLineElement(messageLineList.get(0));
    }

    /**
     * リクエストラインをスペースで3分割する
     * @param requestLine リクエストライン
     */
    private void setRequestLineElement(String requestLine) {
        List<String> requestLineList = Arrays.asList(requestLine.split(SP));
        this.method = requestLineList.get(0);
        this.requestTarget = requestLineList.get(1);
        this.httpVersion = requestLineList.get(2);
    }

    /**
     * メッセージのリストを field-name と field-value に分割して Map として取得する
     * @param messageLineList リクエストメッセージの1行ごとのリスト
     */
    private void setRequestHeader(List<String> messageLineList) {
        this.requestHeaderAttr = messageLineList.stream().skip(1)
                .map(line -> line.split(":" + SP))
                .filter(strArray -> strArray.length == 2)
                .collect(Collectors.toMap(strArray -> strArray[0], strArray -> strArray[1]));
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========

    public String getMethod() {
        return this.method;
    }

    public String getRequestTarget() {
        return this.requestTarget;
    }

    public String getHttpVersion() {
        return this.httpVersion;
    }

    /**
     * 指定された field-name の リクエストヘッダーの値を返す
     * @param name field-name
     * @return field-value (Nullable)
     */
    public String getHeader(String name) {
        return this.requestHeaderAttr.get(name);
    }

    /**
     * ログ出力用の文字列に変換する
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // request-line
        sb.append(CRLF)
                .append(this.method).append(SP).append(requestTarget).append(SP).append(httpVersion);

        // request-header
        this.requestHeaderAttr.entrySet()
                .forEach(headerLine -> sb.append(headerLine.getKey()).append(":").append(SP).append(headerLine.getValue()).append(CRLF));

        return sb.toString();
    }
}

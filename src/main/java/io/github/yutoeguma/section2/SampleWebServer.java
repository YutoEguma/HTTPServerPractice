package io.github.yutoeguma.section2;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * HTTPリクエストを除いてみる
 *
 * @author yuto.eguma
 */
public class SampleWebServer {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int portNum = 8091;
    private static final Logger logger = Logger.getLogger(SampleWebServer.class);

    // ===================================================================================
    //                                                                                Main
    //                                                                                ====
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(portNum)) {
            while (true) {
                try (
                        Socket socket = server.accept();
                        InputStream is = socket.getInputStream();
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
                ) {

                    // HTTPリクエストインスタンスを準備
                    logger.info(">>>>>>>>>>>>>>>>>>>> request");
                    HttpRequest request = new HttpRequest(is);
                    logger.info(request.toString());

                    // HTTPレスポンスを返す
                    bw.write("リクエストは以下のとおりだったよ");
                    bw.write(request.toString());
                }
            }
        }
    }
}

package io.github.yutoeguma.section4_1;

import io.github.yutoeguma.enums.HttpStatus;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * HTTPレスポンスバイナリで返して見る
 * 画像を返す練習
 *
 * @author yuto.eguma
 */
public class SampleWebServer {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int portNum = 8093;
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
                        OutputStream os = socket.getOutputStream()
                ) {

                    // HTTPリクエストインスタンスを準備
                    logger.info(">>>>>>>>>>>>>>>>>>>> request");
                    HttpRequest request = new HttpRequest(is);
                    logger.info(request.toString());

                    // HTTPレスポンスインスタンスを準備して返して見る
                    logger.info(">>>>>>>>>>>>>>>>>>>> response");
                    HttpResponse response = new HttpResponse(HttpStatus.OK);
                    logger.info(response.toString());
                    os.write(response.getResponse());
                }
            }
        }
    }
}

package io.github.yutoeguma.section4_2;

import io.github.yutoeguma.enums.HttpStatus;
import io.github.yutoeguma.exeptions.ContentsNotFoundException;
import io.github.yutoeguma.exeptions.IncorrectRequestException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * HTTP Request に応じてファイルを読み込む
 *
 * @author yuto.eguma
 */
public class SampleWebServer {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int portNum = 8094;
    private static final Logger logger = Logger.getLogger(SampleWebServer.class);
    private static final ContentsLoader contentsLoader = ContentsLoader.get();

    // ===================================================================================
    //                                                                                Main
    //                                                                                ====
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(portNum)) {
            while (true) {
                logger.info("\n");
                try (
                        Socket socket = server.accept();
                        InputStream is = socket.getInputStream();
                        OutputStream os = socket.getOutputStream()
                ) {
                    // request-target によってレスポンスを返す
                    HttpResponse response;
                    try {
                        logger.info(">>>>>>>>>>>>>>>>>>>> request");
                        HttpRequest request = new HttpRequest(is);
                        logger.info(request.toString());

                        logger.info(">>>>>>>>>>>>>>>>>>>> response");
                        Contents contents = contentsLoader.getContents(request.getRequestTarget());
                        response = new HttpResponse(HttpStatus.OK, contents);
                        logger.info(response.toString());

                    } catch (IncorrectRequestException e) {
                        logger.info("正しくないリクエストを受け付けました", e);
                        response = new HttpResponse(HttpStatus.BAD_REQUEST);
                    } catch (ContentsNotFoundException e) {
                        logger.info("request-target に対応するコンテンツが存在しません", e);
                        response = new HttpResponse(HttpStatus.NOT_FOUND);
                    } catch (IOException e) {
                        logger.error("IOExceptionが発生しました", e);
                        response = new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    os.write(response.getResponse());

                } catch (Exception e) {
                    // その他どこで例外が発生しても落とさないようにする
                    // logger.error("Error", e);
                }
            }
        }
    }
}

package io.github.yutoeguma.section5;

import io.github.yutoeguma.enums.HttpStatus;
import io.github.yutoeguma.exeptions.ContentsNotFoundException;
import io.github.yutoeguma.exeptions.IncorrectRequestException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 通信を受け付けたら
 * HTTPプロトコルとしてリクエストを解析しレスポンスをクライアントに返すタスク
 *
 * @author yuto.eguma
 */
public class CreateResponseTask implements Runnable {

    private static final Logger logger = Logger.getLogger(CreateResponseTask.class);
    private static final ContentsLoader contentsLoader = ContentsLoader.get();

    private Socket socket;

    public CreateResponseTask(Socket socket) {
        this.socket = socket;
    }

    /**
     * レスポンスを返すタスク
     */
    @Override
    public void run() {

        try (
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
            logger.info(response.toString());
            os.write(response.getResponse());
        } catch (Exception e) {
            // その他どこで例外が発生してもログに落としておく
            logger.error("Error", e);
        } finally {
            try {
                socket.close();
            } catch (IOException ignore) {}
        }
    }
}

package io.github.yutoeguma.section1;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Javaで通信を行うためのプログラミング
 *
 * @author yuto.eguma
 */
public class SampleWebServer {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int portNum = 8090;
    private static final Logger logger = Logger.getLogger(SampleWebServer.class);

    // ===================================================================================
    //                                                                                Main
    //                                                                                ====
    public static void main(String[] args) throws IOException {
        // ServerSocketを準備する
        try (ServerSocket server = new ServerSocket(portNum)) {

            // 接続を起動している間ずっと受け付ける
            while (true) {
                try (
                        // クライアントからの接続を待機する
                        // 接続があった場合、 Socketインスタンスが作成されて、
                        // そこに対して読み書きすることで通信のプロトコルが実装できる
                        Socket socket = server.accept();

                        // リクエストを読みとるための Buffered Reader
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        // レスポンスを書き出すための Buffered Writer
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
                ) {
                    // リクエストを読み込んでログに出す
                    logger.info(">>>>>>>>>>>>>>>>>>>> request");
                    StringBuilder sb = new StringBuilder();
                    sb.append("\n");
                    String line = br.readLine();
                    while (line != null && !line.isEmpty()) {
                        sb.append(line).append("\n");
                        line = br.readLine();
                    }
                    logger.info(sb.toString());

                    // レスポンスを返す
                    logger.info(">>>>>>>>>>>>>>>>>>>> response");
                    String respStr = "初めてのレスポンスを返すよ";
                    logger.info(respStr);
                    bw.write(respStr);
                }
            }
        }
    }
}

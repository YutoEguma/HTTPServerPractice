package io.github.yutoeguma.section5;

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
    private static final int portNum = 8095;

    // ===================================================================================
    //                                                                                Main
    //                                                                                ====
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(portNum)) {
            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new CreateResponseTask(socket));
                thread.start();
            }
        }
    }
}

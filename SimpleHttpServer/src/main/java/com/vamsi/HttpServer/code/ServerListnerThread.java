package com.vamsi.HttpServer.code;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListnerThread extends  Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerListnerThread.class);
    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListnerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                LOGGER.info("Connection is accepted :" + socket.getInetAddress());
                HttpConnectionWorkerThread workerThread = new HttpConnectionWorkerThread(socket);
                workerThread.start();
            }
        } catch (IOException e) {
            LOGGER.error("problem wit setting the socket",e);
            e.printStackTrace();
        }finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}

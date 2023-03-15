package com.vamsi.HttpServer.code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{
    private static  final Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket socket ;
    public HttpConnectionWorkerThread(Socket socket){
        this.socket =socket;
    }
    @Override
    public void run() {
        InputStream inputStream=null;
        OutputStream outputStream=null;
         try{
                  inputStream = socket.getInputStream();
                  outputStream = socket.getOutputStream();

               String html = "<html><head><title>Simple java Http server</title></head><body><h1>this page was served using simple java server</h1></body></html>";
            final String CRLF = "\n\r";
            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content.length :" + html.getBytes().length + CRLF +
                    CRLF +
                    html +
                    CRLF + CRLF;
            outputStream.write(response.getBytes());
            inputStream.close();
            outputStream.close();
            //socket.close();
            LOGGER.info("Processing finished");

        }catch (IOException e){
             LOGGER.error("problem with communication",e);
            e.printStackTrace();
    }finally {
             if(inputStream!=null){
                 try{
                     inputStream.close();
                 }catch (IOException e){
                     e.printStackTrace();
                 }
             }
             if(outputStream !=null){
                 try {
                     outputStream.close();
                 }catch (IOException e){
                     e.printStackTrace();
                 }
             }
             if(socket!=null){
                 try {
                     socket.close();
                 }catch (IOException e){
                     e.printStackTrace();
                 }
             }
         }
//            serverSocket.close();
    }
}

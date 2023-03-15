package com.vamsi.HttpServer;

/*
Driver class for the server
*/

import com.vamsi.HttpServer.code.ServerListnerThread;
import com.vamsi.HttpServer.config.Configuration;
import com.vamsi.HttpServer.config.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class HttpServer {
    private final static Logger LOGGER= LoggerFactory.getLogger(HttpServer.class);
    public static void main(String [] args) {

        LOGGER.info("Server is starting");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/Http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        LOGGER.info("using port "+ conf.getPort());
        LOGGER.info("using webroot"+ conf.getWebroot());
         try {
             ServerListnerThread serverListnerThread = new ServerListnerThread(conf.getPort(), conf.getWebroot());
             serverListnerThread.start();
         }catch (IOException e){
             e.printStackTrace();
         }
    }
}

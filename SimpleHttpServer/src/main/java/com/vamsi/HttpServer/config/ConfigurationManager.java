package com.vamsi.HttpServer.config;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.vamsi.HttpServer.Util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager myconfigurationmanager;
    private static Configuration myCurrentConfiguration;
    private ConfigurationManager(){
    }
    public static ConfigurationManager getInstance(){
        if(myconfigurationmanager==null){
            return new ConfigurationManager();
        }else{
           return  myconfigurationmanager;
        }
    }
    /*to load the cfg files by the path
    * provided
    * */
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb  = new StringBuffer();
        int i;
        try {
            while((i= fileReader.read()) !=-1){
                sb.append( (char)i);
            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
        JsonNode conf = null;
        String s = sb.toString();
        try {
            conf = Json.parse(s);
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the conifguaration filee");
        }
        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing confgi file interenal");
        }
    }

    /*
    Return the current loaded Configuration
    * */
    public Configuration getCurrentConfiguration() throws HttpConfigurationException {

        if(myCurrentConfiguration ==null){
            throw new HttpConfigurationException("No parent configuration Set");

        }else
            return myCurrentConfiguration;

    }
}

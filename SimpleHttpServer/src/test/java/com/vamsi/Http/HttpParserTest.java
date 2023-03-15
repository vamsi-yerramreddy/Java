package com.vamsi.Http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {

    private  HttpParser httpParser;
    @BeforeAll
    public void beforeClass(){
        httpParser = new HttpParser();
    }

    @Test
    void praseHttpRequest() throws IOException {
        HttpRequest request= null;
        try {
            request = httpParser.parseHttpRequest(
                    generateValidTestCase()
            );
        } catch (HttpParsingException e) {
            fail(e);
        }
        //httpParser.parseHttpRequest(generateValidTestCase());
        assertEquals(request.getMethod(),HttpMethod.GET);
    }
    //@Test
    /*void parseHttpRequestBadMethod1() throws IOException {
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseMethodName1()
            );
            fail();
        }catch(HttpParsingException e){
            assertEquals(e.getErrorCode(),HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }
    }*/

    @Test
    void parseHttpRequestBadMethod2() throws IOException {
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseMethodName2()
            );
            fail();
        }catch(HttpParsingException e){
            assertEquals(e.getErrorCode(),HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }
    }
    @Test
    void parseHttpRequestInvNumItems1() throws IOException {
        try{
            HttpRequest httpRequest= httpParser.parseHttpRequest(
                    generateBadTestCaserequestIneInNumbItems1()
            );
            fail();
        }catch (HttpParsingException e){
            assertEquals(e.getErrorCode(),HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
    }

    private InputStream generateBadTestCaserequestIneInNumbItems1() {
        String s = "\r\n";
        String rawData= "GET / AAAA HTTP/1.1" + s +
                "Host: localhost:8080" + s +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8" + s +
                "Accept-Language: en-US,en;q=0.5" + s ;
        InputStream inputStream= new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII
                )
        );
        return inputStream;

    }

    private InputStream generateValidTestCase(){
        String s = "\r\n";
        String rawData= "GET / HTTP/1.1" + s +
                "Host: localhost:8080" + s +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/110.0" + s +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8" + s +
                "Accept-Language: en-US,en;q=0.5" + s +
                "Accept-Encoding: gzip, deflate, br" + s +
                "Connection: keep-alive" + s +
                "Upgrade-Insecure-Requests: 1" + s +
                "Sec-Fetch-Dest: document" + s +
                "Sec-Fetch-Mode: navigate" + s +
                "Sec-Fetch-Site: none" + s +
                "Sec-Fetch-User: ?1"+s
                +s;
        InputStream inputStream=new ByteArrayInputStream(
                rawData.getBytes(
                       StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }
    private InputStream generateBadTestCaseMethodName1()  {
        String s = "\r\n";
        String rawData= "GET / HTTP/1.1" + s +
                "Host: localhost:8080" + s +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/110.0" + s +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8" + s +
                "Accept-Language: en-US,en;q=0.5" + s ;
        InputStream inputStream= new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII
                )
        );
        return inputStream;

    }
    private InputStream generateBadTestCaseMethodName2()  {
        String s = "\r\n";
        String rawData= "GETTWSSAS / HTTP/1.1" + s +
                "Host: localhost:8080" + s +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/110.0" + s +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8" + s +
                "Accept-Language: en-US,en;q=0.5" + s ;
        InputStream inputStream= new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII
                )
        );
        return inputStream;

    }
}
package com.vamsi.Http;

public enum HttpStatusCode {
CLIENT_ERROR_400_BAD_REQUEST(400,"Bad Request"),
    SERVER_ERROR_501_NOT_IMPLEMENTED(501,"serverError" ),
    CLIENT_ERROR_401_METHOD_NOTALLOWED(401,"Method not allowed"),
CLIENT_ERROR_414_BAD_REQUEST(414,"URI tooo long");


public final  int STATUS_CODE;
public final String MESSAGE;

HttpStatusCode(int statusCode,String message){
    this.STATUS_CODE = statusCode;
    this.MESSAGE=message;
}
}

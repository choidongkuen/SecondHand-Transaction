package com.example.shopproject.common.response;

public class ResponseResult {


    ResponseResultHeader header;
    Object body;

    public ResponseResult(boolean result, String message){
        header = new ResponseResultHeader(result,message);
    }

    public ResponseResult(boolean result){
        header = new ResponseResultHeader(result);
    }
}

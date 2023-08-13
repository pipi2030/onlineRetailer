package com.neutech.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//返回浏览器的数据
@Data
public class ServerResponse {
    private int status;
    private String message;
    private Map<String,Object> data = new HashMap<>();
    private ServerResponse(){}
    public static ServerResponse success(String message){
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(Constants.SUCCESS);
        serverResponse.setMessage(message);
        return serverResponse;
    }
    public static ServerResponse success(){
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(Constants.SUCCESS);
        return serverResponse;
    }
    public static ServerResponse failed(String message){
        ServerResponse ServerResponse = new ServerResponse();
        ServerResponse.setStatus(Constants.FAILED);
        ServerResponse.setMessage(message);
        return ServerResponse;
    }
    public ServerResponse data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}

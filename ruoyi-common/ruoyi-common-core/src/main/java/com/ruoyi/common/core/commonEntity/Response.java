package com.ruoyi.common.core.commonEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;

/**
 * @author DavidLoman
 * @create 2025-03-29 17:45
 */
@Data
@Schema(description = "返回对象")
public class Response<T extends Object>{
    @Schema(description ="返回状态 true-成功，false-失败")
    private Boolean isSuccess;
    @Schema(description = "返回码 0-失败 200-成功",required = true)
    private int code;
    @Schema(description ="返回信息")
    private String message;
    @Schema(description ="返回数据")
    private T  data;

    public static Response ok(){
        Response response =new Response();
        response.setIsSuccess(Boolean.TRUE);
        response.setMessage(ResponseEnum.SUCCESS.getMsg());
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setData(new HashMap<String,String>(8));
        //response.setData(new Object());
        return response;
    }



    public static <T>  Response<T> ok(T data){
        Response<T> response =new Response();
        response.setIsSuccess(Boolean.TRUE);
        response.setMessage(ResponseEnum.SUCCESS.getMsg());
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

    public static Response<String> failed(BaseEnum baseEnum){
        Response response =new Response();
        response.setIsSuccess(Boolean.FALSE);
        response.setCode(baseEnum.getCode());
        response.setMessage(baseEnum.getMsg());
        return response;
    }

    public static Response<String> failed(int code,String msg){
        Response response =new Response();
        response.setIsSuccess(Boolean.FALSE);
        response.setCode(code);
        response.setMessage(msg);
        return response;
    }

}

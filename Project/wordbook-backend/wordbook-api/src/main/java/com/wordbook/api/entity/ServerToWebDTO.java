package com.wordbook.api.entity;

// 传给前端数据的模板：后端（发送） --> 前端
public class ServerToWebDTO {

    // 成功响应码
    public static final int SUCCESS_CODE = 200;

    // 失败响应码
    public static final int ERROR_CODE = 500;

    // 表示成功或失败
    private int code=0;

    // 返回的数据，比如：UserDTO 类型的数据
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ServerToWebDTO(int code, Object data) {
        this.code = code;
        this.data = data;
    }
}

package com.zjgsu.utils;

/**
 * Created by IcedSoul on 2018/4/8.
 */

public class Response {
    public int code;
    public String message;
    public Object content;

    public Response() {
    }

    public Response(int code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public int getcode() {
        return code;
    }

    public void setcode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}

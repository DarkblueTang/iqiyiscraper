package com.tang.iqiyi.entity;

public class Message {
    private int code;
    private Object data;

    public Message() {
    }

    public Message(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

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
}

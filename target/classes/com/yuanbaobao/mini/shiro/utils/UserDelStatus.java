package com.codebaobao.shiro.utils;

public enum UserDelStatus {

    OK(0,"正常"),
    IS_DEL(1,"已删除");
    private int code;
    private String message;

    UserDelStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}

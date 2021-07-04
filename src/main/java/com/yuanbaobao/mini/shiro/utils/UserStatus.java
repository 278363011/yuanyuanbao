package com.codebaobao.shiro.utils;

public enum UserStatus {
    UN_APPROVAL("0","未审核"),
    OK("1", "启用"),
    FREEZED("2", "冻结"),
    EXPIRE("3", "过期");

    private String code;
    private String message;

    UserStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

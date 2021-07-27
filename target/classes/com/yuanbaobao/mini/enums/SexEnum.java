package com.yuanbaobao.mini.enums;

public enum SexEnum {
    MALE(0,"男"),
    FEMALE(1,"女"),
    UNKONW(2,"未知");

    private Integer status;
    private String desc;
     SexEnum(Integer status, String desc){
        this.status=status;
        this.desc=desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

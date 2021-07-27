package com.yuanbaobao.mini.enums;

public enum UserLevelEnum {
    NORMAL(0,"普通用户"),
    VIP(1,"vip用户"),
    SVIP(2,"超级vip用户"),
    CO_CREATOR(3,"共创者"),
    ADMIN(4,"管理者"),
    ROOT(5,"超级管理员");

    private Integer status;
    private String desc;
     UserLevelEnum(Integer status,String desc){
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

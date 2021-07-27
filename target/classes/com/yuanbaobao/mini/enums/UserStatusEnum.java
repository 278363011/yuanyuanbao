package com.yuanbaobao.mini.enums;

public enum UserStatusEnum {
    AVAILABLE(0,"可用的"),
    DISABLED(1,"禁用的"),
    CANCELLED(2,"注销的");

    private Integer status;
    private String desc;
     UserStatusEnum(Integer status, String desc){
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

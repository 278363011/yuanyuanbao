package com.codebaobao.sms;

public enum  TemplateEnum {


    ORDER_SUCCESS_STUDENT("933266","学生提前动身"),
    ORDER_SUCCESS_DOCTOR("933270","咨询师提前动身"),
    LOGIN("933275","登录"),
    FIND_PASSWORD("933255","找回密码"),
    CANCEL_ORDER("959939","取消订单通知咨询师"),
    ORDER_PRE_STUDENT("959938","订单预约成功通知学生"),
    ORDER_PRE_DOCTOR("959937","订单预约成功提醒咨询师");

    String templateId;
    String desc;

    TemplateEnum(String templateId,String desc){
        this.templateId = templateId;
        this.desc = desc;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

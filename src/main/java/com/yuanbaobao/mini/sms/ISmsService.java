package com.codebaobao.sms;

public interface ISmsService {

    String sendSmsCode(String phone);

    void sendSmsTemplate(String phone,String templateIdCode,String[] templateParams);


    boolean verifySmsCode(String phone, String code);
}

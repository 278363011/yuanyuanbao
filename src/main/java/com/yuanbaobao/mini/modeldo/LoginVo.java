package com.codebaobao.modeldo;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {
    String userName;
    String passWord;
    String verification_code;
    String imgsUrl;
    String sms_code;
}
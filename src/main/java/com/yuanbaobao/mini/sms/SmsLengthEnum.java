package com.codebaobao.sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public enum  SmsLengthEnum {
    /** 4位短信验证码 */
    SMS_LENGTH_4(4),
    /** 6位短信验证码 */
     SMS_LENGTH_6(6)
    ;

    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

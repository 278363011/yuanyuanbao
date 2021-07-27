package com.yuanbaobao.mini.exception;

/**
 * @author Jirath
 * @date 2020/4/9
 * @description:
 */

public class WxApiException extends Exception {
    public WxApiException(){super();}
    public WxApiException(String message) {
        super(message);
    }
    public WxApiException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.yuanbaobao.mini.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    //测试
//    public static void main(String[] args) {
//        String uid = UUIDUtil.getUUID();
//        System.out.println("===="+uid);
//    }
}

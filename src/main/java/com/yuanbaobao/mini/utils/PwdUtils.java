package com.codebaobao.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

public class PwdUtils {


    public static String encryption(final String pwd, final String salt) {
        return DigestUtils.md5DigestAsHex((pwd + salt).getBytes());
    }

    public static boolean comparePassword(final String sqlPwd, final String pwd, final String salt) {
        return StringUtils.equals(sqlPwd, DigestUtils.md5DigestAsHex((pwd + salt).getBytes()));
    }


    public static void main(String[] args) {
        System.out.println(PwdUtils.encryption("123", "123"));
    }

}

package com.codebaobao.modeldo;

import lombok.Data;

import java.io.Serializable;
@Data
public class CheckPdVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String phone;
    private String newPwd;
    private String checkWord;
}

package com.codebaobao.modeldo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CheckStudentVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String accountName;
    private String oldPw;
    private String newPw;
}

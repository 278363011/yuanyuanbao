package com.codebaobao.modeldo;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentOrderVo implements Serializable {

    private Integer studentId;

    private String tabStatus;
}

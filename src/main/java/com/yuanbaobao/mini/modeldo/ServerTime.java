package com.codebaobao.modeldo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
@Accessors
public class ServerTime implements Serializable {
    private static final long serialVersionUID = 1L;

    private int year;
    private int month;
    private int day;
}

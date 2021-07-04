package com.yuanbaobao.mini.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginRequestVo {
    String userName;
    String pwd;
}

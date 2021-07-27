package com.yuanbaobao.mini.shiro.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors
public class RoleByUserIdVo {
    private Long rid;
    private String rName;
}

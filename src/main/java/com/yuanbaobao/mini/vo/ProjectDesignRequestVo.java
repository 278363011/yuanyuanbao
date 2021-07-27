package com.yuanbaobao.mini.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors
public class ProjectDesignRequestVo {
    Integer pageNow;
    Integer pageSize;
    String orderType;
    Integer isHot;
    Integer cateType;
    String titleContent;

}

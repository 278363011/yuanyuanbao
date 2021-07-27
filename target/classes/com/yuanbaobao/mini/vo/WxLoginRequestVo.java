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
public class WxLoginRequestVo {
    String code;
    String nick;
    String avaUrl;
    Integer sex;
    String country;
    String city;
    String province;
    String language;
}

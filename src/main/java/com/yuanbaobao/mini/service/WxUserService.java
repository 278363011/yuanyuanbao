package com.yuanbaobao.mini.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuanbaobao.mini.model.WxUser;
import com.yuanbaobao.mini.vo.WxLoginRequestVo;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author codebaobao
 * @since 2021-07-03
 */
public interface WxUserService extends IService<WxUser> {

    Map<String, String> login(WxLoginRequestVo wxLoginRequestVo) throws Exception;
}

package com.yuanbaobao.mini.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuanbaobao.mini.model.SysUser;
import com.yuanbaobao.mini.vo.AdminLoginRequestVo;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author codebaobao
 * @since 2021-07-04
 */
public interface SysUserService extends IService<SysUser> {

    Map<String, String> login(AdminLoginRequestVo adminLoginRequestVo) throws Exception;
}

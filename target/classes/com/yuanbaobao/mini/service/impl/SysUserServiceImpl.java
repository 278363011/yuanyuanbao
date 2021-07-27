package com.yuanbaobao.mini.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuanbaobao.mini.mapper.SysUserMapper;
import com.yuanbaobao.mini.model.SysUser;
import com.yuanbaobao.mini.service.SysUserService;
import com.yuanbaobao.mini.vo.AdminLoginRequestVo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author codebaobao
 * @since 2021-07-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public Map<String, String> login(AdminLoginRequestVo adminLoginRequestVo) throws Exception {
        return null;
    }
}

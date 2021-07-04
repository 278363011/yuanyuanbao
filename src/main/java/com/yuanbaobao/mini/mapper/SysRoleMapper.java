package com.yuanbaobao.mini.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuanbaobao.mini.model.SysRole;
import com.yuanbaobao.mini.shiro.model.RoleByUserIdVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author codebaobao
 * @since 2021-07-04
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<RoleByUserIdVo> findRoleInfoByUserId(Long uid);
}

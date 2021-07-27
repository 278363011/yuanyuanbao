package com.yuanbaobao.mini.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuanbaobao.mini.model.SysMenu;
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
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<String> findMenuPermissionsByRoleId(Long roleId);
}

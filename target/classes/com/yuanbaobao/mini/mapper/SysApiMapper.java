package com.yuanbaobao.mini.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuanbaobao.mini.model.SysApi;
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
public interface SysApiMapper extends BaseMapper<SysApi> {

    List<String> findApiPermissionsByRoleId(Long roleId);

}

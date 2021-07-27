package com.yuanbaobao.mini.shiro.model;

import com.yuanbaobao.mini.model.SysApi;
import com.yuanbaobao.mini.model.SysMenu;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Accessors
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long id;          // 主键ID
    public Long wxUserId;   //给微信用户用的Id
    public String account;      // 账号
    public String name;         // 姓名
    public List<Long> roleList; // 角色集
    public List<String> roleNames; // 角色名称集
    /**
     * 角色对应菜单权限集合
     */
    private Set<SysMenu> menuPermissions;

    /**
     * 角色对应菜单权限集合
     */
    private Set<SysApi> apiPermissions;


}

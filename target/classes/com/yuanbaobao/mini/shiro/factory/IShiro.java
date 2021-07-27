package com.yuanbaobao.mini.shiro.factory;

import com.yuanbaobao.mini.model.SysUser;
import com.yuanbaobao.mini.model.WxUser;
import com.yuanbaobao.mini.shiro.model.RoleByUserIdVo;
import com.yuanbaobao.mini.shiro.model.ShiroUser;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import java.util.List;
import java.util.Map;

public interface IShiro {

    /**
     * 根据账号获取登录用户
     *
     * @param account 账号
     */
    SysUser user(String account);

    /**
     * 根据系统用户获取Shiro的用户
     *
     * @param user 系统用户
     */
    ShiroUser shiroUser(SysUser user);

    /**
     * 根据系统用户获取Shiro的用户
     *
     * @param user 系统用户
     */
    ShiroUser shiroWxUser(WxUser user);

    /**
     * 获取API权限列表通过角色id
     *
     * @param roleId 角色id
     */
    List<String> findApiPermissionsByRoleId(Long roleId);

    /**
     * 获取菜单权限列表通过角色id
     *
     * @param roleId 角色id
     */
    List<String> findMenuPermissionsByRoleId(Long roleId);


    /**
     * 根据用户id获取角色id及名称
     */
    List<RoleByUserIdVo> findRoleInfoByUserId(Long uid);

    /**
     * 获取shiro的认证信息
     */
    SimpleAuthenticationInfo info(ShiroUser shiroUser, SysUser user, String realmName);


    /**
     * 初始化权限 -> 拿全部权限
     */
    Map<String, String> loadFilterChainDefinitionMap();


    /**
     * 在对uri权限进行增删改操作时，需要调用此方法进行动态刷新加载数据库中的uri权限
     */
    void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean, Long roleId, Boolean isRemoveSession);


    /**
     * shiro动态权限加载 -> 原理：删除shiro缓存，重新执行doGetAuthorizationInfo方法授权角色和权限
     */
    void updatePermissionByRoleId(Long roleId, Boolean isRemoveSession);
}

package com.codebaobao.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebaobao.constants.Constant;
import com.codebaobao.mapper.RolesMapper;
import com.codebaobao.model.Roles;
import com.codebaobao.modeldo.PageVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.RolesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    RolesService roleService;
    @Autowired
    RolesMapper roleMapper;

    //增
    @RequestMapping("/add")
    public Result<String> addRole(@Valid @RequestBody final Roles role) {
        try {
            Roles existRole = this.roleService.getOne(new QueryWrapper<Roles>().eq("role_name", role.getRoleName()));
            if(Objects.nonNull(existRole)){
                return Result.error(CodeMsg.create(10000, "请勿重复插入相同的角色"));
            }

            if (this.roleService.save(role)) {
                // 清空缓存
                AdminLoginController.roelMap.clear();
                return Result.success(Constant.INSERT_SUCCESS);
            } else {
                return Result.error(CodeMsg.create(10000, Constant.INSERT_ERROR));
            }
        } catch (final Exception e) {
            log.error(Constant.INSERT_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.INSERT_EXCEPTION));
        }
    }

    //删
    @RequestMapping("/delete")
    public Result<String> deleteRole(@Valid @RequestBody final Roles role) {
        try {
            final Roles sqlRole = this.roleService.getById(role.getId());
            if (Objects.isNull(sqlRole)) {
                return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
            } else {
                if (this.roleService.removeById(role.getId())) {
                    // 清空缓存
                    AdminLoginController.roelMap.clear();
                    return Result.success(Constant.DELETE_SUCCESS);
                } else {
                    return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
                }
            }
        } catch (final Exception e) {
            log.error(Constant.DELETE_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.DELETE_EXCEPTION));
        }
    }

    //改
    @RequestMapping("/update")
    public Result<String> updateRole(@Valid @RequestBody final Roles role) {
        try {
            final Roles sqlRole = this.roleService.getById(role.getId());
            if (Objects.isNull(sqlRole)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                if (this.roleService.updateById(role)) {
                    // 清空缓存
                    AdminLoginController.roelMap.clear();
                    return Result.success(Constant.UPDATE_SUCCESS);
                } else {
                    return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
                }
            }
        } catch (final Exception e) {
            log.error(Constant.UPDATE_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.UPDATE_EXCEPTION));
        }
    }

    //查
    @RequestMapping("/queryById")
    public Result<Object> selectRoleById(@Valid @RequestBody final Roles role) {
        try {
            final Roles sqlRole = this.roleService.getById(role.getId());
            if (Objects.isNull(sqlRole)) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(sqlRole);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    //分页查
    @RequestMapping("/queryByPage")
    public Result<Object> selectAllRoleByPage(@Valid @RequestBody final PageVo pageVo) {
        try {
            final IPage<Roles> rolePage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.roleMapper.selectPage(rolePage, null);
            final List<Roles> roleList = rolePage.getRecords();
            if (Objects.isNull(roleList) || roleList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(rolePage);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


    @RequestMapping("/fuzzyQuery")
    public Result<Object> fuzzyQuery(@Valid @RequestBody final PageVo pageVo) {
        try {
            if(pageVo.getPageNow() == 0){
                pageVo.setPageNow(1);
            }
            if(pageVo.getPageSize()==0){
                pageVo.setPageSize(10);
            }
            final IPage<Roles> rolePage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.roleMapper.selectPage(rolePage, new QueryWrapper<Roles>()
                    .like("role_name", pageVo.getSearch())
                    .or().like("role_desc", pageVo.getSearch()));
            if (rolePage.getRecords().isEmpty()) {
                return Result.success("查询数据为空");
            } else {
                return Result.success(rolePage);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    @RequestMapping("/selectAll")
    public Result<Object> selectAll() {
        try {
            final List<Roles> list = this.roleService.list();
            if (Objects.isNull(list) || list.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(list);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


}

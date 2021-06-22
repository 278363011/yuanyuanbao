package com.codebaobao.controller.h5;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codebaobao.constants.Constant;
import com.codebaobao.controller.admin.AdminLoginController;
import com.codebaobao.model.Roles;
import com.codebaobao.model.Student;
import com.codebaobao.modeldo.LoginVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.RolesService;
import com.codebaobao.service.StudentService;
import com.codebaobao.utils.JwtTokenUtil;
import com.codebaobao.utils.PwdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-22
 */
@Slf4j
@RestController
@RequestMapping("/h5/student")
public class StudentLoginController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    StudentService studentService;
    @Autowired
    RolesService roleService;

    @RequestMapping("/login")
    public Result<Object> dockLogin(@Valid @RequestBody final LoginVo loginVo) {

        Student studentInfo = this.studentService.getOne(new QueryWrapper<Student>()
                .eq("account_name", loginVo.getUserName()));
        if (Objects.isNull(studentInfo)) {
            studentInfo = this.studentService.getOne(new QueryWrapper<Student>().eq("tel_phone", loginVo.getUserName()));
            if(Objects.isNull(studentInfo)){
                return Result.error(CodeMsg.create(500, "账号不存在"));
            }
        }

        if (AdminLoginController.roelMap.isEmpty()) {
            final List<Roles> list = this.roleService.list();
            if (Objects.isNull(list) || list.isEmpty()) {
                return Result.error(CodeMsg.create(500, "账号信息异常"));
            }
            AdminLoginController.roelMap = list.stream().collect(Collectors.toMap(Roles::getId, Roles::getRoleName));
        }

        if (PwdUtils.comparePassword(studentInfo.getPwd(), loginVo.getPassWord(), studentInfo.getSalt())) {
            final Map<String, Object> claims = new HashMap<>();
            claims.put("username", studentInfo.getAccountName());
            claims.put("role", AdminLoginController.roelMap.get(studentInfo.getRoleId()));

            Map<String,Object> result = new HashMap<>();
            result.put("userinfo",studentInfo);
            result.put("token",this.jwtTokenUtil.generateToken(null));
            return Result.success(result);
        } else {
            return Result.error(CodeMsg.create(500, "密码错误"));
        }

    }

    @RequestMapping("/register")
    public Result<String> registerStudent(@Valid @RequestBody final Student student) {
        Student account_name = this.studentService.getOne(new QueryWrapper<Student>().eq("account_name", student.getAccountName()).or().eq("tel_phone", student.getTelPhone()));
        if(Objects.nonNull(account_name)){
            return Result.error(CodeMsg.create(10010, "账号或手机号已被注册"));
        }
        try {
            //密码处理
            student.setSalt(student.getAccountName());
            student.setPwd(PwdUtils.encryption(student.getPwd(), student.getSalt()));
            if (this.studentService.save(student)) {
                return Result.success(Constant.INSERT_SUCCESS);
            } else {
                return Result.error(CodeMsg.create(10000, Constant.INSERT_ERROR));
            }
        } catch (final Exception e) {
            log.error(Constant.INSERT_EXCEPTION, e);
                return Result.error(CodeMsg.create(10000, "用户名太长，请您换一个"));
        }
    }










}

package com.codebaobao.controller.h5;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codebaobao.constants.Constant;
import com.codebaobao.model.PsychologicalOrder;
import com.codebaobao.model.Student;
import com.codebaobao.modeldo.CheckStudentVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.StudentService;
import com.codebaobao.utils.PwdUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/h5/person")
public class H5PersonController {


    @Autowired
    StudentService studentService;

    //查
    @RequestMapping("/queryById")
    public Result<Object> selectStudentById(@Valid @RequestBody final Student student) {
        try {
            final Student sqlStudent = this.studentService.getById(student.getId());
            if (Objects.isNull(sqlStudent)) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(sqlStudent);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    //改
    @RequestMapping("/update")
    public Result<String> updateStudent(@Valid @RequestBody final Student student) {
        try {
            final Student sqlStudent = this.studentService.getById(student.getId());
            if (Objects.isNull(sqlStudent)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                //密码处理
                student.setPwd(sqlStudent.getPwd());
                student.setSalt(sqlStudent.getSalt());
                if (this.studentService.updateById(student)) {
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



    @RequestMapping("/updatePwd")
    public Result<String> updatePwd(@Valid @RequestBody final CheckStudentVo student) {
        try {
            final Student sqlStudent = this.studentService.getById(student.getId());
            if (Objects.isNull(sqlStudent)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                if(!StringUtils.equalsIgnoreCase(PwdUtils.encryption(student.getOldPw(), sqlStudent.getSalt()),sqlStudent.getPwd())){
                    return Result.error(CodeMsg.create(10000, "原始密码错误,修改失败"));
                }
                //密码处理
                Student updateStudent = new Student();
                updateStudent.setId(student.getId());
                updateStudent.setPwd(PwdUtils.encryption(student.getNewPw(), sqlStudent.getSalt()));
                if (this.studentService.updateById(updateStudent)) {
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








}

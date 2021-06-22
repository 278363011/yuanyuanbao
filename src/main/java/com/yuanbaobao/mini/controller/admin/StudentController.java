package com.codebaobao.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebaobao.constants.Constant;
import com.codebaobao.mapper.StudentMapper;
import com.codebaobao.mapstruct.StudentConvert;
import com.codebaobao.model.Roles;
import com.codebaobao.model.SchoolArea;
import com.codebaobao.model.Student;
import com.codebaobao.modeldo.PageVo;
import com.codebaobao.modeldo.StudentVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.RolesService;
import com.codebaobao.service.SchoolAreaService;
import com.codebaobao.service.StudentService;
import com.codebaobao.utils.PwdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/admin/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    StudentMapper studentMapper;

    //增
    @RequestMapping("/add")
    public Result<String> addStudent(@Valid @RequestBody final Student student) {
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
            return Result.error(CodeMsg.create(10000, Constant.INSERT_EXCEPTION));
        }
    }

    //删
    @RequestMapping("/delete")
    public Result<String> deleteStudent(@NotNull @RequestBody final Student student) {
        try {
            final Student sqlStudent = this.studentService.getById(student.getId());
            if (Objects.isNull(sqlStudent)) {
                return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
            } else {
                if (this.studentService.removeById(student.getId())) {
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
    public Result<String> updateStudent(@Valid @RequestBody final Student student) {
        try {
            final Student sqlStudent = this.studentService.getById(student.getId());
            if (Objects.isNull(sqlStudent)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                //密码处理
                student.setSalt(student.getAccountName());
                student.setPwd(PwdUtils.encryption(student.getPwd(), student.getSalt()));
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

    //分页查
    @RequestMapping("/queryByPage")
    public Result<Object> selectAllStudentByPage(@Valid @RequestBody final PageVo pageVo) {
        try {
            final IPage<Student> studentPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.studentMapper.selectPage(studentPage, null);
            final List<Student> studentList = studentPage.getRecords();
            if (Objects.isNull(studentList) || studentList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(studentPage);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    @Autowired
    RolesService rolesService;
    @Autowired
    SchoolAreaService schoolAreaService;

    @RequestMapping("/fuzzyQuery")
    public Result<Object> fuzzyQuery(@Valid @RequestBody final PageVo pageVo) {
        try {
            if(pageVo.getPageNow() == 0){
                pageVo.setPageNow(1);
            }
            if(pageVo.getPageSize()==0){
                pageVo.setPageSize(10);
            }
            final IPage<Student> studentPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.studentMapper.selectPage(studentPage, new QueryWrapper<Student>()
                    .like("stu_no", pageVo.getSearch())
                    .or().like("name", pageVo.getSearch())
                    .or().like("sex", pageVo.getSearch())
                    .or().like("age", pageVo.getSearch())
                    .or().like("department", pageVo.getSearch())
                    .or().like("collage", pageVo.getSearch())
                    .or().like("profess", pageVo.getSearch())
                    .or().like("grade", pageVo.getSearch())
                    .or().like("clazz", pageVo.getSearch())
                    .or().like("tel_phone", pageVo.getSearch())
                    .or().like("email", pageVo.getSearch())
                    .or().like("account_name", pageVo.getSearch())
            );

            if (studentPage.getRecords().isEmpty()) {
                return Result.success("查询数据为空");
            } else {
                //获取区域
                final List<SchoolArea> areaList = this.schoolAreaService.list();
                //获取角色
                final List<Roles> roleList = this.rolesService.list();
                //做一下字段补充

                final List<StudentVo> studentVoList = studentPage.getRecords().stream().map(item -> {
                    final StudentVo studentVo = StudentConvert.INSTANCE.domain2dto(item);
                    areaList.forEach(aitem -> {
                        if (studentVo.getAreaId().equals(aitem.getId())) {
                            studentVo.setAreaName(aitem.getAreaName());
                        }
                    });
                    roleList.forEach(citem -> {
                        if (studentVo.getRoleId().equals(citem.getId())) {
                            studentVo.setRoleName(citem.getRoleName());
                        }
                    });
                    return studentVo;
                }).collect(Collectors.toList());
                final IPage<StudentVo> studentVoIPage = new Page<>();
                studentVoIPage.setRecords(studentVoList);
                studentVoIPage.setCurrent(studentPage.getCurrent());
                studentVoIPage.setPages(studentPage.getPages());
                studentVoIPage.setSize(studentPage.getSize());
                studentVoIPage.setTotal(studentPage.getTotal());
                return Result.success(studentVoIPage);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


    @RequestMapping("/getAllByPage")
    public Result<Object> getDoctorDetailInfo(@Valid @RequestBody final PageVo pageVo) {
        final IPage<StudentVo> studentVoPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
        final IPage<StudentVo> allDoctorInfo = this.studentService.getStudentDetailInfo(studentVoPage);
        if (allDoctorInfo.getRecords().isEmpty()) {
            return Result.error(CodeMsg.create(1000, "数据为空"));
        } else {
            return Result.success(allDoctorInfo);
        }
    }


}

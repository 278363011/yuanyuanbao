package com.codebaobao.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebaobao.constants.Constant;
import com.codebaobao.mapper.DoctorMapper;
import com.codebaobao.mapstruct.DoctorConvert;
import com.codebaobao.model.Doctor;
import com.codebaobao.model.PsychologicalTypeDic;
import com.codebaobao.model.Roles;
import com.codebaobao.model.SchoolArea;
import com.codebaobao.modeldo.DoctorVo;
import com.codebaobao.modeldo.PageVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.DoctorService;
import com.codebaobao.service.RolesService;
import com.codebaobao.service.SchoolAreaService;
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
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/admin/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;
    @Autowired
    DoctorMapper doctorMapper;


    @RequestMapping("/selectAll")
    public Result<Object> selectAll() {
        try {
            final List<Doctor> list = this.doctorService.list();
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

    //增
    @RequestMapping("/add")
    public Result<String> addDoctor(@Valid @RequestBody final Doctor doctor) {
        try {
            //密码处理
            doctor.setSalt(doctor.getAccountName());
            doctor.setPwd(PwdUtils.encryption(doctor.getPwd(), doctor.getSalt()));
            doctor.setImgsUrl("/images/"+doctor.getImgsUrl());
            doctor.setRoleId(2);
            if (this.doctorService.save(doctor)) {
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
    public Result<String> deleteDoctor(@Valid @RequestBody final Doctor doctor) {
        try {

            final Doctor sqlDoctor = this.doctorService.getById(doctor.getId());
            if (Objects.isNull(sqlDoctor)) {
                return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
            } else {
                if (this.doctorService.removeById(doctor.getId())) {
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
    public Result<String> updateDoctor(@Valid @RequestBody final Doctor doctor) {
        try {
            final Doctor sqlDoctor = this.doctorService.getById(doctor.getId());

            if (Objects.isNull(sqlDoctor)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                if(!doctor.getImgsUrl().startsWith("/images/")){
                    doctor.setImgsUrl("/images/"+doctor.getImgsUrl());
                }
                //密码处理
                if(!StringUtils.equalsIgnoreCase(sqlDoctor.getPwd(),doctor.getPwd())){
                    doctor.setSalt(doctor.getAccountName());
                    doctor.setPwd(PwdUtils.encryption(doctor.getPwd(), doctor.getSalt()));
                }

                if (this.doctorService.updateById(doctor)) {
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
    public Result<Object> selectDoctorById(@Valid @RequestBody final Doctor doctor) {
        try {
            final Doctor doctors = this.doctorService.getById(doctor.getId());
            if (Objects.isNull(doctors)) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(doctors);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    //分页查
    @RequestMapping("/queryByPage")
    public Result<Object> selectAllDoctorByPage(@Valid @RequestBody final PageVo pageVo) {
        try {
            final IPage<Doctor> doctorPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.doctorMapper.selectPage(doctorPage, null);
            final List<Doctor> doctorList = doctorPage.getRecords();
            if (Objects.isNull(doctorList) || doctorList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(doctorPage);
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
            final IPage<Doctor> doctorPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.doctorMapper.selectPage(doctorPage, new QueryWrapper<Doctor>()
                    .like("doctor_code", pageVo.getSearch())
                    .or().like("doctor_name", pageVo.getSearch())
                    .or().like("doctor_desc", pageVo.getSearch())
                    .or().like("sex", pageVo.getSearch())
                    .or().like("account_name", pageVo.getSearch())
                    .or().like("tel_phone", pageVo.getSearch())
                    .or().like("sex", pageVo.getSearch())
                    .or().like("age", pageVo.getSearch())
            );

            if (doctorPage.getRecords().isEmpty()) {
                return Result.success("查询数据为空");
            } else {
                //获取区域
                final List<SchoolArea> areaList = this.schoolAreaService.list();
                //获取角色
                final List<Roles> roleList = this.rolesService.list();
                //做一下字段补充

                final List<DoctorVo> doctorVoList = doctorPage.getRecords().stream().map(item -> {
                    final DoctorVo doctorVo = DoctorConvert.INSTANCE.domain2dto(item);
                    areaList.forEach(aitem -> {
                        if (doctorVo.getAreaId().equals(aitem.getId())) {
                            doctorVo.setAreaName(aitem.getAreaName());
                        }
                    });
                    roleList.forEach(citem -> {
                        if (doctorVo.getRoleId().equals(citem.getId())) {
                            doctorVo.setRoleName(citem.getRoleName());
                        }
                    });
                    return doctorVo;
                }).collect(Collectors.toList());
                final IPage<DoctorVo> doctorVoIPage = new Page<>();
                doctorVoIPage.setRecords(doctorVoList);
                doctorVoIPage.setCurrent(doctorPage.getCurrent());
                doctorVoIPage.setPages(doctorPage.getPages());
                doctorVoIPage.setSize(doctorPage.getSize());
                doctorVoIPage.setTotal(doctorPage.getTotal());
                return Result.success(doctorVoIPage);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


    @RequestMapping("/getAllByPage")
    public Result<Object> getDoctorDetailInfo(@Valid @RequestBody final PageVo pageVo) {
        final IPage<DoctorVo> doctorPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
        final IPage<DoctorVo> allDoctorInfo = this.doctorService.getDoctorDetailInfo(doctorPage);
        if (allDoctorInfo.getRecords().isEmpty()) {
            return Result.error(CodeMsg.create(1000, "数据为空"));
        } else {
            return Result.success(allDoctorInfo);
        }
    }





}

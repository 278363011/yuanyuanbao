package com.codebaobao.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.codebaobao.constants.Constant;
import com.codebaobao.model.DoctorWeek;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.DoctorWeekService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author codebaobao
 * @since 2021-04-18
 */
@Slf4j
@RestController
@RequestMapping("/admin/doctorWeek")
public class DoctorWeekController {

    @Autowired
    DoctorWeekService doctorWeekService;

    @RequestMapping("/saveAndUpdate")
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean>  saveOrUpdateWork(@RequestBody final List<DoctorWeek> doctorWeekList){
        try {
            doctorWeekList.forEach(item->{
                doctorWeekService.saveOrUpdate(item,
                        new UpdateWrapper<DoctorWeek>()
                                .eq("doctor_id", item.getDoctorId())
                                .eq("weekday", item.getWeekday())
                                .eq("time", item.getTime()));
            });
            return Result.success(true);
        } catch (final Exception e) {
            log.error(Constant.DELETE_EXCEPTION, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error(CodeMsg.create(10000, Constant.DELETE_EXCEPTION));
        }
    }

    @RequestMapping("/getAllWorkByDoctorId")
    public Result<List<DoctorWeek>> getAllWorkByDoctorId(@RequestBody final DoctorWeek doctorWeek){
        try {
            List<DoctorWeek> list = doctorWeekService.list(new QueryWrapper<DoctorWeek>().eq("doctor_id", doctorWeek.getDoctorId())
                    .eq("weekday", doctorWeek.getWeekday()));
            if(Objects.nonNull(list)){
                return Result.success(list);
            }

        } catch (final Exception e) {
            log.error(Constant.DELETE_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.DELETE_EXCEPTION));
        }
        return Result.success(null);
    }








}

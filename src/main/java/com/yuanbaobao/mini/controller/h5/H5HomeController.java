package com.codebaobao.controller.h5;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.codebaobao.constants.Constant;
import com.codebaobao.mapstruct.OrderConvert;
import com.codebaobao.model.*;
import com.codebaobao.modeldo.CheckPdVo;
import com.codebaobao.modeldo.DoctorVo;
import com.codebaobao.modeldo.OrderVo;
import com.codebaobao.modeldo.ServerTime;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.*;
import com.codebaobao.sms.ISmsService;
import com.codebaobao.sms.TemplateEnum;
import com.codebaobao.utils.PwdUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.TransactionalCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/h5/home")
public class H5HomeController {

    @Autowired
    RotationChartService rotationChartService;

    @Autowired
    SchoolAreaService schoolAreaService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PsychologicalOrderService orderService;

    @Autowired
    PsychologicalTypeDicService psychologicalTypeDicService;

    @Autowired
    PsychologicalRoomService psychologicalRoomService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    ISmsService smsService;



    // 获取首页轮播器
    @RequestMapping("/getHomeRotationCharts")
    public Result<Object> getHomeRotationCharts() {
        try {
            final List<RotationChart> list = this.rotationChartService.list();
            if (Objects.isNull(list) || list.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(list);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.SELECT_EXCEPTION));
        }
    }

    // 获取所有校区
    @RequestMapping("/getHomeSchoolArea")
    public Result<Object> getAllSchoolArea() {
        try {
            List<SchoolArea> list = this.schoolAreaService.list();
            if (Objects.isNull(list) || list.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(list);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.SELECT_EXCEPTION));
        }
    }

    // 通过校区获取对应的所有医生
    @RequestMapping("/getDoctorsByAreaId")
    public Result<Object> getDoctorsByAreaId(@Valid @RequestBody final DoctorVo doctorvo) {
        try {
            List<Doctor> doctorList = this.doctorService.list(new QueryWrapper<Doctor>().eq("area_id", doctorvo.getAreaId()).eq("role_id",2 ));
            if (Objects.isNull(doctorList) || doctorList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(doctorList);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.SELECT_EXCEPTION));
        }
    }


    @RequestMapping("/getServerTime")
    public Result<Object> getServerTime() {

        Map<String, ServerTime> resultMap = new HashMap<>();
        LocalDateTime currentDate = LocalDateTime.now();
        ServerTime minDate = ServerTime.builder().year(currentDate.getYear()).month(currentDate.getMonthValue()).day(currentDate.getDayOfMonth()).build();
        resultMap.put("minDate", minDate);
        LocalDateTime futureDate = LocalDateTime.now().plusDays(30);
        ServerTime maxDate = ServerTime.builder().year(futureDate.getYear()).month(futureDate.getMonthValue()).day(futureDate.getDayOfMonth()).build();
        resultMap.put("maxDate", maxDate);
        return Result.success(resultMap);
    }

    // 获取当前医生的所有有效预约时间
    @RequestMapping("/getPsychologicalTimeByDoctorAndDate")
    public Result<Object> getPsychologicalTimeByDoctorAndDate(@Valid @RequestBody final OrderVo order) {
        try {
            List<PsychologicalOrder> orderList = this.orderService.list(
                    new QueryWrapper<PsychologicalOrder>()
                            .eq("doctor_id", order.getDoctorId())
                            .eq("del_flag", 0)
                            .eq("order_date", order.getOrderDate()));
            if (Objects.isNull(orderList) || orderList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(orderList);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.SELECT_EXCEPTION));
        }
    }

    // 提交预约订单
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/submitOrder")
    public Result<String> submitOrder(@Valid @RequestBody final List<OrderVo> orderList) {
        try {
            if(!orderList.isEmpty()){
                // 预定日期  人数的校验
                orderList.stream().forEach(item -> {
                    // 日期的校验
                    List<PsychologicalOrder> list = this.orderService.list(
                            new QueryWrapper<PsychologicalOrder>()
                                    .eq("order_date", item.getOrderDate())
                                    .eq("order_starttime", item.getOrderStarttime())
                                    .eq("area_id", item.getAreaId())
                                    .eq("room_id", item.getRoomId())
                    );
                    if(list.isEmpty()){
                        item.setOrderCode(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
                        PsychologicalOrder psychologicalOrder = OrderConvert.INSTANCE.domain2dto(item);
                        this.orderService.save(psychologicalOrder);
                    }else{
                        throw new IllegalStateException("预约冲突");
                    }
                });
                OrderVo orderVo = orderList.get(0);
                Doctor doctor = doctorService.getById(orderVo.getDoctorId());
                Student student = studentService.getById(orderVo.getStudentId());
                // 给老师发送短信需要审批
                smsService.sendSmsTemplate(doctor.getTelPhone(), TemplateEnum.ORDER_PRE_DOCTOR.getTemplateId(),new String[]{doctor.getDoctorName(),student.getName()});
                return Result.success(Constant.INSERT_SUCCESS);
            }
            return Result.error(CodeMsg.create(10000, "预约信息不能为空"));
        } catch (final Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(Constant.INSERT_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, "预约冲突"));
        }
    }

    // 获取预约类型
    @RequestMapping("/getCategories")
    public Result<Object> getCategories() {
        try {
            final List<PsychologicalTypeDic> list = this.psychologicalTypeDicService.list();
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

    //获取预约室通过校区
    @RequestMapping("/getRoomByAreaId")
    public Result<Object> getRoomByAreaId(@Valid @RequestBody final PsychologicalRoom psychologicalRoom) {
        try {
            final List<PsychologicalRoom> list = this.psychologicalRoomService.list(new QueryWrapper<PsychologicalRoom>().eq("area_id", psychologicalRoom.getAreaId()));
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


    @Autowired
    DoctorWeekService doctorWeekService;
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




    // 发送短信
    @RequestMapping("/sendSms")
    public Result<String> sendSms(HttpServletRequest request,@RequestBody final CheckPdVo checkPdVo) {
            if(StringUtils.isBlank(checkPdVo.getPhone())){
                return Result.success("请输入手机号");
            }

            try {
                if(StringUtils.isBlank(checkPdVo.getPhone())){
                    return Result.success("号码不能为空");
                }
                if(stringRedisTemplate.hasKey(checkPdVo.getPhone())){
                    return Result.success("5分钟内只允许发一条验证短信");
                }
                stringRedisTemplate.opsForValue().set(checkPdVo.getPhone(), "1", 5, TimeUnit.MINUTES);
                smsService.sendSmsCode(checkPdVo.getPhone());
            }catch (Exception e){
                if(Objects.nonNull(checkPdVo.getPhone())){
                    stringRedisTemplate.delete(checkPdVo.getPhone());
                }
            }

        return Result.success("短信发送成功");
    }

    @Autowired
    StudentService studentService;

    // 发送短信
    @RequestMapping("/checkNewPwd")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> checkNewPwd(@RequestBody final CheckPdVo checkPdVo) {
        try {
            if(smsService.verifySmsCode(checkPdVo.getPhone(),checkPdVo.getCheckWord())){
                Student student = studentService.getOne(new QueryWrapper<Student>().eq("tel_phone", checkPdVo.getPhone()));
                if(Objects.isNull(student)){
                    return Result.error(CodeMsg.create(500, "账号不存在"));
                }
                Student updateStudent = new Student();
                updateStudent.setId(student.getId());
                updateStudent.setPwd(PwdUtils.encryption(checkPdVo.getNewPwd(), student.getSalt()));
                if(studentService.updateById(updateStudent)){
                    return Result.success("密码修改成功");
                }else{
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
            }
        }catch (Exception e){
            log.error(StringUtils.EMPTY, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Result.error(CodeMsg.create(10000, "密码更新错误"));
    }

}

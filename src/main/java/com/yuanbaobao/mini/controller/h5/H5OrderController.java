package com.codebaobao.controller.h5;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebaobao.constants.Constant;
import com.codebaobao.model.Doctor;
import com.codebaobao.model.PsychologicalOrder;
import com.codebaobao.model.RotationChart;
import com.codebaobao.model.Student;
import com.codebaobao.modeldo.OrderVo;
import com.codebaobao.modeldo.StudentOrderVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.DoctorService;
import com.codebaobao.service.PsychologicalOrderService;
import com.codebaobao.service.StudentService;
import com.codebaobao.sms.ISmsService;
import com.codebaobao.sms.TemplateEnum;
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
@RequestMapping("/h5/order")
public class H5OrderController {
    @Autowired
    PsychologicalOrderService orderService;

    @Autowired
    ISmsService smsService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    StudentService studentService;

    // 获取当前学生的所有订单
    @RequestMapping("/getOrderByStudentId")
    public Result<Object> getOrderByStudentId(@Valid @RequestBody final OrderVo orderVo){
        try {
            final IPage<OrderVo> orderVoPage = new Page<>(orderVo.getPageNow(), orderVo.getPageSize());
            final IPage<OrderVo> allOrderInfo = this.orderService.getOrderByKeyWord(orderVoPage,orderVo);
            if (allOrderInfo.getRecords().isEmpty()) {
                return Result.error(CodeMsg.create(1000, "数据为空"));
            } else {
                return Result.success(allOrderInfo);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.SELECT_EXCEPTION));
        }
    }


    // 更新当前订单的评分
    @RequestMapping("/updateOrderRemark")
    public Result<Object> updateOrderRemark(@Valid @RequestBody final PsychologicalOrder order){
        try {
            PsychologicalOrder existOrder = this.orderService.getOne(new QueryWrapper<PsychologicalOrder>().eq("order_code", order.getOrderCode()));
            if(Objects.isNull(existOrder)){
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            }
            PsychologicalOrder psychologicalOrder=new PsychologicalOrder();
            psychologicalOrder.setRateRank(order.getRateRank());
            boolean updateFlag = this.orderService.update(psychologicalOrder, new QueryWrapper<PsychologicalOrder>().eq("order_code", existOrder.getOrderCode()));
            if (!updateFlag) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(Constant.UPDATE_SUCCESS);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.SELECT_EXCEPTION));
        }
    }



    // 学生取消当前订单
    @RequestMapping("/updateOrderByStudent")
    public Result<Object> updateOrderByStudent(@Valid @RequestBody final PsychologicalOrder order){
        try {
            PsychologicalOrder existOrder = this.orderService.getOne(new QueryWrapper<PsychologicalOrder>().eq("order_code", order.getOrderCode()));
            if(Objects.isNull(existOrder)){
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            }
            PsychologicalOrder psychologicalOrder=new PsychologicalOrder();
            psychologicalOrder.setOrderStatus("3");//3代表预约取消
            boolean updateFlag = this.orderService.update(psychologicalOrder, new QueryWrapper<PsychologicalOrder>().eq("order_code", existOrder.getOrderCode()));
            if (!updateFlag) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                // 给老师发短信
                Doctor doctor = doctorService.getById(existOrder.getDoctorId());
                Student student = studentService.getById(existOrder.getStudentId());
                if(Objects.nonNull(doctor) && Objects.nonNull(student) ){
                    smsService.sendSmsTemplate(doctor.getTelPhone(), TemplateEnum.CANCEL_ORDER.getTemplateId(),new String[]{doctor.getDoctorName(),student.getName()});
                }else{
                    log.error("订单关联的医生或学生实体居然为空");
                }
                return Result.success(Constant.UPDATE_SUCCESS);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.SELECT_EXCEPTION));
        }
    }




}

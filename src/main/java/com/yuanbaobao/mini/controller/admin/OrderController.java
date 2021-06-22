package com.codebaobao.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebaobao.constants.Constant;
import com.codebaobao.mapper.PsychologicalOrderMapper;
import com.codebaobao.model.Doctor;
import com.codebaobao.model.PsychologicalOrder;
import com.codebaobao.model.Student;
import com.codebaobao.modeldo.OrderVo;
import com.codebaobao.modeldo.PageVo;
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
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    PsychologicalOrderService orderService;

    @Autowired
    PsychologicalOrderMapper orderMapper;

    @Autowired
    ISmsService smsService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    StudentService studentService;

    //增
    @RequestMapping("/add")
    public Result<String> addOrder(@Valid @RequestBody final PsychologicalOrder order) {
        try {
            //生产订单ID
            order.setOrderCode(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
            if (this.orderService.save(order)) {
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
    public Result<String> deleteOrder(@NotNull @RequestBody final PsychologicalOrder order) {
        try {
            final PsychologicalOrder sqlOrder= this.orderService.getById(order.getId());
            if (Objects.isNull(sqlOrder)) {
                return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
            } else {
                if (this.orderService.removeById(order.getId())) {
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
    public Result<String> updateOrder(@Valid @RequestBody final PsychologicalOrder order) {
        try {
            final PsychologicalOrder sqlOrder= this.orderService.getById(order.getId());
            if (Objects.isNull(sqlOrder)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                if (this.orderService.updateById(order)) {
                    if(sqlOrder.getOrderStatus().equals("0") && order.getOrderStatus().equals("1")){
                        Doctor doctor = doctorService.getById(order.getDoctorId());
                        Student student = studentService.getById(order.getStudentId());
                        // 发短信给学生
                        smsService.sendSmsTemplate(student.getTelPhone(),TemplateEnum.ORDER_PRE_STUDENT.getTemplateId(),new String[]{student.getName()});
                    }
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
    public Result<Object> selectOrderById(@Valid @RequestBody final PsychologicalOrder order) {
        try {
            final PsychologicalOrder sqlOrder = this.orderService.getById(order.getId());
            if (Objects.isNull(sqlOrder)) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(sqlOrder);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    //分页查
    @RequestMapping("/queryByPage")
    public Result<Object> selectAllOrderByPage(@Valid @RequestBody final PageVo pageVo) {
        try {
            if(pageVo.getPageNow() == 0){
                pageVo.setPageNow(1);
            }
            if(pageVo.getPageSize()==0){
                pageVo.setPageSize(10);
            }
            final IPage<PsychologicalOrder> orderPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.orderMapper.selectPage(orderPage, null);
            final List<PsychologicalOrder> orderList = orderPage.getRecords();
            if (Objects.isNull(orderList) || orderList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(orderPage);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    @RequestMapping("/getAllByPage")
    public Result<Object> getOrderByKeyWord(@Valid @RequestBody final OrderVo orderVo) {
        final IPage<OrderVo> orderVoPage = new Page<>(orderVo.getPageNow(), orderVo.getPageSize());
        final IPage<OrderVo> allOrderInfo = this.orderService.getOrderByKeyWord(orderVoPage,orderVo);
        if (allOrderInfo.getRecords().isEmpty()) {
            return Result.error(CodeMsg.create(1000, "数据为空"));
        } else {
            return Result.success(allOrderInfo);
        }
    }

}

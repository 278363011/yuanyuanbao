package com.codebaobao.schedule;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codebaobao.model.Doctor;
import com.codebaobao.model.PsychologicalOrder;
import com.codebaobao.model.Student;
import com.codebaobao.service.DoctorService;
import com.codebaobao.service.PsychologicalOrderService;
import com.codebaobao.service.StudentService;
import com.codebaobao.sms.ISmsService;
import com.codebaobao.sms.TemplateEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Configuration
public class OrderSchedule {

    @Autowired
    PsychologicalOrderService psychologicalOrderService;

    @Autowired
    private ISmsService smsService;

    @Autowired
    private StudentService studentService;
    @Autowired
    private DoctorService doctorService;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void cronScheduled(){
        List<PsychologicalOrder> orderList = psychologicalOrderService.list(new QueryWrapper<PsychologicalOrder>()
                .eq("order_date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .eq("order_status", 1)
                .eq("del_flag", 0)
                .eq("confict", 0)
        );

        orderList.forEach(item->{
            String orderTimeStr = item.getOrderDate() + " " + item.getOrderStarttime()+":00";
            LocalDateTime orderTime = LocalDateTime.parse(orderTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime dateTime = LocalDateTime.now();
            if(dateTime.isAfter(orderTime)){
                //修改订单短信字段状态
                item.setConfict(1);
                boolean updateSuccess = psychologicalOrderService.updateById(item);
                if(updateSuccess){
                    //发送短信给学生
                    Student studentItem = studentService.getById(item.getStudentId());
                    if(StringUtils.isBlank(studentItem.getTelPhone())){
                        log.error("学生的手机为空:{}",JSON.toJSONString(studentItem));
                        return;
                    }
                    smsService.sendSmsTemplate(studentItem.getTelPhone(), TemplateEnum.ORDER_SUCCESS_STUDENT.getTemplateId(),new String[]{studentItem.getName()});
                    //发送短信给医生
                    Doctor doctorItem = doctorService.getById(item.getDoctorId());
                    if(StringUtils.isBlank(doctorItem.getTelPhone())){
                        log.error("医生的手机为空:{}",JSON.toJSONString(doctorItem));
                        return;
                    }
                    smsService.sendSmsTemplate(doctorItem.getTelPhone(), TemplateEnum.ORDER_SUCCESS_DOCTOR.getTemplateId(),new String[]{doctorItem.getDoctorName()});
                    System.out.println("准备发送短信"+ JSON.toJSONString(item));
                }
            }
        });

    }

}


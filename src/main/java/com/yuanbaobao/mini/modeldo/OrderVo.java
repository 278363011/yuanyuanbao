package com.codebaobao.modeldo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OrderVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 订单ID
     */
    private String orderCode;

    /**
     * 公共号码
     */
    private String commonPhone;

    /**
     * 预约类型
     */
    private Integer orderType;

    private String orderLevel;

    private String orderGrade;

    /**
     * 预约日期
     */
    private String orderDate;

    /**
     * 预约开始时间
     */
    private String orderStarttime;

    /**
     * 预约结束时间
     */
    private String orderEndtime;

    /**
     * 用户留言
     */
    private String stuMessage;

    /**
     * 医生留言
     */
    private String doctorMessage;

    /**
     * 订单状态 0审批中 1预约成功 2撤销 3订单完成
     */
    private String orderStatus;

    /**
     * 订单集合 1,2
     */
    private String orderStatusStr;

    /**
     * 订单评价 1,2,3,4,5评星
     */
    private Integer rateRank;

    /**
     * 预约医生id
     */
    private Integer doctorId;

    /**
     * 预约学生id
     */
    private Integer studentId;

    /**
     * 预约校区id
     */
    private Integer areaId;

    /**
     * 预约科室id
     */
    private Integer roomId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 是否冲突-后台管理订单审核中
     */
    private int confict;

    /**
     * 逻辑删除标识
     */
    private Integer delFlag;
    /**
     * 校区名字
     */
    private String areaName;
    /**
     * 预约室名字
     */
    private String roomName;
    /**
     * 学生名字
     */
    private String studentName;
    /**
     * 医生名字
     */
    private String doctorName;
    /**
     * 排序规则
     */
    private String orderBy;
    /**
     * 当前页
     */
    private int pageNow;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 学号
     */
    private String  stuNo;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private int age;
    /**
     * 邮件
     */
    private String email;
    /**
     * 学生电话号码
     */
    private String telPhone;
    /**
     * 医生电话号码
     */
    private String doctorTelPhone;

    private String department;
    /**
     * 班级
     */
    private String clazz;
    /**
     * 年级
     */
    private String  grade;
    /**
     * 专业
     */
    private String profess;

    private String collage;
    /**
     * 预约类型名字
     */
    private String typeName;


}

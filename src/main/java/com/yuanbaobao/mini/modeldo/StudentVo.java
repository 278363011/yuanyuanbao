package com.codebaobao.modeldo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class StudentVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 学号
     */
    private String stuNo;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;
    private String collage;

    /**
     * 专业
     */
    private String profess;

    /**
     * 年级
     */
    private String grade;

    /**
     * 班级
     */
    private String clazz;

    private String department;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 电话号码
     */
    private String telPhone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 所属校区
     */
    private Integer areaId;

    /**
     * 账号状态
     */
    private String accountStatus;

    /**
     * 账号
     */
    private String accountName;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 盐
     */
    private String salt;

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
     * 逻辑删除标识
     */
    private Integer delFlag;

    private String roleName;
    private String areaName;

}

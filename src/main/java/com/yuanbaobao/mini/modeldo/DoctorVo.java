package com.codebaobao.modeldo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DoctorVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 医生编号
     */
    private String doctorCode;

    /**
     * 医生名
     */
    private String doctorName;

    /**
     * 医生简介
     */
    private String doctorDesc;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 医生电话
     */
    private String telPhone;

    private String commonPhone;

    /**
     * 医生性别
     */
    private String sex;

    /**
     * 医生年龄
     */
    private Integer age;

    /**
     * 所属校区
     */
    private Integer areaId;

    /**
     * 用户名
     */
    private String accountName;

    private String imgsUrl;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 盐
     */
    @JsonIgnore
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

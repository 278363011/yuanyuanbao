package com.codebaobao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PsychologicalOrder extends Model<PsychologicalOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单ID
     */
    private String orderCode;

    /**
     * 预约类型
     */
    private Integer orderType;


    /**
     * 预约类型等级
     */
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

    /**getRoomByAreaId
     * 是否冲突-后台管理订单审核中
     */
    private int confict;

    /**
     * 逻辑删除标识
     */
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

package com.yuanbaobao.mini.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author codebaobao
 * @since 2021-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysOperationLog extends Model<SysOperationLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private Long userId;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 请求地址
     */
    private String actionUrl;

    /**
     * 请求方法
     */
    private String actionMethod;

    /**
     * 请求时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime finishTime;

    /**
     * 操作IP
     */
    private String ip;

    /**
     * 操作地点
     */
    private String location;

    /**
     * 操作平台
     */
    private String os;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

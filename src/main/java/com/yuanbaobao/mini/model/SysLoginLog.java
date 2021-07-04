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
public class SysLoginLog extends Model<SysLoginLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userid;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 登录IP
     */
    private String ip;

    /**
     * 登录地点
     */
    private String location;

    /**
     * 1成功 2失败
     */
    private Integer isSucceed;

    /**
     * 登录平台
     */
    private String os;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

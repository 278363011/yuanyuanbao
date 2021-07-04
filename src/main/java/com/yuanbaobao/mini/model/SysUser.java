package com.yuanbaobao.mini.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 用户名字
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 性别 1男 2女
     */
    private Integer sex;

    /**
     * 创建者
     */
    @TableField("createUserId")
    private Integer createUserId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0-待审核 ，1-正常，2-锁定
     */
    private Integer lockFlag;

    /**
     * 0-正常，1-逻辑删除
     */
    private Integer isDel;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

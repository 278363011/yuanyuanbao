package com.yuanbaobao.mini.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author codebaobao
 * @since 2021-07-03
 */
@Data
@Builder
@Accessors
@EqualsAndHashCode(callSuper = false)
public class WxUser extends Model<WxUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 小程序用户的openId
     */
    private String openId;

    /**
     * 0.普通 1.vip 2.svip
     */
    private Integer roleId;

    /**
     * 用户ID,方便查找
     */
    private String userId;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 微信session key
     */
    private String sessionKey;

    /**
     * 性别  0-男、1-女
     */
    private Integer gender;

    /**
     * 所在国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 语种
     */
    private String wxLanguage;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 我的积分
     */
    private Long integral;

    /**
     * 分享的用户ID
     */
    private Long shareUserId;

    /**
     * 0可用, 1 禁用, 2 注销
     */
    private Integer currentStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    private Integer isDel;

    /**
     * 乐观锁
     */
    private Long lockVersion;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

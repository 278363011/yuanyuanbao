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
public class SysApi extends Model<SysApi> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限码
     */
    private String code;

    /**
     * api url
     */
    private String url;

    /**
     * 请求方式-get post
     */
    private String requestType;

    /**
     * 菜单权限标识
     */
    private String permFlag;

    /**
     * api描述
     */
    private String desc;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 0--正常 1--删除
     */
    private Integer isDel;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

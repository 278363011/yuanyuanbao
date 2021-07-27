package com.yuanbaobao.mini.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author codebaobao
 * @since 2021-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Carousel extends Model<Carousel> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 轮播图片名称
     */
    private String cName;

    /**
     * 图片地址
     */
    private String cImgUrl;

    /**
     * 图片顺序
     */
    private Integer cOrder;

    /**
     * 跳转地址
     */
    private String redictUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

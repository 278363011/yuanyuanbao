package com.codebaobao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author codebaobao
 * @since 2020-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SchoolArea extends Model<SchoolArea> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 校区名
     */
    private String areaName;

    /**
     * 地址
     */
    private String address;

    /**
     * 描述扩展字段
     */
    private String desction;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

package com.yuanbaobao.mini.model;

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
 * @since 2021-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DesignCategoryMiddle extends Model<DesignCategoryMiddle> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目ID
     */
    private Long designId;

    /**
     * 分类ID
     */
    private Long cateId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

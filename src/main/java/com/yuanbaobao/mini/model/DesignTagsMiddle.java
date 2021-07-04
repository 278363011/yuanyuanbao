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
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DesignTagsMiddle extends Model<DesignTagsMiddle> {

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
     * 标签ID
     */
    private Long tagId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

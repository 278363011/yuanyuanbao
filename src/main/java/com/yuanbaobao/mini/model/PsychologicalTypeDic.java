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
 * @since 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PsychologicalTypeDic extends Model<PsychologicalTypeDic> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 预约类型名字
     */
    private String name;

    /**
     * 描述
     */
    private String desction;

    /**
     * 优先级
     */
    private Integer orders;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

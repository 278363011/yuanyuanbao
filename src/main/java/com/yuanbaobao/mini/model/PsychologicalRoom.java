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
 * @since 2020-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PsychologicalRoom extends Model<PsychologicalRoom> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 预约科室名字
     */
    private String roomName;

    /**
     * 预约科室描述
     */
    private String roomDesc;

    /**
     * 所属校区
     */
    private Integer areaId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

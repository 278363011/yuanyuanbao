package com.codebaobao.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author codebaobao
 * @since 2021-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DoctorWeek extends Model<DoctorWeek> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer doctorId;

    private String weekday;

    private String time;

    private String workStatus;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}

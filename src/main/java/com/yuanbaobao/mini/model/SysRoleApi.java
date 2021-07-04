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
 * @since 2021-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleApi extends Model<SysRoleApi> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * APIID
     */
    private Long apiId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

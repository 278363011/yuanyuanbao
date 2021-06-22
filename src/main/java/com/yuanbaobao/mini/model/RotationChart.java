package com.codebaobao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class RotationChart extends Model<RotationChart> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图片地址
     */
    @TableField("picUrl")
    private String picUrl;

    /**
     * 消息id 暂时扩展
     */
    @TableField("messageId")
    private Integer messageId;

    /**
     * 优先级
     */
    private Integer orders;

    /**
     * 描述
     */
    private String desction;

    /**
     * 状态0下线 1上线
     */
    @TableField("statesDesc")
    private Integer statesDesc;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

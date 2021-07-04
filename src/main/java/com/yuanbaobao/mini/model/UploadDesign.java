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
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UploadDesign extends Model<UploadDesign> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上传者
     */
    private Long userId;

    /**
     * 项目名称
     */
    private String designName;

    /**
     * 开发语言
     */
    private String designLanguage;

    /**
     * 下载地址
     */
    private String uploadUrl;

    /**
     * 联系方式
     */
    private String concactMethod;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    private Integer isDel;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

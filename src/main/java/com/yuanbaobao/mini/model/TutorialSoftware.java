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
public class TutorialSoftware extends Model<TutorialSoftware> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 教程ID
     */
    private Long tutorialId;

    /**
     * 软件名称
     */
    private String sofewareName;

    /**
     * 软件下载地址
     */
    private String sofewareDownloadUrl;

    /**
     * 更新时间
     */
    private LocalDateTime createTime;

    /**
     * 逻辑删除
     */
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

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
public class Tutorial extends Model<Tutorial> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 教程名称
     */
    private String tutorialName;

    /**
     * 教程描述
     */
    private String tutorialDesc;

    /**
     * 教程内容
     */
    private String tutorialContent;

    /**
     * 用户浏览量
     */
    private Integer userViewNum;

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

    /**
     * 乐观锁
     */
    private Long lockVersion;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

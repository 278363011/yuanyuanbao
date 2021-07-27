package com.yuanbaobao.mini.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class ProjectDesign extends Model<ProjectDesign> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 项目标题
     */
    private String titleName;

    /**
     * 项目描述
     */
    private String titleDesc;

    /**
     * 源码详情html
     */
    private String titleContent;

    /**
     * 所需积分
     */
    private Integer integration;

    /**
     * 用户浏览量
     */
    private Integer userViewNum;

    /**
     * 分享量
     */
    private Integer shareNum;

    /**
     * 兑换数
     */
    private Integer exchangeNum;

    /**
     * 设计资源下载地址
     */
    private String downloadUrl;

    /**
     * 0 待发布,1 上线,2 下线
     */
    private Integer currentStatus;

    /**
     * 0 非热门 1热门
     */
    private Integer isHot;

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

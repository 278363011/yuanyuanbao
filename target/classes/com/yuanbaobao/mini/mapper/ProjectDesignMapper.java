package com.yuanbaobao.mini.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuanbaobao.mini.model.ProjectDesign;
import com.yuanbaobao.mini.vo.ProjectDesignRequestVo;
import com.yuanbaobao.mini.vo.ProjectDesignResponseVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author codebaobao
 * @since 2021-07-03
 */
@Mapper
public interface ProjectDesignMapper extends BaseMapper<ProjectDesign> {

    IPage<ProjectDesignResponseVo> getDesignList(IPage<ProjectDesignResponseVo> page, ProjectDesignRequestVo pdrv);

    IPage<ProjectDesignResponseVo> getSelectedDesignList(IPage<ProjectDesignResponseVo> page, ProjectDesignRequestVo pdrv);
}

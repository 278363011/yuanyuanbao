package com.codebaobao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codebaobao.model.Doctor;
import com.codebaobao.modeldo.DoctorVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-28
 */
@Mapper
public interface DoctorMapper extends BaseMapper<Doctor> {
    IPage<DoctorVo> getDoctorDetailInfo(IPage<DoctorVo> page);
}

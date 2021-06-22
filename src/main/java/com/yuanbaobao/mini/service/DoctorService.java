package com.codebaobao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codebaobao.model.Doctor;
import com.codebaobao.modeldo.DoctorVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-28
 */
public interface DoctorService extends IService<Doctor> {

    IPage<DoctorVo> getDoctorDetailInfo(IPage<DoctorVo> page);

}

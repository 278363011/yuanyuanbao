package com.codebaobao.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codebaobao.mapper.DoctorMapper;
import com.codebaobao.model.Doctor;
import com.codebaobao.modeldo.DoctorVo;
import com.codebaobao.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-28
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {

    @Autowired
    DoctorMapper doctorMapper;

    @Override
    public IPage<DoctorVo> getDoctorDetailInfo(final IPage<DoctorVo> page) {
        return this.doctorMapper.getDoctorDetailInfo(page);
    }
}

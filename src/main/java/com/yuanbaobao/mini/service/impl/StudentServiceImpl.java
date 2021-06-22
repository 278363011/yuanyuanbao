package com.codebaobao.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codebaobao.mapper.StudentMapper;
import com.codebaobao.model.Student;
import com.codebaobao.modeldo.StudentVo;
import com.codebaobao.service.StudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public IPage<StudentVo> getStudentDetailInfo(final IPage<StudentVo> page) {
        return this.studentMapper.getStudentDetailInfo(page);
    }
}

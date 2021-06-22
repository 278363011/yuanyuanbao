package com.codebaobao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codebaobao.model.Student;
import com.codebaobao.modeldo.StudentVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-28
 */
public interface StudentService extends IService<Student> {
    IPage<StudentVo> getStudentDetailInfo(IPage<StudentVo> page);
}

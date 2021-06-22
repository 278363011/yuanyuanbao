package com.codebaobao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codebaobao.model.Student;
import com.codebaobao.modeldo.StudentVo;
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
public interface StudentMapper extends BaseMapper<Student> {
    IPage<StudentVo> getStudentDetailInfo(IPage<StudentVo> page);
}

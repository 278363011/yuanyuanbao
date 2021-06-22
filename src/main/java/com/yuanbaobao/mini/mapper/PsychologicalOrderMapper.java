package com.codebaobao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codebaobao.model.PsychologicalOrder;
import com.codebaobao.modeldo.DoctorVo;
import com.codebaobao.modeldo.OrderVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-28
 */
@Mapper
public interface PsychologicalOrderMapper extends BaseMapper<PsychologicalOrder> {

    IPage<OrderVo> getOrderByKeyWord(IPage<OrderVo> page,OrderVo ordervo);

}

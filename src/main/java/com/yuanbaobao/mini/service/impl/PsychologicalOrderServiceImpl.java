package com.codebaobao.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codebaobao.model.PsychologicalOrder;
import com.codebaobao.mapper.PsychologicalOrderMapper;
import com.codebaobao.modeldo.DoctorVo;
import com.codebaobao.modeldo.OrderVo;
import com.codebaobao.service.PsychologicalOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-28
 */
@Service
public class PsychologicalOrderServiceImpl extends ServiceImpl<PsychologicalOrderMapper, PsychologicalOrder> implements PsychologicalOrderService {

    @Autowired
    PsychologicalOrderMapper psychologicalOrderMapper;

    @Override
    public IPage<OrderVo> getOrderByKeyWord(IPage<OrderVo> page, OrderVo ordervo) {
        return psychologicalOrderMapper.getOrderByKeyWord(page,ordervo);
    }
}

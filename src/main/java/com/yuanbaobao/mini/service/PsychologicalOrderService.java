package com.codebaobao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codebaobao.model.PsychologicalOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codebaobao.modeldo.DoctorVo;
import com.codebaobao.modeldo.OrderVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-28
 */
public interface PsychologicalOrderService extends IService<PsychologicalOrder> {
    IPage<OrderVo> getOrderByKeyWord(IPage<OrderVo> page, OrderVo ordervo);
}

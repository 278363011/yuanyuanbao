package com.codebaobao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codebaobao.model.PsychologicalRoom;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-28
 */
public interface PsychologicalRoomService extends IService<PsychologicalRoom> {


    IPage<Map<String, Object>> getAllRoomAndAreaInfo(IPage<Map<String, Object>> page);
}

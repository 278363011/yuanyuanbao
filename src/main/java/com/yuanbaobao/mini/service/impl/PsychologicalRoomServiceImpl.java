package com.codebaobao.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codebaobao.mapper.PsychologicalRoomMapper;
import com.codebaobao.model.PsychologicalRoom;
import com.codebaobao.service.PsychologicalRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-28
 */
@Service
public class PsychologicalRoomServiceImpl extends ServiceImpl<PsychologicalRoomMapper, PsychologicalRoom> implements PsychologicalRoomService {

    @Autowired
    PsychologicalRoomMapper psychologicalRoomMapper;

    @Override
    public IPage<Map<String, Object>> getAllRoomAndAreaInfo(final IPage<Map<String, Object>> page) {
        return this.psychologicalRoomMapper.getAllRoomAndAreaInfo(page);
    }
}

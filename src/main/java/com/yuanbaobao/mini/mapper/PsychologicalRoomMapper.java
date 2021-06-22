package com.codebaobao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codebaobao.model.PsychologicalRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-28
 */
@Mapper
public interface PsychologicalRoomMapper extends BaseMapper<PsychologicalRoom> {

    IPage<Map<String, Object>> getAllRoomAndAreaInfo(IPage<Map<String, Object>> page);
}

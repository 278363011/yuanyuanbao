package com.codebaobao.mapstruct;

import com.codebaobao.model.Doctor;
import com.codebaobao.model.PsychologicalOrder;
import com.codebaobao.modeldo.DoctorVo;
import com.codebaobao.modeldo.OrderVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderConvert {
    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "orderCode", target = "orderCode"),
            @Mapping(source = "orderType", target = "orderType"),
            @Mapping(source = "orderDate", target = "orderDate"),
            @Mapping(source = "orderStarttime", target = "orderStarttime"),
            @Mapping(source = "orderEndtime", target = "orderEndtime"),
            @Mapping(source = "stuMessage", target = "stuMessage"),
            @Mapping(source = "doctorMessage", target = "doctorMessage"),
            @Mapping(source = "orderStatus", target = "orderStatus"),
            @Mapping(source = "rateRank", target = "rateRank"),
            @Mapping(source = "doctorId", target = "doctorId"),
            @Mapping(source = "studentId", target = "studentId"),
            @Mapping(source = "areaId", target = "areaId"),
            @Mapping(source = "roomId", target = "roomId"),
            @Mapping(source = "createTime", target = "createTime"),
            @Mapping(source = "updateTime", target = "updateTime"),
            @Mapping(source = "confict", target = "confict"),
            @Mapping(source = "orderLevel", target = "orderLevel"),
            @Mapping(source = "orderGrade", target = "orderGrade"),
            @Mapping(source = "delFlag", target = "delFlag"),
    })
    PsychologicalOrder domain2dto(OrderVo orderVo);

    List<PsychologicalOrder> domain2dto(List<OrderVo> orderVoList);
}

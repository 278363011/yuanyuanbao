//package com.codebaobao.mapstruct;
//
//import com.codebaobao.model.Doctor;
//import com.codebaobao.modeldo.DoctorVo;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//@Mapper
//public interface DoctorConvert {
//    DoctorConvert INSTANCE = Mappers.getMapper(DoctorConvert.class);
//
//    @Mappings({
//            @Mapping(source = "id", target = "id"),
//            @Mapping(source = "doctorCode", target = "doctorCode"),
//            @Mapping(source = "doctorName", target = "doctorName"),
//            @Mapping(source = "doctorDesc", target = "doctorDesc"),
//            @Mapping(source = "roleId", target = "roleId"),
//            @Mapping(source = "telPhone", target = "telPhone"),
//            @Mapping(source = "sex", target = "sex"),
//            @Mapping(source = "age", target = "age"),
//            @Mapping(source = "areaId", target = "areaId"),
//            @Mapping(source = "accountName", target = "accountName"),
//            @Mapping(source = "imgsUrl", target = "imgsUrl"),
//            @Mapping(source = "pwd", target = "pwd"),
//            @Mapping(source = "salt", target = "salt"),
//            @Mapping(source = "createTime", target = "createTime"),
//            @Mapping(source = "updateTime", target = "updateTime"),
//            @Mapping(source = "delFlag", target = "delFlag"),
//    })
//    DoctorVo domain2dto(Doctor docotor);
//
//    List<DoctorVo> domain2dto(List<Doctor> docotor);
//}

//package com.codebaobao.mapstruct;
//
//import com.codebaobao.model.Student;
//import com.codebaobao.modeldo.StudentVo;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//@Mapper
//public interface StudentConvert {
//    StudentConvert INSTANCE = Mappers.getMapper(StudentConvert.class);
//
//    @Mappings({
//            @Mapping(source = "id", target = "id"),
//            @Mapping(source = "stuNo", target = "stuNo"),
//            @Mapping(source = "name", target = "name"),
//            @Mapping(source = "roleId", target = "roleId"),
//            @Mapping(source = "sex", target = "sex"),
//            @Mapping(source = "age", target = "age"),
//            @Mapping(source = "collage", target = "collage"),
//            @Mapping(source = "department", target = "department"),
//            @Mapping(source = "profess", target = "profess"),
//            @Mapping(source = "grade", target = "grade"),
//            @Mapping(source = "clazz", target = "clazz"),
//            @Mapping(source = "idCard", target = "idCard"),
//            @Mapping(source = "telPhone", target = "telPhone"),
//            @Mapping(source = "email", target = "email"),
//            @Mapping(source = "areaId", target = "areaId"),
//            @Mapping(source = "accountStatus", target = "accountStatus"),
//            @Mapping(source = "accountName", target = "accountName"),
//            @Mapping(source = "pwd", target = "pwd"),
//            @Mapping(source = "salt", target = "salt"),
//            @Mapping(source = "createTime", target = "createTime"),
//            @Mapping(source = "updateTime", target = "updateTime"),
//            @Mapping(source = "delFlag", target = "delFlag"),
//    })
//    StudentVo domain2dto(Student student);
//
//    List<StudentVo> domain2dto(List<Student> student);
//}

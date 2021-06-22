package com.codebaobao.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebaobao.constants.Constant;
import com.codebaobao.mapper.PsychologicalRoomMapper;
import com.codebaobao.model.Doctor;
import com.codebaobao.model.PsychologicalRoom;
import com.codebaobao.modeldo.PageVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.PsychologicalRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/admin/room")
public class SchoolRoomController {

    @Autowired
    PsychologicalRoomService psychologicalRoomService;
    @Autowired
    PsychologicalRoomMapper psychologicalRoomMapper;

    @RequestMapping("/getRoomByAreaId")
    public Result<Object> getRoomByAreaId(@Valid @RequestBody final PsychologicalRoom psychologicalRoom) {
        try {
            final List<PsychologicalRoom> list = this.psychologicalRoomService.list(new QueryWrapper<PsychologicalRoom>().eq("area_id", psychologicalRoom.getAreaId()));
            if (Objects.isNull(list) || list.isEmpty()) {
                return Result.success(null);
            } else {
                return Result.success(list);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    //增
    @RequestMapping("/add")
    public Result<String> addPsychologicalRoom(@Valid @RequestBody final PsychologicalRoom psychologicalRoom) {
        try {
            if (this.psychologicalRoomService.save(psychologicalRoom)) {
                return Result.success(Constant.INSERT_SUCCESS);
            } else {
                return Result.error(CodeMsg.create(10000, Constant.INSERT_ERROR));
            }
        } catch (final Exception e) {
            log.error(Constant.INSERT_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.INSERT_EXCEPTION));
        }
    }

    //删
    @RequestMapping("/delete")
    public Result<String> deletePsychologicalRoom(@Valid @RequestBody final PsychologicalRoom psychologicalRooms) {
        try {
            final PsychologicalRoom psychologicalRoom = this.psychologicalRoomService.getById(psychologicalRooms.getId());
            if (Objects.isNull(psychologicalRoom)) {
                return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
            } else {
                if (this.psychologicalRoomService.removeById(psychologicalRooms.getId())) {
                    return Result.success(Constant.DELETE_SUCCESS);
                } else {
                    return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
                }
            }
        } catch (final Exception e) {
            log.error(Constant.DELETE_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.DELETE_EXCEPTION));
        }
    }

    //改
    @RequestMapping("/update")
    public Result<String> updatePsychologicalRoom(@Valid @RequestBody final PsychologicalRoom psychologicalRoom) {
        try {
            final PsychologicalRoom sqlPsychologicalRoom = this.psychologicalRoomService.getById(psychologicalRoom.getId());
            if (Objects.isNull(sqlPsychologicalRoom)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                if (this.psychologicalRoomService.updateById(psychologicalRoom)) {
                    return Result.success(Constant.UPDATE_SUCCESS);
                } else {
                    return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
                }
            }
        } catch (final Exception e) {
            log.error(Constant.UPDATE_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.UPDATE_EXCEPTION));
        }
    }

    //查
    @RequestMapping("/queryById")
    public Result<Object> selectPsychologicalRoomById(@Valid @RequestBody final PsychologicalRoom psychologicalRooms) {
        try {
            final PsychologicalRoom psychologicalRoom = this.psychologicalRoomService.getById(psychologicalRooms.getId());
            if (Objects.isNull(psychologicalRoom)) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(psychologicalRoom);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    //分页查
    @RequestMapping("/queryByPage")
    public Result<Object> selectAllPsychologicalRoomByPage(@Valid @RequestBody final PageVo pageVo) {
        try {
            final IPage<PsychologicalRoom> psychologicalRoomPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.psychologicalRoomMapper.selectPage(psychologicalRoomPage, null);
            final List<PsychologicalRoom> psychologicalRoomList = psychologicalRoomPage.getRecords();
            if (Objects.isNull(psychologicalRoomList) || psychologicalRoomList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(psychologicalRoomPage);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


    @RequestMapping("/fuzzyQuery")
    public Result<Object> fuzzyQuery(@Valid @RequestBody final PageVo pageVo) {
        try {
            if(pageVo.getPageNow() == 0){
                pageVo.setPageNow(1);
            }
            if(pageVo.getPageSize()==0){
                pageVo.setPageSize(10);
            }
            final IPage<PsychologicalRoom> psychologicalRoomPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.psychologicalRoomMapper.selectPage(psychologicalRoomPage, new QueryWrapper<PsychologicalRoom>()
                    .like("room_name", pageVo.getSearch())
                    .or().like("room_desc", pageVo.getSearch())
            );

            if (psychologicalRoomPage.getRecords().isEmpty()) {
                return Result.success("查询数据为空");
            } else {
                return Result.success(psychologicalRoomPage);
            }

        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


    @RequestMapping("/getAllByPage")
    public Result<Object> getAllRoomAndAreaInfo(@Valid @RequestBody final PageVo pageVo) {
        final IPage<Map<String, Object>> psychologicalRoomPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
        final IPage<Map<String, Object>> allRoomAndAreaInfo = this.psychologicalRoomService.getAllRoomAndAreaInfo(psychologicalRoomPage);

        if (allRoomAndAreaInfo.getRecords().isEmpty() || Objects.isNull(allRoomAndAreaInfo)) {
            return Result.error(CodeMsg.create(1000, "数据为空"));
        } else {
            return Result.success(allRoomAndAreaInfo);
        }
    }
}

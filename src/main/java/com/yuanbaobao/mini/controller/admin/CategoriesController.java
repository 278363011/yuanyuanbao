package com.codebaobao.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebaobao.constants.Constant;
import com.codebaobao.mapper.PsychologicalTypeDicMapper;
import com.codebaobao.model.PsychologicalTypeDic;
import com.codebaobao.model.SchoolArea;
import com.codebaobao.modeldo.PageVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.PsychologicalTypeDicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/admin/categories")
public class CategoriesController {

    @Autowired
    PsychologicalTypeDicService psychologicalTypeDicService;
    @Autowired
    PsychologicalTypeDicMapper psychologicalTypeDicMapper;



    //增
    @RequestMapping("/add")
    public Result<String> addCategories(@Valid @RequestBody final PsychologicalTypeDic psychologicalTypeDic) {
        try {

            if (this.psychologicalTypeDicService.save(psychologicalTypeDic)) {
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
    public Result<String> deleteCategories(@Valid @RequestBody final PsychologicalTypeDic psychologicalTypeDic) {
        try {
            final PsychologicalTypeDic sqlCategories = this.psychologicalTypeDicService.getById(psychologicalTypeDic.getId());
            if (Objects.isNull(sqlCategories)) {
                return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
            } else {
                if (this.psychologicalTypeDicService.removeById(psychologicalTypeDic.getId())) {
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
    public Result<String> updateCategories(@Valid @RequestBody final PsychologicalTypeDic psychologicalTypeDic) {
        try {
            final PsychologicalTypeDic sqlCategories = this.psychologicalTypeDicService.getById(psychologicalTypeDic.getId());
            if (Objects.isNull(sqlCategories)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                if (this.psychologicalTypeDicService.updateById(psychologicalTypeDic)) {
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
    public Result<Object> selectCategoriesById(@Valid @RequestBody final PsychologicalTypeDic psychologicalTypeDic) {
        try {
            final PsychologicalTypeDic sqlCategories = this.psychologicalTypeDicService.getById(psychologicalTypeDic.getId());
            if (Objects.isNull(sqlCategories)) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(sqlCategories);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    //分页查
    @RequestMapping("/queryByPage")
    public Result<Object> selectAllCategoriesByPage(@Valid @RequestBody final PageVo pageVo) {
        try {
            final IPage<PsychologicalTypeDic> categoriePage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.psychologicalTypeDicMapper.selectPage(categoriePage, new QueryWrapper<PsychologicalTypeDic>().orderByDesc("orders"));
            final List<PsychologicalTypeDic> categorieList = categoriePage.getRecords();
            if (Objects.isNull(categorieList) || categorieList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(categoriePage);
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
            final IPage<PsychologicalTypeDic> categoriePage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.psychologicalTypeDicMapper.selectPage(categoriePage, new QueryWrapper<PsychologicalTypeDic>()
                    .like("name", pageVo.getSearch())
                    .or().like("desction", pageVo.getSearch()
                    ).orderByDesc("orders")
            );

            if (categoriePage.getRecords().isEmpty()) {
                return Result.success("查询数据为空");
            } else {
                return Result.success(categoriePage);
            }

        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


    @RequestMapping("/selectAll")
    public Result<Object> selectAll() {
        try {
            final List<PsychologicalTypeDic> list = this.psychologicalTypeDicService.list();
            if (Objects.isNull(list) || list.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(list);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


}

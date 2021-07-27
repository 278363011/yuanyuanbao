package com.yuanbaobao.mini.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuanbaobao.mini.model.Carousel;
import com.yuanbaobao.mini.result.Result;
import com.yuanbaobao.mini.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/carousel")
public class CarouselController {

    @Autowired
    CarouselService carouselService;

    /**
     * 查询轮播图
     */
    @PostMapping(value = "/query")
    public Result<List<Carousel>> carouselQuery() {
        List<Carousel> carouselList = carouselService.list(new QueryWrapper<Carousel>().orderByAsc("c_order"));
        return Result.success(carouselList);
    }

    /**
     * 增加轮播图
     */
    @PostMapping(value = "/addOrUpdate")
    @Transactional
    public Result<Boolean> carouselAddOrUpdate(@RequestBody List<Carousel> carouselList) {
        return Result.success(carouselService.saveOrUpdateBatch(carouselList));
    }

    /**
     * 增加轮播图
     */
    @PostMapping(value = "/delete")
    @Transactional
    public Result<Boolean> carouselDelete(@RequestBody List<Long> ids) {
        return Result.success(carouselService.removeByIds(ids));
    }


}

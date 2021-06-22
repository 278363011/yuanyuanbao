package com.codebaobao.modeldo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Accessors
public class PageVo extends BaseVo {

    public PageVo() {
    }

    public PageVo(final int pageNow, final int pageSize, final String search) {
        this.pageNow = pageNow;
        this.pageSize = pageSize;
        this.search = search;
    }

    @NotNull(message = "pageNow 不能为空")
    private int pageNow;
    @NotNull(message = "pageSize 不能为空")
    private int pageSize;
    private String search;
}

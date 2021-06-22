package com.codebaobao.modeldo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Accessors
public class FuzzyQueryVo extends BaseVo {

    public FuzzyQueryVo() {
    }

    public FuzzyQueryVo(final String fuzzyValue) {
        this.search = fuzzyValue;
    }

    @NotNull(message = "pageNow 不能为空")
    private String search;
}

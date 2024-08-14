package com.kcm.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageSpec {

    private Long PageNum;
    private Long PageSize;


    public PageSpec(Long pageNum) {
        PageNum = pageNum;
        PageSize = 3L;
    }

    public Long getStartNum(){
        return (this.PageNum - 1) * this.PageSize;
    }
}

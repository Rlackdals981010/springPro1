package com.kcm.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {

    private Long pageNum;
    private Long pageSize;

    public Page(Long pageNum) {
        this.pageNum = pageNum;
        this.pageSize = 5L;
    }

    public Long getPageStart(){
        return (this.pageNum-1)*pageSize;
    }
}

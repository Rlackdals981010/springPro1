package com.kcm.demo.dto;

import com.kcm.demo.entity.Page;
import lombok.Getter;

import java.sql.Date;

@Getter
public class PageResponseDto {

    private Long eventId;
    private String todo;
    private String manId;
    private Date createDay;
    private Date updateDay;

    private String name;


    public PageResponseDto(Page page) {
        this.eventId = page.getEventId();
        this.todo = page.getTodo();
        this.manId = page.getManId();
        this.createDay = page.getCreateDay();
        this.updateDay = page.getUpdateDay();
        this.name = page.getName();
    }
}

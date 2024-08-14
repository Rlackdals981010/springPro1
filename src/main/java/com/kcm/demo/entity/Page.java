package com.kcm.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class Page {

    private Long eventId;
    private String todo;
    private String manId;
    private Date createDay;
    private Date updateDay;

    private String name;

    public Page(Event event, Manager manager) {

        this.eventId = event.getEventId();
        this.todo = event.getTodo();
        this.manId = event.getManId();
        this.createDay = event.getCreateDay();
        this.updateDay = event.getUpdateDay();

        this.name = manager.getName();
    }


}

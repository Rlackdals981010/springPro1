package com.kcm.demo.dto;

import com.kcm.demo.entity.Event;

import java.util.Date;

public class EventResponseDto {
    private Long event_id;
    private String todo;
    private String name;
    private String password;
    private Date update_day;

    public EventResponseDto(Event event) {
        this.event_id = event.getEvent_id();
        this.todo = event.getTodo();
        this.name = event.getName();
        this.password = event.getPassword();
        this.update_day = event.getUpdate_day();
    }
}

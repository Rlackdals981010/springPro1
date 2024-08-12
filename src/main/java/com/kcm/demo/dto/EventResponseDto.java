package com.kcm.demo.dto;

import com.kcm.demo.entity.Event;
import lombok.Getter;

import java.util.Date;


@Getter
public class EventResponseDto {
    private Long eventId;
    private String todo;
    private String name;
    private String password;
    private Date createDay;
    private Date updateDay;

    public EventResponseDto(Event event) {
        this.eventId = event.getEventId();
        this.todo = event.getTodo();
        this.name = event.getName();
        //this.password = event.getPassword();
        this.createDay = event.getCreateDay();
        this.updateDay = event.getUpdateDay();
    }
}

package com.kcm.demo.dto;

import com.kcm.demo.entity.Event;
import lombok.Getter;

import java.util.Date;

//서버가 제공하는 데이터를 여기를 통해 Client에게 갈 수 있도록 변환
@Getter
public class EventResponseDto {

    private Long eventId;
    private String todo;
    private String name;
    private Date createDay;
    private Date updateDay;

    public EventResponseDto(Event event) {
        this.eventId = event.getEventId();
        this.todo = event.getTodo();
        this.name = event.getName();
        this.createDay = event.getCreateDay();
        this.updateDay = event.getUpdateDay();
    }
}

package com.kcm.demo.entity;

import com.kcm.demo.dto.EventRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Event {
    private Long eventId;
    private String todo;
    private String name;
    private String password;
    private Date createDay;
    private Date updateDay;

    public Event(EventRequestDto eventRequestDto) {
        this.todo = eventRequestDto.getTodo();
        this.name = eventRequestDto.getName();
        this.password = eventRequestDto.getPassword();
        this.createDay = eventRequestDto.getCreateDay();
        this.updateDay = eventRequestDto.getUpdateDay();
    }
}

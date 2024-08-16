package com.kcm.demo.entity;

import com.kcm.demo.dto.EventRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class Event {

    private Long eventId;
    private String todo;
    private String manId;
    private String password;
    private Date createDay;
    private Date updateDay;

    public Event(EventRequestDto eventRequestDto) {
        this.todo = eventRequestDto.getTodo();
        this.manId = eventRequestDto.getManId();
        this.password = eventRequestDto.getPassword();
        this.createDay = getNow();
        this.updateDay = getNow();
    }


    private Date getNow(){
        return new Date(System.currentTimeMillis());
    }
}

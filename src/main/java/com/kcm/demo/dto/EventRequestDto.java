package com.kcm.demo.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class EventRequestDto {
    private String todo;
    private String name;
    private String password;
    private Date update_day;
}

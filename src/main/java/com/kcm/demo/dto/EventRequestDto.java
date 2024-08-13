package com.kcm.demo.dto;

import lombok.Getter;

import java.util.Date;

//Client가 입력하는 데이터가 여기를 통해서 매핑될꺼임.
@Getter
public class EventRequestDto {
    private String todo;
    private String manId;
    private String password;
    private Date createDay;
    private Date updateDay;
}

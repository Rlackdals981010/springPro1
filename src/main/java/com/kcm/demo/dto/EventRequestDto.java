package com.kcm.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import jakarta.validation.constraints.Size;
import java.util.Date;

//Client가 입력하는 데이터가 여기를 통해서 매핑될꺼임.
@Getter
public class EventRequestDto {

    @NotNull(message = "Todo cannot be null")
    @Size(max =200)
    private String todo;
    private String manId;

    @NotNull(message = "password cannot be null")
    private String password;
    private Date createDay;
    private Date updateDay;
}

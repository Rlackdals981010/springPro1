package com.kcm.demo.dto;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

//Client가 입력하는 데이터가 여기를 통해서 매핑될꺼임.
@Getter
public class EventRequestDto {

    @Valid
    @Size(max =200)
    private String todo;
    private String manId;

    @Valid
    private String password;
    private Date createDay;
    private Date updateDay;
}

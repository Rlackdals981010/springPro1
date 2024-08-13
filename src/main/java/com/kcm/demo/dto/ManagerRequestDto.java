package com.kcm.demo.dto;

import lombok.Getter;

import java.sql.Date;

@Getter
public class ManagerRequestDto {
    private String manId;
    private String name;
    private String email;
    private Date createDay;
    private Date updateDay;

}

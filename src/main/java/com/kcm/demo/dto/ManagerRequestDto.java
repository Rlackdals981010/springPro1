package com.kcm.demo.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

import java.sql.Date;

@Getter
public class ManagerRequestDto {
    private String manId;
    private String name;
    @Email
    private String email;
    private Date createDay;
    private Date updateDay;

}

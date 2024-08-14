package com.kcm.demo.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
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

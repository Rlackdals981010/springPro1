package com.kcm.demo.dto;

import com.kcm.demo.entity.Manager;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
public class ManagerResponseDto {
    private String manId;
    private String name;
    private String email;
    private Date createDay;
    private Date updateDay;


    public ManagerResponseDto(Manager manager) {
        this.manId = manager.getManId();
        this.name = manager.getName();
        this.email = manager.getEmail();
        this.createDay = manager.getCreateDay();
        this.updateDay = manager.getUpdateDay();
    }
}

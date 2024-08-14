package com.kcm.demo.entity;

import com.kcm.demo.dto.ManagerRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.Email;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class Manager {

    private String manId;
    private String name;

    @Email
    private String email;
    private Date createDay;
    private Date updateDay;


    public Manager(ManagerRequestDto managerRequestDto) {
        this.manId = managerRequestDto.getManId();
        this.name = managerRequestDto.getName();
        this.email = managerRequestDto.getEmail();
        this.createDay =  getNow();
        this.updateDay =  getNow();
    }

    private Date getNow(){
        return new Date(System.currentTimeMillis());
    }
}

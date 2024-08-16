package com.kcm.demo.controller;


import com.kcm.demo.dto.ManagerRequestDto;
import com.kcm.demo.dto.ManagerResponseDto;
import com.kcm.demo.entity.Manager;
import com.kcm.demo.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managers")
@Validated
public class ManagerController {

    private ManagerService managerService;
    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping()
    public ManagerResponseDto createManager(@Valid @RequestBody ManagerRequestDto managerRequestDto) {
        return managerService.createManager(managerRequestDto);
    }

    @GetMapping("/{manId}")
    public ManagerResponseDto selectManager(@PathVariable String manId){
        return managerService.selectManager(manId);
    }

    @GetMapping()
    public List<Manager> selectManagers(){
        return managerService.selectManagers();
    }

    @PutMapping("/{manId}")
    public ManagerResponseDto updateManager(@PathVariable String manId, @RequestBody ManagerRequestDto managerRequestDto){
        return managerService.updateManager(manId, managerRequestDto);
    }

    @DeleteMapping("/{manId}")
    public String deleteManager(@PathVariable String manId) {
        return managerService.deleteManager(manId);
    }

}

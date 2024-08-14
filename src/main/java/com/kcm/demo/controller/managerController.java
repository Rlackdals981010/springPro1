package com.kcm.demo.controller;


import com.kcm.demo.dto.ManagerRequestDto;
import com.kcm.demo.dto.ManagerResponseDto;
import com.kcm.demo.entity.Manager;
import com.kcm.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managers")
public class managerController {

    private ManagerService managerService;
    @Autowired
    public managerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping()
    public ManagerResponseDto createManager(@RequestBody ManagerRequestDto managerRequestDto) {
        return managerService.createManager(managerRequestDto);
    }

    @GetMapping("/{manId}")
    public ManagerResponseDto selectManager(@PathVariable String manId){
        return managerService.selectManager(manId);
    }

    @GetMapping()
    public List<Manager> selectManagers(@RequestBody ManagerResponseDto managerResponseDto){
        return null;
    }

    @PutMapping("/{manid}")
    public ManagerResponseDto updateManager(@PathVariable Long manId, @RequestBody ManagerResponseDto managerResponseDto){
        return  null;
    }

    @DeleteMapping("/{manid}")
    public Long deleteManaget(@PathVariable Long manId) {
        return null;
    }

}

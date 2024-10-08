package com.kcm.demo.service;

import com.kcm.demo.dto.EventResponseDto;
import com.kcm.demo.dto.ManagerRequestDto;
import com.kcm.demo.dto.ManagerResponseDto;
import com.kcm.demo.entity.Manager;
import com.kcm.demo.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }


    public ManagerResponseDto createManager(ManagerRequestDto managerRequestDto) {
        Manager manager = new Manager(managerRequestDto);
        Manager saveManager = managerRepository.save(manager);
        return new ManagerResponseDto(saveManager);
    }

    public ManagerResponseDto selectManager(String manId) {
        Manager selectManager = managerRepository.findById(manId);
        if(selectManager!=null){
            return new ManagerResponseDto(selectManager);
        }
        else{
            throw new IllegalArgumentException("존재하지 않는 매니저 입니다.");
        }
    }

    public List<Manager> selectManagers() {
        List<Manager> managerList = managerRepository.findAllManager();
        if (managerList != null) {
            return managerList;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public ManagerResponseDto updateManager(String manId, ManagerRequestDto managerRequestDto) {
        Manager updateManager = managerRepository.findById(manId);
        if (updateManager != null) {
            return new ManagerResponseDto(managerRepository.updateById(manId,managerRequestDto));

        } else {
            throw new IllegalArgumentException("존재하지 않는 매니저 입니다.");
        }

    }

    public String deleteManager(String manId) {
        Manager deleteManager = managerRepository.findById(manId);
        if (deleteManager != null) {
            managerRepository.deleteById(manId);
            return manId;
        } else {
            throw new IllegalArgumentException("존재하지 않는 매니저 입니다.");
        }
    }
}

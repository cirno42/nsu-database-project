package ru.nsu.nikolotov.dbproject.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.nikolotov.dbproject.backend.entities.ServiceStaffEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.ServiceStaffPositionEntity;
import ru.nsu.nikolotov.dbproject.backend.services.ServiceStaffPositionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/servicestaffpositions")
public class ServiceStaffPositionController {
    @Autowired
    private ServiceStaffPositionService service;


    @GetMapping("getall")
    public List<ServiceStaffPositionEntity> getAll() {
        return service.getAll();
    }
}

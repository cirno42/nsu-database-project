package ru.nsu.nikolotov.dbproject.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nikolotov.dbproject.backend.dtos.CreateDoctorWorksAtHospitalDTO;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtHospitalFullInfoEntity;
import ru.nsu.nikolotov.dbproject.backend.services.DoctorWorksAtHospitalService;

import java.util.List;

@RestController
@RequestMapping("api/v1/doctorworksathospital")
public class DoctorWorksAtHospitalController {
    @Autowired
    private DoctorWorksAtHospitalService service;

    @GetMapping("getall")
    public List<DoctorWorksAtHospitalFullInfoEntity> getAll() {
        return service.getAll();
    }

    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DoctorWorksAtHospitalFullInfoEntity addDoctorToHospital(@RequestBody CreateDoctorWorksAtHospitalDTO dto) {
        return service.addDoctorToHospital(dto);
    }

    @GetMapping("history")
    public List<DoctorWorksAtHospitalFullInfoEntity> getHistoryOfWorkers() {
        return service.getHistoryOfWorkers();
    }

    @DeleteMapping
    public void fireDoctor(@RequestParam Integer id) {
        service.fireDoctor(id);
    }
}

package ru.nsu.nikolotov.dbproject.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nikolotov.dbproject.backend.dtos.CreateDoctorWorksAtHospitalDTO;
import ru.nsu.nikolotov.dbproject.backend.dtos.CreateDoctorWorksAtPolyclinicDTO;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtHospitalFullInfoEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtPolyclinicFullInfo;
import ru.nsu.nikolotov.dbproject.backend.repositories.DoctorWorksAtPolyclinicRepository;
import ru.nsu.nikolotov.dbproject.backend.services.DoctorWorksAtHospitalService;
import ru.nsu.nikolotov.dbproject.backend.services.DoctorWorksAtPolyclinicService;

import java.util.List;


@RestController
@RequestMapping("api/v1/doctorworksatpolyclinic")
public class DoctorWorksAtPolyclinicController {
    @Autowired
    private DoctorWorksAtPolyclinicService service;

    @GetMapping("getall")
    public List<DoctorWorksAtPolyclinicFullInfo> getAll() {
        return service.getAll();
    }

    @GetMapping("history")
    public List<DoctorWorksAtPolyclinicFullInfo> getHistoryOfWorkers() {
        return service.getHistoryOfWorkers();
    }

        @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DoctorWorksAtPolyclinicFullInfo addDoctorToHospital(@RequestBody CreateDoctorWorksAtPolyclinicDTO dto) {
        return service.addDoctorToHospital(dto);
    }

    @PutMapping(path = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DoctorWorksAtPolyclinicFullInfo update(@RequestBody CreateDoctorWorksAtPolyclinicDTO dto) {
        return service.addDoctorToHospital(dto);
    }

    @DeleteMapping
    public void fireDoctor(@RequestParam Integer id) {
        service.fireDoctor(id);
    }
}

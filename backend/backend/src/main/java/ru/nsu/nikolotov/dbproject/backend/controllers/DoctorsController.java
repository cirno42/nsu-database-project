package ru.nsu.nikolotov.dbproject.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nikolotov.dbproject.backend.dtos.DoctorDTO;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtHospitalEntity;
import ru.nsu.nikolotov.dbproject.backend.services.DoctorService;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorType;

import java.util.List;


@RestController
@RequestMapping("api/v1/doctors")
public class DoctorsController {
    @Autowired
    DoctorService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        var entity = DoctorDTO.DTOToEntity(doctorDTO);
        var created = service.create(entity);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }
        var dto = DoctorDTO.EntityToDTO(created);
        return ResponseEntity.ok(dto);

    }

    @GetMapping(path = "doctorworksathospital")
    public List<DoctorWorksAtHospitalEntity> getListOfDoctorsWorkingInHospitalWithSuchType(@RequestParam int hospitalId, @RequestParam String type) {
        DoctorType doctorType = DoctorType.fromString(type);
        if (doctorType == DoctorType.NONE) {
            return null;
        }
        return service.getListOfDoctorsWorkingInHospitalWithSuchType(hospitalId, doctorType);
    }

    @GetMapping(path = "doctorworksatpolyclinic")
    public List<DoctorWorksAtHospitalEntity> getListOfDoctorsWorkingInPolyclinicWithSuchType(@RequestParam int polyclinicId, @RequestParam String type) {
        DoctorType doctorType = DoctorType.fromString(type);
        if (doctorType == DoctorType.NONE) {
            return null;
        }
        return service.getListOfDoctorsWorkingInPolyclinicWithSuchType(polyclinicId, doctorType);
    }

}

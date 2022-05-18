package ru.nsu.nikolotov.dbproject.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nikolotov.dbproject.backend.dtos.DoctorDTO;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorExperienceEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtInstitutionEntity;
import ru.nsu.nikolotov.dbproject.backend.services.DoctorService;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorSciencePosition;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorScienceRank;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorType;
import ru.nsu.nikolotov.dbproject.backend.types.MedicineInstitutionType;

import java.util.ArrayList;
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

    @GetMapping(path = "doctorworks/info")
    public List<DoctorWorksAtInstitutionEntity> getListOfDoctorsWorkingInHospitalWithSuchType
            (@RequestParam(required = false) Integer instId, @RequestParam String doctorType, @RequestParam String institutionType) {
        DoctorType doctorT= DoctorType.fromString(doctorType);
        if (doctorT == DoctorType.NONE) {
            return null;
        }
        MedicineInstitutionType medicineInstitutionType = MedicineInstitutionType.fromString(institutionType);
        return service.getListOfDoctorsWorkingInInstitutionWithSuchType(instId, doctorT, medicineInstitutionType);
    }

    @GetMapping(path = "doctorworks/operationsinfo")
    public List<DoctorDTO> getInfoAboutDoctorOperations
            (@RequestParam Integer count,
             @RequestParam String institutionType,
             @RequestParam String doctorType,
             @RequestParam(required = false) Integer institutionId) {

        var doctorT = DoctorType.fromString(doctorType);
        var instT = MedicineInstitutionType.fromString(institutionType);


        var entities=  service.getListOfDoctorsWhoDoneMoreOperations(count, institutionId, doctorT, instT);
        List<DoctorDTO> dtoList = new ArrayList<>(entities.size());
        entities.forEach(el -> {
            el.setDoctorType(doctorT);
            dtoList.add(DoctorDTO.EntityToDTO(el));
        });
        return dtoList;
    }

    @GetMapping(path = "doctorworks/experinced")
    public List<DoctorExperienceEntity> getDoctorsWithExperienceMoreThanSpecified
            (@RequestParam(required = false) Integer institutionId,
             @RequestParam Integer experience,
             @RequestParam String doctorType,
             @RequestParam String institutionType) {
        return service.getListOfDoctorsWithMoreExperience(institutionId,
                experience,
                MedicineInstitutionType.fromString(institutionType),
                DoctorType.fromString(doctorType));


    }

    @GetMapping(path = "doctorranks")
    public List<DoctorDTO> getDoctorsWithSuchRankAndPosition
            (@RequestParam(required = false) Integer institutionId,
             @RequestParam String doctorType,
             @RequestParam String institutionType,
             @RequestParam(required = false) String rank,
             @RequestParam(required = false) String position) {

        DoctorType doctorT = DoctorType.fromString(doctorType);
        DoctorScienceRank doctorR = DoctorScienceRank.fromString(rank);
        DoctorSciencePosition doctorP = DoctorSciencePosition.fromString(position);
        var res= service.getDoctorsWithSuchRankAndPosition(institutionId,
                doctorT,
                MedicineInstitutionType.fromString(institutionType),
                doctorR,
                doctorP);
        var dtoList = new ArrayList<DoctorDTO>(res.size());
        res.forEach(el -> {
            el.setDoctorType(doctorT);
            el.setDoctorSciencePosition(doctorP);
            el.setDoctorScienceRank(doctorR);
            dtoList.add(DoctorDTO.EntityToDTO(el));
        });
        return dtoList;
    }

}

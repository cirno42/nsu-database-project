package ru.nsu.nikolotov.dbproject.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nikolotov.dbproject.backend.dtos.DoctorDTO;
import ru.nsu.nikolotov.dbproject.backend.entities.*;
import ru.nsu.nikolotov.dbproject.backend.services.DoctorService;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorSciencePosition;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorScienceRank;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorType;
import ru.nsu.nikolotov.dbproject.backend.types.MedicineInstitutionType;

import java.util.ArrayList;
import java.util.Date;
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

    @GetMapping(path = "doctorworks/info/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public DoctorTypeStatisticEntity getCountOfDoctorsWorkingInHospitalWithSuchType
            (@RequestParam(required = false) Integer instId, @RequestParam String doctorType, @RequestParam String institutionType) {
        DoctorType doctorT= DoctorType.fromString(doctorType);
        if (doctorT == DoctorType.NONE) {
            return null;
        }
        MedicineInstitutionType medicineInstitutionType = MedicineInstitutionType.fromString(institutionType);
        return service.getCountOfDoctorsWorkingInInstitutionWithSuchType(instId, doctorT,medicineInstitutionType);
        // return service.getListOfDoctorsWorkingInInstitutionWithSuchType(instId, doctorT, medicineInstitutionType);
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

    @GetMapping(path = "doctorworks/operationsinfo/count")
    public DoctorTypeStatisticEntity getCountOfDoctorOperations
            (@RequestParam Integer count,
             @RequestParam String institutionType,
             @RequestParam String doctorType,
             @RequestParam(required = false) Integer institutionId) {

        var doctorT = DoctorType.fromString(doctorType);
        var instT = MedicineInstitutionType.fromString(institutionType);


        return  service.getCountOfDoctorsWhoDoneMoreOperations(count, institutionId, doctorT, instT);
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

    @GetMapping(path = "doctorworks/experinced/count")
    public DoctorTypeStatisticEntity getCountOfDoctorsWithExperienceMoreThanSpecified
            (@RequestParam(required = false) Integer institutionId,
             @RequestParam Integer experience,
             @RequestParam String doctorType,
             @RequestParam String institutionType) {
        return service.getCountOfDoctorsWithMoreExperience(institutionId,
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

    @GetMapping(path = "doctorranks/count")
    public DoctorTypeStatisticEntity getCountOfDoctorsWithSuchRankAndPosition
            (@RequestParam(required = false) Integer institutionId,
             @RequestParam String doctorType,
             @RequestParam String institutionType,
             @RequestParam(required = false) String rank,
             @RequestParam(required = false) String position) {

        DoctorType doctorT = DoctorType.fromString(doctorType);
        DoctorScienceRank doctorR = DoctorScienceRank.fromString(rank);
        DoctorSciencePosition doctorP = DoctorSciencePosition.fromString(position);


        return service.getCountOfDoctorsWithSuchRankAndPosition(institutionId,
                doctorT,
                MedicineInstitutionType.fromString(institutionType),
                doctorR,
                doctorP);
    }

    @GetMapping("doctorstatistic/polyclinics")
    public List<DoctorPolyclinicWorkStatistic> getPolyclinicStats(@RequestParam(required = false) Integer doctorId,
                                                                  @RequestParam(required = false) Integer polyclinicId,
                                                                  @RequestParam(required = false) String doctorType,
                                                                  @RequestParam Date beginDate,
                                                                  @RequestParam Date endDate) {

        if ((doctorId != null) && (doctorId < 0)) {
            doctorId = null;
        }
        if ((polyclinicId != null) && (polyclinicId < 0)) {
            polyclinicId = null;
        }
        if (doctorType == null) {
            doctorType = "";
        }

        return service.getPolyclinicStats(doctorId, beginDate, endDate, polyclinicId, DoctorType.fromString(doctorType));
    }

    @GetMapping("doctorstatistic/hospitals")
    public List<DoctorStatisticEntity> getHospitalStats(@RequestParam(required = false) Integer doctorId,
                                                        @RequestParam(required = false) Integer hospitalId,
                                                        @RequestParam(required = false) String doctorType) {
        if ((doctorId == null) && (hospitalId == null) && (doctorType == null)) {
            return null;
        }
        if ((doctorId != null) && (doctorId < 0)) {
            doctorId = null;
        }
        if ((hospitalId != null) && (hospitalId < 0)) {
            hospitalId = null;
        }
        if (doctorType == null) {
            doctorType = "";
        }
        return service.getHospitalStats(doctorId, hospitalId, DoctorType.fromString(doctorType));
    }

    @GetMapping("getall")
    public List<DoctorDTO> getAllDoctors() {
        var entities = service.getAll();
        List<DoctorDTO> dtos = new ArrayList<>(entities.size());
        entities.forEach(entity -> dtos.add(DoctorDTO.EntityToDTO(entity)));
        return dtos;
    }

}

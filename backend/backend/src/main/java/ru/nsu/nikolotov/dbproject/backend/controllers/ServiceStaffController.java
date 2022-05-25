package ru.nsu.nikolotov.dbproject.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.nikolotov.dbproject.backend.entities.ServiceStaffEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.ServiceStaffInfoEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.ServiceStaffTypeStatistic;
import ru.nsu.nikolotov.dbproject.backend.services.ServiceStaffInfoService;
import ru.nsu.nikolotov.dbproject.backend.types.MedicineInstitutionType;

import java.util.List;

@RestController
@RequestMapping("api/v1/servicestaff")
public class ServiceStaffController {
    @Autowired
    ServiceStaffInfoService service;

    @GetMapping("getall")
    public List<ServiceStaffEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("servicestaffinfo")
    public List<ServiceStaffInfoEntity> getInfoAboutServiceStaff(
            @RequestParam(required = false) Integer institutionId,
            @RequestParam String workerType,
            @RequestParam String institutionType) {
        MedicineInstitutionType medicineInstitutionType = MedicineInstitutionType.fromString(institutionType);
        return service.getServiceStaffWorkingAtSuchInstitution(institutionId, workerType, medicineInstitutionType);
    }

    @GetMapping("servicestaffinfo/count")
    public ServiceStaffTypeStatistic getCountAboutServiceStaff(
            @RequestParam(required = false) Integer institutionId,
            @RequestParam String workerType,
            @RequestParam String institutionType) {
        MedicineInstitutionType medicineInstitutionType = MedicineInstitutionType.fromString(institutionType);
        return service.getCountServiceStaffWorkingAtSuchInstitution(institutionId, workerType, medicineInstitutionType);
    }
}

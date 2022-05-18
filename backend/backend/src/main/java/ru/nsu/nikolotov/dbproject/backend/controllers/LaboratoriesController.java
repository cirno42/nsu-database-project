package ru.nsu.nikolotov.dbproject.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.nikolotov.dbproject.backend.entities.LaboratoryServiceStatisticEntity;
import ru.nsu.nikolotov.dbproject.backend.services.LaboratoriesService;
import ru.nsu.nikolotov.dbproject.backend.types.LaboratoryTypes;
import ru.nsu.nikolotov.dbproject.backend.types.MedicineInstitutionType;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/labs")
public class LaboratoriesController {
    @Autowired
    LaboratoriesService service;

    @GetMapping("services/statistic")
    public List<LaboratoryServiceStatisticEntity> getLabServicesStats(
            @RequestParam Integer labId,
            @RequestParam String labType,
            @RequestParam Date beginDate,
            @RequestParam Date endDate,
            @RequestParam(required = false) Integer institutionId,
            @RequestParam String institutionType
    ) {
        MedicineInstitutionType institutionT = MedicineInstitutionType.fromString(institutionType);
        LaboratoryTypes labT = LaboratoryTypes.fromString(labType);
        if ((institutionId == null) && (institutionT != MedicineInstitutionType.ALL)) {
            return null;
        }
        if (institutionT == MedicineInstitutionType.NONE || labT == LaboratoryTypes.NONE) {
            return null;
        }

        return service.getLabServicesStats(labId, labT, beginDate, endDate, institutionId, institutionT);
    }
}

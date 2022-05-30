package ru.nsu.nikolotov.dbproject.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.LaboratoryEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.LaboratoryServiceStatisticEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.LaboratoryRepository;
import ru.nsu.nikolotov.dbproject.backend.types.LaboratoryTypes;
import ru.nsu.nikolotov.dbproject.backend.types.MedicineInstitutionType;

import java.util.Date;
import java.util.List;

@Service
public class LaboratoriesService {

    @Autowired
    LaboratoryRepository repository;
    public List<LaboratoryServiceStatisticEntity> getLabServicesStats
            (Integer labId, LaboratoryTypes labType, Date beginDate, Date endDate, Integer institutionId, MedicineInstitutionType institutionType) {

        return repository.getLabServicesStats(labId, labType, beginDate, endDate, institutionId, institutionType);
    }

    public List<LaboratoryEntity> getAll() {
        return repository.getAll();
    }

    public List<LaboratoryEntity> getLabsWithSuchType(LaboratoryTypes type) {
        return repository.getLabsWithSuchType(type);
    }
}

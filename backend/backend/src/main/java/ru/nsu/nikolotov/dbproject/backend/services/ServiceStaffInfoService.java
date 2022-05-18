package ru.nsu.nikolotov.dbproject.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.ServiceStaffInfoEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.ServiceStaffTypeStatistic;
import ru.nsu.nikolotov.dbproject.backend.repositories.ServiceStaffInfoRepository;
import ru.nsu.nikolotov.dbproject.backend.types.MedicineInstitutionType;

import java.util.List;

@Service
public class ServiceStaffInfoService {
    @Autowired
    private ServiceStaffInfoRepository repository;

    public List<ServiceStaffInfoEntity> getServiceStaffWorkingAtSuchInstitution
            (Integer institutionId, String workerType, MedicineInstitutionType institutionType) {
        return repository.getServiceStaffWorkingAtSuchInstitution(institutionId, workerType, institutionType);
    }

    public ServiceStaffTypeStatistic getCountServiceStaffWorkingAtSuchInstitution
            (Integer institutionId, String workerType, MedicineInstitutionType institutionType) {
        var list = repository.getServiceStaffWorkingAtSuchInstitution(institutionId, workerType, institutionType);
        var entity = new ServiceStaffTypeStatistic();
        entity.setType(workerType);
        entity.setCount(list.size());
        return entity;
    }
}

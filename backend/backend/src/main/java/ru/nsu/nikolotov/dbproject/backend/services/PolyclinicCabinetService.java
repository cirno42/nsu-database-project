package ru.nsu.nikolotov.dbproject.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.PolyclinicCabinetVisitsCountEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.PolyclinicCabinetsRepository;

import java.util.Date;
import java.util.List;

@Service
public class PolyclinicCabinetService {
    @Autowired
    PolyclinicCabinetsRepository repository;

    public List<PolyclinicCabinetVisitsCountEntity> getVisitStatistic(Integer polyclinicId, Date beginDate, Date endDate) {
        return repository.getVisitStatistic(polyclinicId, beginDate, endDate);
    }
}

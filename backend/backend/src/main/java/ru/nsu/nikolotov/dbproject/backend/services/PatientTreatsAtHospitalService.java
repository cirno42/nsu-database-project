package ru.nsu.nikolotov.dbproject.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtPolyclinicFullInfo;
import ru.nsu.nikolotov.dbproject.backend.entities.PatientTreatsInHospitalFullInfoEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.DoctorWorksAtPolyclinicRepository;
import ru.nsu.nikolotov.dbproject.backend.repositories.PatientTreatsAtHospitalRepository;

import java.util.List;

@Service
public class PatientTreatsAtHospitalService {
    @Autowired
    private PatientTreatsAtHospitalRepository repository;

    public List<PatientTreatsInHospitalFullInfoEntity> getAllCurrentPatients() {
        return repository.getAllCurrentPatients();
    }

    public List<PatientTreatsInHospitalFullInfoEntity> getHistoryOfPatients() {
        return repository.getHistoryOfPatients();
    }
}

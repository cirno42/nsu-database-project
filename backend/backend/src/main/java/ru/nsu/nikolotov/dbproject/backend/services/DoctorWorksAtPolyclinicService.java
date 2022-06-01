package ru.nsu.nikolotov.dbproject.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.dtos.CreateDoctorWorksAtHospitalDTO;
import ru.nsu.nikolotov.dbproject.backend.dtos.CreateDoctorWorksAtPolyclinicDTO;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtHospitalFullInfoEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtPolyclinicFullInfo;
import ru.nsu.nikolotov.dbproject.backend.repositories.DoctorWorksAtHospitalRepository;
import ru.nsu.nikolotov.dbproject.backend.repositories.DoctorWorksAtPolyclinicRepository;

import java.util.List;

@Service
public class DoctorWorksAtPolyclinicService {
    @Autowired
    private DoctorWorksAtPolyclinicRepository repository;

    public List<DoctorWorksAtPolyclinicFullInfo> getAll() {
        return repository.getAllWorkers();
    }

    public DoctorWorksAtPolyclinicFullInfo addDoctorToHospital(CreateDoctorWorksAtPolyclinicDTO dto) {
        return repository.addDoctorToPolyclinic(dto);
    }

    public void fireDoctor(Integer id) {
        repository.fireDoctor(id);
    }

    public List<DoctorWorksAtPolyclinicFullInfo> getHistoryOfWorkers() {
        return repository.getHistoryOfWorkers();
    }
}

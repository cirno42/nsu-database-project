package ru.nsu.nikolotov.dbproject.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.dtos.CreateDoctorWorksAtHospitalDTO;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtHospitalFullInfoEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.DoctorWorksAtHospitalRepository;

import java.util.List;

@Service
public class DoctorWorksAtHospitalService {
    @Autowired
    private DoctorWorksAtHospitalRepository repository;

    public List<DoctorWorksAtHospitalFullInfoEntity> getAll() {
        return repository.getAllWorkers();
    }

    public DoctorWorksAtHospitalFullInfoEntity addDoctorToHospital(CreateDoctorWorksAtHospitalDTO dto) {
        return repository.addDoctorToHospital(dto);
    }

    public List<DoctorWorksAtHospitalFullInfoEntity> getHistoryOfWorkers() {
        return repository.getHistoryOfWorkers();
    }

    public void fireDoctor(Integer id) {
        repository.fireDoctor(id);
    }
}

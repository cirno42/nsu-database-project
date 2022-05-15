package ru.nsu.nikolotov.dbproject.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtHospitalEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.DoctorRepository;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorSciencePosition;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorScienceRank;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorType;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository repository;

    public DoctorEntity create(DoctorEntity entity) {
        if (!isValidEntity(entity)) {
            return null;
        }
        return repository.create(entity);
    }

    public List<DoctorWorksAtHospitalEntity> getListOfDoctorsWorkingInHospitalWithSuchType(int hospitalId, DoctorType type) {
        return repository.getDoctorsWorkingInHospital(hospitalId, type);
    }

    public

    private boolean isValidEntity(DoctorEntity entity) {
        if (entity.getDoctorSciencePosition() == DoctorSciencePosition.PROFESSOR) {
            return entity.getDoctorScienceRank() != DoctorScienceRank.NONE;
        } else if (entity.getDoctorSciencePosition() == DoctorSciencePosition.DOCENT) {
            return entity.getDoctorScienceRank() == DoctorScienceRank.CANDIDATE_OF_SCIENCE;
        }
        return true;
    }


}

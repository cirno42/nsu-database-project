package ru.nsu.nikolotov.dbproject.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorExperienceEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtInstitutionEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.DoctorRepository;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorSciencePosition;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorScienceRank;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorType;
import ru.nsu.nikolotov.dbproject.backend.types.MedicineInstitutionType;

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

    public List<DoctorWorksAtInstitutionEntity> getListOfDoctorsWorkingInInstitutionWithSuchType
            (Integer instId, DoctorType type, MedicineInstitutionType institutionType) {
        return repository.getDoctorsWorkingInHospital(instId, type, institutionType);
    }

    public List<DoctorEntity> getListOfDoctorsWhoDoneMoreOperations
            (Integer count, Integer institutionId, DoctorType doctorType, MedicineInstitutionType institutionType) {
        return repository.getDoctorsWhoDoneMoreOperations(count, institutionId, doctorType, institutionType);
    }

    public List<DoctorExperienceEntity> getListOfDoctorsWithMoreExperience
            (Integer instId, Integer experience, MedicineInstitutionType institutionType, DoctorType doctorType) {
        return repository.getDoctorsWithMoreExperience(experience, instId, institutionType, doctorType);
    }

    public List<DoctorEntity> getDoctorsWithSuchRankAndPosition
            (Integer instId,
             DoctorType doctorType,
             MedicineInstitutionType institutionType,
             DoctorScienceRank rank,
             DoctorSciencePosition position) {
        return repository.getDoctorsWithSuchRankAndPosition(instId, doctorType, institutionType, rank, position);
    }

    private boolean isValidEntity(DoctorEntity entity) {
        if (entity.getDoctorSciencePosition() == DoctorSciencePosition.PROFESSOR) {
            return entity.getDoctorScienceRank() != DoctorScienceRank.NONE;
        } else if (entity.getDoctorSciencePosition() == DoctorSciencePosition.DOCENT) {
            return entity.getDoctorScienceRank() == DoctorScienceRank.CANDIDATE_OF_SCIENCE;
        }
        return true;
    }


}

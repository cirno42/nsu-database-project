package ru.nsu.nikolotov.dbproject.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.nikolotov.dbproject.backend.entities.*;
import ru.nsu.nikolotov.dbproject.backend.repositories.DoctorRepository;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorSciencePosition;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorScienceRank;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorType;
import ru.nsu.nikolotov.dbproject.backend.types.MedicineInstitutionType;

import java.util.Date;
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

    public DoctorTypeStatisticEntity getCountOfDoctorsWorkingInInstitutionWithSuchType
            (Integer instId, DoctorType type, MedicineInstitutionType institutionType) {
        var list = repository.getDoctorsWorkingInHospital(instId, type, institutionType);
        DoctorTypeStatisticEntity entity = new DoctorTypeStatisticEntity();
        entity.setCount(list.size());
        entity.setType(type.toString());
        return entity;
    }

    public List<DoctorEntity> getListOfDoctorsWhoDoneMoreOperations
            (Integer count, Integer institutionId, DoctorType doctorType, MedicineInstitutionType institutionType) {
        return repository.getDoctorsWhoDoneMoreOperations(count, institutionId, doctorType, institutionType);
    }

    public DoctorTypeStatisticEntity getCountOfDoctorsWhoDoneMoreOperations
            (Integer count, Integer institutionId, DoctorType doctorType, MedicineInstitutionType institutionType) {
        var list = repository.getDoctorsWhoDoneMoreOperations(count, institutionId, doctorType, institutionType);
        DoctorTypeStatisticEntity entity = new DoctorTypeStatisticEntity();
        entity.setCount(list.size());
        entity.setType(doctorType.toString());
        return entity;
    }

    public List<DoctorExperienceEntity> getListOfDoctorsWithMoreExperience
            (Integer instId, Integer experience, MedicineInstitutionType institutionType, DoctorType doctorType) {
        return repository.getDoctorsWithMoreExperience(experience, instId, institutionType, doctorType);
    }

    public DoctorTypeStatisticEntity getCountOfDoctorsWithMoreExperience
            (Integer instId, Integer experience, MedicineInstitutionType institutionType, DoctorType doctorType) {
        var list = repository.getDoctorsWithMoreExperience(experience, instId, institutionType, doctorType);
        DoctorTypeStatisticEntity entity = new DoctorTypeStatisticEntity();
        entity.setCount(list.size());
        entity.setType(doctorType.toString());
        return entity;
    }

    public List<DoctorEntity> getDoctorsWithSuchRankAndPosition
            (Integer instId,
             DoctorType doctorType,
             MedicineInstitutionType institutionType,
             DoctorScienceRank rank,
             DoctorSciencePosition position) {
        return repository.getDoctorsWithSuchRankAndPosition(instId, doctorType, institutionType, rank, position);
    }

    public DoctorTypeStatisticEntity getCountOfDoctorsWithSuchRankAndPosition
            (Integer instId,
             DoctorType doctorType,
             MedicineInstitutionType institutionType,
             DoctorScienceRank rank,
             DoctorSciencePosition position) {
        var list =  repository.getDoctorsWithSuchRankAndPosition(instId, doctorType, institutionType, rank, position);
        DoctorTypeStatisticEntity entity = new DoctorTypeStatisticEntity();
        entity.setCount(list.size());
        entity.setType(doctorType.toString());
        return entity;
    }

    public List<DoctorPolyclinicWorkStatistic> getPolyclinicStats(Integer doctorId,
                                                                  Date beginDate,
                                                                  Date endDate,
                                                                  Integer polyclinicId,
                                                                  DoctorType doctorType) {
        return repository.getPolyclinicStats(doctorId, beginDate, endDate, polyclinicId, doctorType);
    }

    public List<DoctorStatisticEntity> getHospitalStats(Integer doctorId,
                                                          Integer hospitalId,
                                                          DoctorType doctorType) {
        return repository.getHospitalStats(doctorId, hospitalId, doctorType);
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

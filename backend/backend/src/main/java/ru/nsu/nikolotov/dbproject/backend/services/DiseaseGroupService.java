package ru.nsu.nikolotov.dbproject.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.DiseaseGroupEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.DiseaseGroupRepository;

@Service
public class DiseaseGroupService {
    @Autowired
    DiseaseGroupRepository repository;

    public DiseaseGroupEntity createDiseaseGroup(DiseaseGroupEntity entity) {
        return repository.createDiseaseGroup(entity);
    }

    public DiseaseGroupEntity getDiseaseGroupById(int id) {
        return repository.getDiseaseGroupById(id);
    }

    public DiseaseGroupEntity getNextDiseaseGroupById(int id) {
        return repository.getNextDiseaseGroup(id);
    }

    public void deleteDiseaseGroup(int id) {
        repository.deleteDiseaseGroupById(id);
    }

    public void updateDiseaseGroup(DiseaseGroupEntity entity) {
        repository.updateDiseaseGroup(entity);
    }

}


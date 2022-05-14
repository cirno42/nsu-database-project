package ru.nsu.nikolotov.dbproject.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.DiseaseGroupEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalWardEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.DiseaseGroupRepository;
import ru.nsu.nikolotov.dbproject.backend.repositories.HospitalWardRepository;

@Service
public class HospitalWardService {
    @Autowired
    HospitalWardRepository repository;

    public HospitalWardEntity create(HospitalWardEntity entity) {
        return repository.create(entity);
    }

    public HospitalWardEntity getById(int id) {
        return repository.getById(id);
    }

    public HospitalWardEntity getNextById(int id) {
        return repository.getNext(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public void update(HospitalWardEntity entity) {
        repository.update(entity);
    }
}

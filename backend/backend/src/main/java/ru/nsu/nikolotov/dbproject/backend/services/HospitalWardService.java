package ru.nsu.nikolotov.dbproject.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.nikolotov.dbproject.backend.entities.DiseaseGroupEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalPlacesStatisticEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalWardEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.DiseaseGroupRepository;
import ru.nsu.nikolotov.dbproject.backend.repositories.HospitalWardRepository;

import java.util.List;

@Service
public class HospitalWardService {
    @Autowired
    HospitalWardRepository repository;

    public List<HospitalWardEntity> getWardsInDepartment(Integer departmentID) {
        return repository.getWardsInDepartment(departmentID);
    }

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

    public List<HospitalPlacesStatisticEntity> getCountOfWardsInHospital(Integer id) {
        return repository.getCountOfWardsInHospital(id);
    }

    public List<HospitalPlacesStatisticEntity> getCountOfWardsInDepartment(Integer id) {
        return repository.getCountOfWardsInDepartment(id);
    }

    public List<HospitalPlacesStatisticEntity> getCountOfPlacesInHospital(Integer id) {
        return repository.getCountOfPlacesInHospital(id);
    }

    public List<HospitalPlacesStatisticEntity> getCountOfPlacesInDepartment(Integer id) {
        return repository.getCountOfPlacesInDepartment(id);
    }

    public List<HospitalPlacesStatisticEntity> getCountOfFreeWardsInHospital(Integer id) {
        return repository.getCountOfFreeWardsInHospital(id);
    }

    public List<HospitalPlacesStatisticEntity> getCountOfFreeWardsInDepartment(Integer id) {
        return repository.getCountOfFreeWardsInDepartment(id);
    }

    public List<HospitalPlacesStatisticEntity> getCountOfFreePlacesInHospital(Integer id) {
        return repository.getCountOfFreePlacesInHospital(id);
    }

    public List<HospitalPlacesStatisticEntity> getCountOfFreePlacesInDepartment(Integer id) {
        return repository.getCountOfFreePlacesInDepartment(id);
    }
}

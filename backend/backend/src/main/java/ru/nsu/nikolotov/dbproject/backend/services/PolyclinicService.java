package ru.nsu.nikolotov.dbproject.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorStatisticEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.PolyclinicEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.PolyclinicRepository;

@Service
public class PolyclinicService {
    @Autowired
    private PolyclinicRepository repository;

    public PolyclinicEntity create(PolyclinicEntity entity) {
        return repository.create(entity);
    }

    public PolyclinicEntity getById(int id) {
        return repository.getById(id);
    }

    public PolyclinicEntity getNextById(int id) {
        return repository.getNext(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public void update(PolyclinicEntity entity) {
        repository.update(entity);
    }

    public DoctorStatisticEntity getPolyclinicCabinetsCount(Integer polyclinicId) {
        return repository.getPolyclinicCabinetsCount(polyclinicId);
    }
}

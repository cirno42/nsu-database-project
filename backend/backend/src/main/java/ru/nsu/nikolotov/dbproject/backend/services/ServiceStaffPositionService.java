package ru.nsu.nikolotov.dbproject.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.ServiceStaffPositionEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.ServiceStaffPositionsRepository;

import java.util.List;

@Service
public class ServiceStaffPositionService {
    @Autowired
    private ServiceStaffPositionsRepository repository;

    public List<ServiceStaffPositionEntity> getAll() {
        return repository.getAll();
    }
}

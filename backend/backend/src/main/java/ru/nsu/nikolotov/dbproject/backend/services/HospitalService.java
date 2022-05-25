package ru.nsu.nikolotov.dbproject.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.HospitalRepository;

import java.util.List;

@Service
public class HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;

    public HospitalEntity createNewHospital(String name) {
        return hospitalRepository.createNewHospital(name);
    }

    public HospitalEntity getNextHospital(int id) {
        return hospitalRepository.getNextHospital(id);
    }

    public void deleteHospital(int id) {
        hospitalRepository.deleteHospitalById(id);
    }

    public void updateHospital(HospitalEntity hospitalEntity) {
        hospitalRepository.updateHospital(hospitalEntity);
    }

    public HospitalEntity getHospitalById(int id) {
        return hospitalRepository.getHospitalById(id);
    }

    public List<HospitalEntity> getAll() {return hospitalRepository.getAll();}
}

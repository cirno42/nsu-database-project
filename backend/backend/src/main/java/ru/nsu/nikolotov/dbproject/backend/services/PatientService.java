package ru.nsu.nikolotov.dbproject.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorTreatsPatientEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.PatientEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.PatientRepository;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public PatientEntity getPatientById(Integer id) {return patientRepository.getPatientById(id);}

    public PatientEntity createNewPatient(String name, String diseaseHistory, String operationsHistory, String medsHistory) {
        return patientRepository.createNewPatient(name, diseaseHistory, operationsHistory, medsHistory);
    }

    public int updatePatient(PatientEntity patient) {
        return patientRepository.updatePatient(patient);
    }

    public PatientEntity getNextPatientById(int id) {return patientRepository.getNextPatient(id);}

    public void deletePatientById(int id) {patientRepository.deletePatientById(id);}

    public List<DoctorTreatsPatientEntity> getPatientsWhoTreatsInSuchPlace(Integer hospitalId, Integer departmentId, Integer wardId) {
        return patientRepository.getPatientsTreatsInSuchPlace(hospitalId, departmentId, wardId);
    }
}

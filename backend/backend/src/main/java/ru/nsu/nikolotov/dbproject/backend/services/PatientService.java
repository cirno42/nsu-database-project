package ru.nsu.nikolotov.dbproject.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.nikolotov.dbproject.backend.entities.*;
import ru.nsu.nikolotov.dbproject.backend.repositories.PatientRepository;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorType;

import java.util.Date;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public PatientEntity getPatientById(Integer id) {
        return patientRepository.getPatientById(id);
    }

    public PatientEntity createNewPatient(String name, String diseaseHistory, String operationsHistory, String medsHistory) {
        return patientRepository.createNewPatient(name, diseaseHistory, operationsHistory, medsHistory);
    }

    public int updatePatient(PatientEntity patient) {
        return patientRepository.updatePatient(patient);
    }

    public PatientEntity getNextPatientById(int id) {
        return patientRepository.getNextPatient(id);
    }

    public void deletePatientById(int id) {
        patientRepository.deletePatientById(id);
    }

    public List<DoctorTreatsPatientEntity> getPatientsWhoTreatsInSuchPlace(Integer hospitalId, Integer departmentId, Integer wardId) {
        return patientRepository.getPatientsTreatsInSuchPlace(hospitalId, departmentId, wardId);
    }

    public List<PatientTreatmentInfo> getPatientsTreatedByDoctorInHospital(Integer doctorId, Date admissionDate, Date dateOfRecovery) {
        return patientRepository.getPatientsTreatedByDoctorInHospital(doctorId, admissionDate, dateOfRecovery);
    }

    public List<PatientTreatmentInfo> getPatientsTreatedInHospital(Integer hospitalId, Date admission, Date recovery) {
        return patientRepository.getPatientsTreatedInHospital(hospitalId, admission, recovery);
    }

    public List<PatientTreatsInPolyclinicEntity> getPatientsTreatsInPolyclinic(Integer polyclinicId, DoctorType doctorType) {
        return patientRepository.getPatientsTreatsInPolyclinic(polyclinicId, doctorType);
    }

    public List<DoneOperationsForPatientEntity> getPatientWhoHadOperationsInPolyclinic(Date beginDate, Date endDate, Integer polyclinicId) {
        return patientRepository.getPatientWhoHadOperationsInPolyclinic(beginDate, endDate, polyclinicId);
    }
    public List<DoneOperationsForPatientEntity> getPatientWhoOperatedByDoctor(Date beginDate, Date endDate, Integer doctorId) {
        return patientRepository.getPatientWhoOperatedByDoctor(beginDate, endDate, doctorId);
   }
    public List<DoneOperationsForPatientEntity> getPatientWhoHadOperationsInHospital(Date beginDate, Date endDate, Integer hospitalId) {
        return patientRepository.getPatientWhoHadOperationsInHospital(beginDate, endDate, hospitalId);
    }

    public List<PatientEntity> getAll() {return patientRepository.getAll();}
}

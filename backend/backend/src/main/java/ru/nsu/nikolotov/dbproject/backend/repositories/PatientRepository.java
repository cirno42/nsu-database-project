package ru.nsu.nikolotov.dbproject.backend.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorTreatsPatientEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtInstitutionEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.PatientEntity;

import java.sql.Types;
import java.util.List;

@Repository
@Transactional
public class PatientRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    RepositoryUtils repositoryUtils;


    public PatientEntity getNextPatient(int id) {
        Integer nextId = repositoryUtils.getNextId("patients", id);
        if (nextId == null) {
            return null;
        }
        var patient = getPatientById(nextId);
        return patient;
    }

    public PatientEntity getPatientById(int id) {
        return repositoryUtils.getEntityId("Patients", id, PatientEntity.class);
    }

    public PatientEntity createNewPatient(String name, String diseaseHistory, String operationsHistory, String medsHistory) {
        var id = repositoryUtils.getNextval("patients_id_seq");
        String statementString = "Insert into Patients(id, name, diseaseHistory, operationsHistory, medsHistory) " +
                "values (?, ?, ?, ?, ?)";
        int affectedRows = jdbcTemplate.update(statementString, id, name, diseaseHistory, operationsHistory, medsHistory);
        if (affectedRows == 0) {
            return null;
        }
        String statementToSelectNewPatient = "Select * from patients where id = " + id;
        var createdPatient = jdbcTemplate.query(statementToSelectNewPatient, BeanPropertyRowMapper.newInstance(PatientEntity.class));
        return createdPatient.get(0);
    }

    public int updatePatient(PatientEntity patient) {
        String statementString = "Update Patients set name = ?, diseaseHistory=?, operationsHistory=?, medsHistory=? " +
                "where id=?";
        return jdbcTemplate.update(statementString, patient.getName(), patient.getDiseaseHistory(), patient.getOperationsHistory(),
                patient.getMedsHistory(), patient.getId());
    }

    public void deletePatientById(int id) {
        String statementString = "Delete from Patients where id = ?";
        jdbcTemplate.update(statementString, Integer.valueOf(id));
    }

    public List<DoctorTreatsPatientEntity> getPatientsTreatsInSuchPlace(Integer hospitalId, Integer departmentId, Integer wardId) {
        String statementString = null;
        if (wardId != null) {
            statementString = getStatementStringForPatientsTreatsInSuchWard();
            return jdbcTemplate.query(statementString,
                    BeanPropertyRowMapper.newInstance(DoctorTreatsPatientEntity.class), wardId);
        } else if (departmentId != null) {
            statementString = getStatementStringForPatientsTreatsInSuchDepartment();
            return jdbcTemplate.query(statementString,
                    BeanPropertyRowMapper.newInstance(DoctorTreatsPatientEntity.class), departmentId);
        } else if (hospitalId != null) {
            statementString = getStatementStringForPatientsTreatsInSuchHospital();
            return jdbcTemplate.query(statementString,
                    BeanPropertyRowMapper.newInstance(DoctorTreatsPatientEntity.class), hospitalId);
        }
        return null;
    }

    private String getStatementStringForPatientsTreatsInSuchWard() {
        String statementString = "Select Patients.id as patientId, Patients.name as patientName, Doctors.id as doctorid, Doctors.name as doctorName, PatientTreatsInHospital.admissionDate ,PatientTreatsInHospital.admissionTemperature \n" +
                "from\n" +
                "PatientTreatsInHospital inner join HospitalWards on (HospitalWards.id = PatientTreatsInHospital.wardId) \n" +
                "inner join Patients on (Patients.id = PatientTreatsInHospital.patientId)\n" +
                "inner join Doctors on (PatientTreatsInHospital.doctorId = Doctors.id)\n" +
                "Where (HospitalWards.id=?);\n";
        return statementString;
    }

    private String getStatementStringForPatientsTreatsInSuchDepartment() {
        String statementString = "Select Patients.id as patientId, Patients.name as patientName, Doctors.id as doctorid, Doctors.name as doctorName, PatientTreatsInHospital.admissionDate ,PatientTreatsInHospital.admissionTemperature \n" +
                " from\n" +
                "PatientTreatsInHospital inner join HospitalWards on (HospitalWards.id = PatientTreatsInHospital.wardId) \n" +
                "inner join HospitalDepartments on (HospitalDepartments.id = HospitalWards.departmentId)\n" +
                "inner join Hospitals on (HospitalDepartments.hospitalId = Hospitals.id) \n" +
                "inner join Patients on (Patients.id = PatientTreatsInHospital.patientId)\n" +
                "inner join Doctors on (PatientTreatsInHospital.doctorId = Doctors.id)\n" +
                "Where (HospitalDepartments.id=?);\n";
        return statementString;
    }

    private String getStatementStringForPatientsTreatsInSuchHospital() {
        String statementString = "Select Patients.id as patientId, Patients.name as patientName, Doctors.id as doctorid, Doctors.name as doctorName, PatientTreatsInHospital.admissionDate ,PatientTreatsInHospital.admissionTemperature \n" +
                "from\n" +
                "PatientTreatsInHospital inner join HospitalWards on (HospitalWards.id = PatientTreatsInHospital.wardId) \n" +
                "inner join HospitalDepartments on (HospitalDepartments.id = HospitalWards.departmentId)\n" +
                "inner join Hospitals on (HospitalDepartments.hospitalId = Hospitals.id) \n" +
                "inner join Patients on (Patients.id = PatientTreatsInHospital.patientId)\n" +
                "inner join Doctors on (PatientTreatsInHospital.doctorId = Doctors.id)\n" +
                "Where (Hospitals.id=?);\n";
        return statementString;
    }

}


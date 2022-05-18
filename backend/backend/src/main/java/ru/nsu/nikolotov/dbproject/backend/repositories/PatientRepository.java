package ru.nsu.nikolotov.dbproject.backend.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.nikolotov.dbproject.backend.entities.*;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorType;

import java.sql.Types;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class PatientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils repositoryUtils;


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

    public List<PatientTreatmentInfo> getPatientsTreatedInHospital(Integer hospitalId, Date admission, Date recovery) {
        String statementString = "Select Patients.id, Patients.name, PatientTreatedInHospital.admissionDate, PatientTreatedInHospital.dateOfRecovery\n" +
                "from \n" +
                "Patients inner join PatientTreatedInHospital on (Patients.id = PatientTreatedInHospital.patientId)\n" +
                "inner join HospitalWards on (patienttreatedinhospital.wardid = HospitalWards.id)\n" +
                "inner join hospitaldepartments on (hospitaldepartments.id = hospitalwards.departmentid)\n" +
                "inner join hospitals on (hospitals.id = hospitaldepartments.hospitalid)\n"+
                "where (Hospitals.id = ?) and (PatientTreatedInHospital.admissionDate >= ?) and (PatientTreatedInHospital.dateOfRecovery <= ?);\n";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(PatientTreatmentInfo.class), hospitalId, admission, recovery);

    }

    public List<PatientTreatmentInfo> getPatientsTreatedByDoctorInHospital(Integer doctorId, Date admissionDate, Date dateOfRecovery) {
        String statementString = "Select Patients.id, Patients.name, PatientTreatedInHospital.admissionDate, PatientTreatedInHospital.dateOfRecovery\n" +
                "from Patients inner join PatientTreatedInHospital on (Patients.id = PatientTreatedInHospital.patientId)\n" +
                "inner join doctors d on patienttreatedinhospital.doctorid = d.id\n" +
                "where (d.id = ?) and (PatientTreatedInHospital.admissionDate >= ?) and (PatientTreatedInHospital.dateOfRecovery <= ?);";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(PatientTreatmentInfo.class), doctorId, admissionDate, dateOfRecovery);
    }

    public List<PatientTreatsInPolyclinicEntity> getPatientsTreatsInPolyclinic(Integer polyclinicId, DoctorType doctorType) {
        String statementString = getStatementStringForPatientsTreatsInPolyclinic(doctorType);
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(PatientTreatsInPolyclinicEntity.class), polyclinicId);
    }

    public  List<DoneOperationsForPatientEntity> getPatientWhoHadOperationsInPolyclinic (Date beginDate, Date endDate, Integer polyclinicId) {
        String statementString = "Select Patients.id, Patients.name,\n" +
                "       doneoperationsforpolyclinics.operationName, doneoperationsforpolyclinics.dateofoperation, doneoperationsforpolyclinics.isresultlethal, doneoperationsforpolyclinics.polyclinicid\n" +
                "from Patients inner join doneoperationsforpolyclinics on (Patients.id = doneoperationsforpolyclinics.patientId)\n" +
                "where dateofoperation >= ? and dateofoperation <= ? and doneoperationsforpolyclinics.polyclinicid = ?;\n;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoneOperationsForPatientEntity.class), beginDate, endDate, polyclinicId);
    }

    public  List<DoneOperationsForPatientEntity> getPatientWhoOperatedByDoctor (Date beginDate, Date endDate, Integer doctorId) {
        String statementString = "Select Patients.id, Patients.name,\n" +
                "DoneOperationsForHospitals.operationName, doneoperationsforhospitals.dateofoperation, doneoperationsforhospitals.isresultlethal, doneoperationsforhospitals.hospitalid\n" +
                "from Patients inner join DoneOperationsForHospitals on (Patients.id = DoneOperationsForHospitals.patientId)\n" +
                "where dateofoperation >= ? and dateofoperation <= ? and doctorid = ?\n "
                + "union " + "Select Patients.id, Patients.name,\n" +
                "DoneOperationsForHospitals.operationName, doneoperationsforhospitals.dateofoperation, doneoperationsforhospitals.isresultlethal, doneoperationsforhospitals.hospitalid\n" +
                "from Patients inner join DoneOperationsForHospitals on (Patients.id = DoneOperationsForHospitals.patientId)\n" +
                "where dateofoperation >= ? and dateofoperation <= ? and doctorid = ?;\n";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoneOperationsForPatientEntity.class), beginDate, endDate, doctorId, beginDate, endDate, doctorId);
    }

    public  List<DoneOperationsForPatientEntity> getPatientWhoHadOperationsInHospital (Date beginDate, Date endDate, Integer hospitalId) {
        String statementString = "Select Patients.id, Patients.name,\n" +
                "DoneOperationsForHospitals.operationName, doneoperationsforhospitals.dateofoperation, doneoperationsforhospitals.isresultlethal, doneoperationsforhospitals.hospitalid\n" +
                "from Patients inner join DoneOperationsForHospitals on (Patients.id = DoneOperationsForHospitals.patientId)\n" +
                "where dateofoperation >= ? and dateofoperation <= ? and hospitalid = ?;\n";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoneOperationsForPatientEntity.class), beginDate, endDate, hospitalId);
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

    private String getStatementStringForPatientsTreatsInPolyclinic(DoctorType doctorType) {
        String doctorTypeTableName = DoctorType.doctorTypeToTableName(doctorType);
        String statementString = "Select Patients.id as patientId, Patients.name as patientName, Doctors.id as doctorId,Doctors.name as doctorName, currentDisease,\n" +
                "PatientTreatsInPolyclinic.currentmeds,\n" +
                "patienttreatsinpolyclinic.currentoperation,\n" +
                "patienttreatsinpolyclinic.admissiondate,\n" +
                "patienttreatsinpolyclinic.dateofrecovery\n" +
                "from Patients inner join PatientTreatsInPolyclinic on (Patients.id = PatientTreatsInPolyclinic.patientId)\n" +
                "inner join Doctors on (Doctors.id = PatientTreatsInPolyclinic.doctorId)\n" +
                "inner join " + doctorTypeTableName + " on (Doctors.id =" + doctorTypeTableName + ".id) "
                + "inner join doctorworksatpolyclinic on (Doctors.id = doctorworksatpolyclinic.doctorId) "
                + "where doctorworksatpolyclinic.polyclinicid=?";
        return statementString;
    }


}


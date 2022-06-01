package ru.nsu.nikolotov.dbproject.backend.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtHospitalFullInfoEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.PatientTreatsInHospitalFullInfoEntity;

import java.util.List;

@Repository
public class PatientTreatsAtHospitalRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils repositoryUtils;

    public List<PatientTreatsInHospitalFullInfoEntity> getAllCurrentPatients() {
        String statementString = "select p.id as patientid, p.name as patientName, doctors.name as doctorname, h.name as hospitalname, dep.name as departmentname,wardnumber,admissionDate,dateOfRecovery,\n" +
                "    admissionTemperature,currentDisease, currentOperation, currentMeds\n" +
                "from patienttreatsinhospital inner join hospitalwards on patienttreatsinhospital.wardid = hospitalwards.id\n" +
                "inner join hospitaldepartments dep on dep.id = hospitalwards.departmentid\n" +
                "inner join hospitals h on dep.hospitalid = h.id\n" +
                "inner join doctors on patienttreatsinhospital.doctorid = doctors.id\n" +
                "inner join patients p on patienttreatsinhospital.patientid = p.id;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(PatientTreatsInHospitalFullInfoEntity.class));
    }

    public List<PatientTreatsInHospitalFullInfoEntity> getHistoryOfPatients() {
        String statementString = "select p.id as patientid, p.name as patientName, doctors.name as doctorname, h.name as hospitalname, dep.name as departmentname,wardnumber,admissionDate,dateOfRecovery,\n" +
                "    admissionTemperature,currentDisease, currentOperation, currentMeds\n" +
                "from patienttreatedinhospital inner join hospitalwards on patienttreatedinhospital.wardid = hospitalwards.id\n" +
                "inner join hospitaldepartments dep on dep.id = hospitalwards.departmentid\n" +
                "inner join hospitals h on dep.hospitalid = h.id\n" +
                "inner join doctors on patienttreatedinhospital.doctorid = doctors.id\n" +
                "inner join patients p on patienttreatedinhospital.patientid = p.id;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(PatientTreatsInHospitalFullInfoEntity.class));
    }

}

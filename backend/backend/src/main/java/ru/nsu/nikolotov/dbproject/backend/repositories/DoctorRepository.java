package ru.nsu.nikolotov.dbproject.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtHospitalEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.PolyclinicEntity;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorSciencePosition;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorScienceRank;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorType;

import java.util.List;

@Repository
@Transactional
public class DoctorRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils repositoryUtils;

    public DoctorEntity create(DoctorEntity entity) {
        var id = repositoryUtils.getNextval("doctors_id_seq");
        String statementString = "Insert into doctors(id, name, vacationstart, vacationend, salarycoefficient) " +
                " values (?, ?, ?, ?, ?)";

        int affectedRows = jdbcTemplate.update(statementString,
                id,
                entity.getName(),
                entity.getVacationStart(),
                entity.getVacationEnd(),
                entity.getSalaryCoefficient());

        if (entity.getCanDoOperation()) {
            insertIntoDoctorsWhoCanDoOperations(id);
        }

        markDoctorRank(id, entity.getDoctorScienceRank());
        markDoctorPosition(id, entity.getDoctorSciencePosition());
        insertDoctorType(id, entity.getDoctorType());

        if (affectedRows == 0) {
            return null;
        }
        entity.setId(id);
        return entity;
    }

    public DoctorEntity getNext(int id) {
        var nextId = repositoryUtils.getNextId("doctors", id);
        return getById(nextId);
    }

    public void delete(int id)  {

    }

    public List<DoctorWorksAtHospitalEntity> getDoctorsWorkingInHospital(int hospitalId, DoctorType doctorType) {
        String tableName = doctorTypeToTableName(doctorType);
        String statementString = "Select Doctors.id as doctorId, Doctors.name as doctorName, Hospitals.id as hospitalId, Hospitals.name as hospitalName, DoctorWorksAtHospital.salary, DoctorWorksAtHospital.contractStartDate, DoctorWorksAtHospital.contractEndDate, Doctors.vacationStart, " +
                "Doctors.vacationEnd, Doctors.salaryCoefficient " +
                "from (" + tableName + " INNER JOIN DoctorWorksAtHospital on (" + tableName + ".id = DoctorWorksAtHospital.doctorId) " +
                "INNER JOIN Doctors on (" + tableName + ".id = Doctors.id)) " +
                "INNER JOIN Hospitals on (DoctorWorksAtHospital.hospitalId = Hospitals.id) where hospitalId = ?";
        var res = jdbcTemplate.query(statementString,
                BeanPropertyRowMapper.newInstance(DoctorWorksAtHospitalEntity.class), hospitalId);
        return res;
    }

    public DoctorEntity getById(int id) {
        return repositoryUtils.getEntityId("doctors", id, DoctorEntity.class);
    }

    public void markDoctorPosition(int id, DoctorSciencePosition position) {
        if (position == DoctorSciencePosition.NONE) {
            return;
        }

        String tableName = "";
        switch (position) {
            case DOCENT:
                tableName = "docents";
                break;
            case PROFESSOR:
                tableName = "professors";
                break;
        }
        String statementString = "insert into " + tableName + "(id)  values (?)";
        jdbcTemplate.update(statementString, id);
    }

    public void markDoctorRank(int id, DoctorScienceRank rank) {
        if (rank == DoctorScienceRank.NONE) {
            return;
        }
        String tableName = "";
        switch (rank) {
            case CANDIDATE_OF_SCIENCE:
                tableName = "candidatsofscience";
                break;
            case DOCTOR_OF_SCIENCE:
                tableName = "doctorsofscience";
                break;
        }
        String statementString = "Insert into " + tableName + "(id) values(?)";
        jdbcTemplate.update(statementString, id);
    }

    public void insertIntoDoctorsWhoCanDoOperations(int id) {
        String statementString = "insert into doctorswhocandooperations (id, operationstotalcount, operationswithlethalresult)" +
                "values (?, 0, 0)";
        jdbcTemplate.update(statementString, id);
    }

    private void insertDoctorType(int id, DoctorType type) {
        if (type == DoctorType.NONE) {
            return;
        }
        String tableName = doctorTypeToTableName(type);

        String statementString = "Insert into " + tableName + "(id) values (?)";
        jdbcTemplate.update(statementString, id);
    }

    private String doctorTypeToTableName(DoctorType type) {
        String tableName = null;
        switch (type) {
            case DENTIST:
                tableName = "dentists";
                break;
            case SURGEON:
                tableName = "surgeons";
                break;
            case THERAPIST:
                tableName = "therapists";
                break;
            case RADIOLOGIST:
                tableName = "radiologists";
                break;
            case OPHTHALMOLOGIST:
                tableName = "ophthalmologists";
                break;
            case NEUROPATHOLOGIST:
                tableName = "neuropathologists";
                break;
        }
        return tableName;
    }
}

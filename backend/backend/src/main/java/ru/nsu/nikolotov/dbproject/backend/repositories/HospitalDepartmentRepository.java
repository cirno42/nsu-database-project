package ru.nsu.nikolotov.dbproject.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalDepartmentEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalEntity;

import java.util.List;

@Repository
@Transactional
public class HospitalDepartmentRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    RepositoryUtils repositoryUtils;

    public List<HospitalDepartmentEntity> getDepartmentsInHospital(Integer hospitalID) {
        String statementString = "Select * from hospitaldepartments where hospitalId=?";
        return jdbcTemplate.query(statementString,  BeanPropertyRowMapper.newInstance(HospitalDepartmentEntity.class), hospitalID);
    }

    public HospitalDepartmentEntity createHospitalDepartment(HospitalDepartmentEntity department) {
        var id = repositoryUtils.getNextval("hospitaldepartment_id_seq");
        String statementString = "Insert into hospitaldepartments(id, diseasegroupspecializationid, name, hospitalid) values (?, ?, ?, ?)";
        int affectedRows = jdbcTemplate.update(statementString, id, department.getDiseaseGroupSpecializationId(),
                department.getName(), department.getHospitalId());
        if (affectedRows == 0) {
            return null;
        }
        String statementToSelectNewPatient = "Select * from hospitaldepartments where id = " + id;
        var createdEntity = jdbcTemplate.query(statementToSelectNewPatient,
                BeanPropertyRowMapper.newInstance(HospitalDepartmentEntity.class));
        return createdEntity.get(0);
    }

    public HospitalDepartmentEntity getNextHospitalDep(int id) {
        var nextId = repositoryUtils.getNextId("hospitaldepartments", id);
        return getHospitalDepById(nextId);
    }

    public int updateHospitalDepartment(HospitalDepartmentEntity departmentEntity) {
        String statementString = "Update hospitaldepartments set name = ?, diseasegroupspecializationid = ?, hospitalid = ? " +
                "where id=?";
        return jdbcTemplate.update(statementString, departmentEntity.getName(),
                departmentEntity.getDiseaseGroupSpecializationId(), departmentEntity.getHospitalId(), departmentEntity.getId());
    }

    public void deleteHospitalDepById(int id) {
        String statementString = "Delete from hospitaldepartments where id = ?";
        jdbcTemplate.update(statementString, id);
    }

    public HospitalDepartmentEntity getHospitalDepById(int id) {
        return repositoryUtils.getEntityId("hospitaldepartments", id, HospitalDepartmentEntity.class);
    }

}

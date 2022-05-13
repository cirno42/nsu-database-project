package ru.nsu.nikolotov.dbproject.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalEntity;

@Transactional
@Repository
public class HospitalRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    RepositoryUtils repositoryUtils;

    public HospitalEntity createNewHospital(String name) {
        var id = repositoryUtils.getNextval("hospital_id_seq");
        String statementString = "Insert into hospitals(id, name) values (?, ?)";
        int affectedRows = jdbcTemplate.update(statementString, id, name);
        if (affectedRows == 0) {
            return null;
        }
        String statementToSelectNewPatient = "Select * from hospitals where id = " + id;
        var createdHospital = jdbcTemplate.query(statementToSelectNewPatient,
                BeanPropertyRowMapper.newInstance(HospitalEntity.class));
        return createdHospital.get(0);
    }

    public HospitalEntity getNextHospital(int id) {
        var nextId = repositoryUtils.getNextId("Hospitals", id);
        return getHospitalById(nextId);
    }

    public int updateHospital(HospitalEntity hospitalEntity) {
        String statementString = "Update hospitals set name = ? " +
                "where id=?";
        return jdbcTemplate.update(statementString, hospitalEntity.getName(), hospitalEntity.getId());
    }

    public void deleteHospitalById(int id) {
        String statementString = "Delete from Hospitals where id = ?";
        jdbcTemplate.update(statementString, id);
    }

    public HospitalEntity getHospitalById(int id) {
        return repositoryUtils.getEntityId("Hospitals", id, HospitalEntity.class);
    }

}

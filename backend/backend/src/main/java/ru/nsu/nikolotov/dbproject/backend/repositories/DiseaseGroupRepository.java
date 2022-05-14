package ru.nsu.nikolotov.dbproject.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.nikolotov.dbproject.backend.entities.DiseaseGroupEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalDepartmentEntity;

@Repository
@Transactional
public class DiseaseGroupRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RepositoryUtils repositoryUtils;

    public DiseaseGroupEntity createDiseaseGroup(DiseaseGroupEntity diseaseGroupEntity) {
        var id = repositoryUtils.getNextval("diseasesgroups_id_seq");
        String statementString = "Insert into diseasesgroups(id, name) values (?, ?)";
        int affectedRows = jdbcTemplate.update(statementString, id, diseaseGroupEntity.getName());
        if (affectedRows == 0) {
            return null;
        }
        String statementToSelectNewPatient = "Select * from diseasesgroups where id = " + id;
        var createdEntity = jdbcTemplate.query(statementToSelectNewPatient,
                BeanPropertyRowMapper.newInstance(DiseaseGroupEntity.class));
        return createdEntity.get(0);
    }

    public DiseaseGroupEntity getNextDiseaseGroup(int id) {
        var nextId = repositoryUtils.getNextId("diseasesgroups", id);
        return getDiseaseGroupById(nextId);
    }

    public int updateDiseaseGroup(DiseaseGroupEntity diseaseGroupEntity) {
        String statementString = "Update diseasesgroups set name = ? " +
                "where id=?";
        return jdbcTemplate.update(statementString, diseaseGroupEntity.getName(), diseaseGroupEntity.getId());
    }

    public void deleteDiseaseGroupById(int id) {
        String statementString = "Delete from diseasesgroups where id = ?";
        jdbcTemplate.update(statementString, id);
    }

    public DiseaseGroupEntity getDiseaseGroupById(int id) {
        return repositoryUtils.getEntityId("diseasesgroups", id, DiseaseGroupEntity.class);
    }

}

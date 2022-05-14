package ru.nsu.nikolotov.dbproject.backend.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.nikolotov.dbproject.backend.entities.DiseaseGroupEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalWardEntity;

@Repository
@Transactional
public class HospitalWardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils repositoryUtils;

    public HospitalWardEntity create(HospitalWardEntity entity) {
        var id = repositoryUtils.getNextval("hospitalward_id_seq");
        String statementString = "Insert into HospitalWards(id, wardnumber, totalplaces, departmentid) " +
                "values (?, ?, ?, ?)";
        int affectedRows = jdbcTemplate.update(statementString, id, entity.getWardNumber(),
                entity.getTotalPlaces(), entity.getDepartmentId());
        if (affectedRows == 0) {
            return null;
        }
        String statementToSelectNewEntity = "Select * from HospitalWards where id = " + id;
        var createdEntity = jdbcTemplate.query(statementToSelectNewEntity,
                BeanPropertyRowMapper.newInstance(HospitalWardEntity.class));
        return createdEntity.get(0);
    }

    public HospitalWardEntity getNext(int id) {
        var nextId = repositoryUtils.getNextId("HospitalWards", id);
        return getById(nextId);
    }

    public int update(HospitalWardEntity entity) {
        String statementString = "Update HospitalWards set wardnumber = ?, totalplaces = ?, departmentid = ? " +
                "where id=?";
        return jdbcTemplate.update(statementString, entity.getWardNumber(), entity.getTotalPlaces(), entity.getDepartmentId(), entity.getId());
    }

    public void delete(int id) {
        String statementString = "Delete from HospitalWards where id = ?";
        jdbcTemplate.update(statementString, id);
    }

    public HospitalWardEntity getById(int id) {
        return repositoryUtils.getEntityId("HospitalWards", id, HospitalWardEntity.class);
    }
}

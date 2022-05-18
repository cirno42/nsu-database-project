package ru.nsu.nikolotov.dbproject.backend.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorStatisticEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.PolyclinicEntity;

@Repository
@Transactional
public class PolyclinicRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    RepositoryUtils repositoryUtils;

    public PolyclinicEntity create(PolyclinicEntity entity) {
        var id = repositoryUtils.getNextval("polyclinics_id_seq");
        String statementString = "Insert into polyclinics(id, name) " +
                " values (?, ?)";
        int affectedRows = jdbcTemplate.update(statementString, id, entity.getName());
        if (affectedRows == 0) {
            return null;
        }
        String statementToSelectNewEntity = "Select * from polyclinics where id = " + id;
        var createdEntity = jdbcTemplate.query(statementToSelectNewEntity,
                BeanPropertyRowMapper.newInstance(PolyclinicEntity.class));
        return createdEntity.get(0);
    }

    public PolyclinicEntity getNext(int id) {
        var nextId = repositoryUtils.getNextId("Polyclinics", id);
        return getById(nextId);
    }

    public int update(PolyclinicEntity entity) {
        String statementString = "Update polyclinics set name " +
                "where id=?";
        return jdbcTemplate.update(statementString, entity.getName(), entity.getId());
    }

    public void delete(int id) {
        String statementString = "Delete from polyclinics where id = ?";
        jdbcTemplate.update(statementString, id);
    }

    public PolyclinicEntity getById(int id) {
        return repositoryUtils.getEntityId("polyclinics", id, PolyclinicEntity.class);
    }

    public DoctorStatisticEntity getPolyclinicCabinetsCount(Integer polyclinicId) {
        String statementString ="Select Polyclinics.id, Polyclinics.name, count(*) as count\n" +
                "from Polyclinics inner join PolyclinicCabinets on (Polyclinics.id = PolyclinicCabinets.polyclinicId)\n" +
                "where polyclinicid = ?\n" +
                "group by Polyclinics.id;";
        var resList = jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoctorStatisticEntity.class), polyclinicId);
        if (resList.size() == 0) {
            return null;
        }
        return resList.get(0);
    }
}

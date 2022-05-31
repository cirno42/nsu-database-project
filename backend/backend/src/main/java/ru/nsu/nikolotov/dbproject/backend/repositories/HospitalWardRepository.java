package ru.nsu.nikolotov.dbproject.backend.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.nikolotov.dbproject.backend.entities.*;

import java.util.List;

@Repository
@Transactional
public class HospitalWardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils repositoryUtils;

    public List<HospitalWardEntity> getWardsInDepartment(Integer departmentId) {
        String statementString = "Select * from hospitalwards where departmentid=?";
        return jdbcTemplate.query(statementString,  BeanPropertyRowMapper.newInstance(HospitalWardEntity.class), departmentId);
    }

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

    public List<HospitalPlacesStatisticEntity> getCountOfWardsInHospital(Integer id) {
        String statemntString = "Select h.id, h.name, count(*)\n" +
                "from HospitalWards \n" +
                "inner join hospitaldepartments hd on hospitalwards.departmentid = hd.id\n" +
                "inner join hospitals h on hd.hospitalid = h.id\n" +
                "where h.id = ?\n" +
                "group by h.id, h.name;";
        return jdbcTemplate.query(statemntString, BeanPropertyRowMapper.newInstance(HospitalPlacesStatisticEntity.class), id);
    }

    public List<HospitalPlacesStatisticEntity> getCountOfWardsInDepartment(Integer id) {
        String statemntString = "Select hd.id, hd.name, count(*)\n" +
                "from HospitalWards \n" +
                "inner join hospitaldepartments hd on hospitalwards.departmentid = hd.id\n" +
                "where hd.id = ?\n" +
                "group by hd.id, hd.name;";
        return jdbcTemplate.query(statemntString, BeanPropertyRowMapper.newInstance(HospitalPlacesStatisticEntity.class), id);
    }

    public List<HospitalPlacesStatisticEntity> getCountOfPlacesInHospital(Integer id) {
        String statementString = "Select h.id, h.name, sum(hospitalwards.totalplaces) as count\n" +
                "from HospitalWards\n" +
                "inner join hospitaldepartments hd on hospitalwards.departmentid = hd.id\n" +
                "inner join hospitals h on hd.hospitalid = h.id\n" +
                "where h.id = ?\n" +
                "group by h.id, h.name;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(HospitalPlacesStatisticEntity.class), id);

    }

    public List<HospitalPlacesStatisticEntity> getCountOfPlacesInDepartment(Integer id) {
        String statementString = "Select hd.id, hd.name, sum(hospitalwards.totalplaces) as count \n" +
                "from HospitalWards\n" +
                "inner join hospitaldepartments hd on hospitalwards.departmentid = hd.id\n" +
                "where hd.id = ?\n" +
                "group by hd.id, hd.name;\n";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(HospitalPlacesStatisticEntity.class), id);
    }

    public List<HospitalPlacesStatisticEntity> getCountOfFreeWardsInHospital(Integer id) {
        String statementString = "Select wards.hospitalId as id, wards.name as name, count(*)  from (\n" +
                "Select HospitalWards.id, HospitalWards.wardNumber, HospitalWards.totalPlaces, h.name, h.id as hospitalId, count(PatientTreatsInHospital.wardId) as busyPlaces, totalPlaces - count(PatientTreatsInHospital.wardId) as freePlaces\n" +
                "from HospitalWards left join PatientTreatsInHospital on (HospitalWards.id = PatientTreatsInHospital.wardId)\n" +
                "                   inner join hospitaldepartments hd on hospitalwards.departmentid = hd.id\n" +
                "                   inner join hospitals h on hd.hospitalid = h.id\n" +
                "where h.id = ?\n" +
                "group by h.name, HospitalWards.wardNumber, HospitalWards.totalPlaces, HospitalWards.id, h.id)\n" +
                "    as wards where wards.freePlaces = wards.totalPlaces\n" +
                "group by wards.hospitalId, wards.name;";

        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(HospitalPlacesStatisticEntity.class), id);
    }

    public List<HospitalPlacesStatisticEntity> getCountOfFreeWardsInDepartment(Integer id) {
        String statementString = "Select wards.depId as id, wards.name as name, count(*) from (\n" +
                "Select HospitalWards.id, HospitalWards.wardNumber, HospitalWards.totalPlaces, hd.name, hd.id as depId, count(PatientTreatsInHospital.wardId) as busyPlaces, totalPlaces - count(PatientTreatsInHospital.wardId) as freePlaces\n" +
                "from HospitalWards left join PatientTreatsInHospital on (HospitalWards.id = PatientTreatsInHospital.wardId)\n" +
                "                   inner join hospitaldepartments hd on hospitalwards.departmentid = hd.id\n" +
                "where hd.id = ?\n" +
                "group by hd.name, HospitalWards.wardNumber, HospitalWards.totalPlaces, HospitalWards.id, hd.id)\n" +
                "    as wards where wards.freePlaces = wards.totalPlaces\n" +
                "group by wards.depId, wards.name;";

        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(HospitalPlacesStatisticEntity.class), id);
    }

    public List<HospitalPlacesStatisticEntity> getCountOfFreePlacesInHospital(Integer id) {
        String statementString = "Select h.id, h.name, sum(wards.freePlaces) as count from (Select HospitalWards.id, hospitalwards.departmentid, totalPlaces - count(PatientTreatsInHospital.wardId) as freePlaces\n" +
                "from HospitalWards left join PatientTreatsInHospital on (HospitalWards.id = PatientTreatsInHospital.wardId)\n" +
                "group by HospitalWards.id) as wards\n" +
                "inner join hospitaldepartments on departmentid = hospitaldepartments.id\n" +
                "inner join hospitals h on hospitaldepartments.hospitalid = h.id\n" +
                "where h.id = ?\n" +
                "group by h.id;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(HospitalPlacesStatisticEntity.class), id);
    }

    public List<HospitalPlacesStatisticEntity> getCountOfFreePlacesInDepartment(Integer id) {
        String statementString = "Select hospitaldepartments.id, hospitaldepartments.name, sum(wards.freePlaces) as count from (Select HospitalWards.id, hospitalwards.departmentid, totalPlaces - count(PatientTreatsInHospital.wardId) as freePlaces\n" +
                "from HospitalWards left join PatientTreatsInHospital on (HospitalWards.id = PatientTreatsInHospital.wardId)\n" +
                "group by HospitalWards.id) as wards\n" +
                "inner join hospitaldepartments on departmentid = hospitaldepartments.id\n" +
                "where hospitaldepartments.id = ?\n" +
                "group by hospitaldepartments.id;";

        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(HospitalPlacesStatisticEntity.class), id);
    }

    public List<HospitalWardFullInfoEntity> getAll() {
        String statementString = "Select hospitalwards.id as id, wardnumber, totalplaces, departmentid, dep.name as departmentName, h.id as hospitalId, h.name as hospitalName\n" +
                " from hospitalwards inner join hospitaldepartments dep on dep.id = hospitalwards.departmentid inner join hospitals h on h.id = dep.hospitalid;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(HospitalWardFullInfoEntity.class));
    }
}

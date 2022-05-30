package ru.nsu.nikolotov.dbproject.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.nsu.nikolotov.dbproject.backend.entities.PolyclinicCabinetEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.PolyclinicCabinetVisitsCountEntity;

import java.util.Date;
import java.util.List;

@Repository
public class PolyclinicCabinetsRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils repositoryUtils;

    public List<PolyclinicCabinetVisitsCountEntity> getVisitStatistic(Integer polyclinicId, Date beginDate, Date endDate) {
        String statementString = "Select Polyclinics.id as polyclinicId, Polyclinics.name as polyclinicName, PolyclinicCabinets.id as cabinetId, PolyclinicCabinets.cabinetName, count(patientsvisitpolycliniccabinets.dateofvisit)\n" +
                "from Polyclinics inner join PolyclinicCabinets on (Polyclinics.id = PolyclinicCabinets.polyclinicId)\n" +
                "                 left join PatientsVisitPolyclinicCabinets on (PolyclinicCabinets.id = patientsvisitpolycliniccabinets.cabinetid)\n" +
                "where ((dateOfVisit >= ? and dateOfVisit <= ?) or (dateofvisit is null)) and polyclinics.id = ?\n" +
                "group by Polyclinics.id, Polyclinics.name, PolyclinicCabinets.id, PolyclinicCabinets.cabinetName;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(PolyclinicCabinetVisitsCountEntity.class), beginDate, endDate, polyclinicId);
    }

    public List<PolyclinicCabinetEntity> getAll() {
        String statementString =
                "Select polyclinicid, name as polyclinicname, p.id as cabinetid, cabinetname from polyclinics inner join polycliniccabinets p on polyclinics.id = p.polyclinicid;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(PolyclinicCabinetEntity.class));
    }
}

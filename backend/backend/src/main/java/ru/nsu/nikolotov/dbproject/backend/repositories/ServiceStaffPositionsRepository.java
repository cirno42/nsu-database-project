package ru.nsu.nikolotov.dbproject.backend.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.nsu.nikolotov.dbproject.backend.entities.ServiceStaffEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.ServiceStaffPositionEntity;

import java.util.List;

@Repository
public class ServiceStaffPositionsRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils repositoryUtils;

    public List<ServiceStaffPositionEntity> getAll() {
        String statementString = "Select * from servicestaffpositions;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(ServiceStaffPositionEntity.class));
    }
}

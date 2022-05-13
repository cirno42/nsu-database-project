package ru.nsu.nikolotov.dbproject.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class RepositoryUtils {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer getNextval(String seqName) {
        String statementString = "Select * from nextval(?);";
        var nextId = jdbcTemplate.queryForObject(statementString, Integer.class, seqName);
        return nextId;
    }

    public Integer getNextId(String tableName, Integer id) {
        String statementString = "Select min(id) from " + tableName + " where id>?";
        var nextId = jdbcTemplate.queryForObject(statementString, Integer.class, Integer.valueOf(id));
        return nextId;
    }

    public <T> T getEntityId(String tableName, Integer id, Class<T> tClass) {
        String statementString = "Select * from " + tableName + " where id = ?";
        List<T> result = jdbcTemplate.query(statementString, new Integer[]{id}, new int[]{Types.INTEGER}, BeanPropertyRowMapper.newInstance(tClass));
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }
}

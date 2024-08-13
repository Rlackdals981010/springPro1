package com.kcm.demo.repository;

import com.kcm.demo.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class ManagerRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ManagerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Manager save(Manager manager) {
        String sql = "INSERT INTO Manager(manId,name,email,createDay,updateDay) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(con->{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, manager.getManId());
            preparedStatement.setString(2, manager.getName());
            preparedStatement.setString(3, manager.getEmail());
            preparedStatement.setDate(4, manager.getCreateDay());
            preparedStatement.setDate(5, manager.getUpdateDay());
            return preparedStatement;
        });

        return manager;
    }
}

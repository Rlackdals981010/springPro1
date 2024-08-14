package com.kcm.demo.repository;

import com.kcm.demo.entity.Event;
import com.kcm.demo.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;


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

    public Manager findById(String manId) {

        String sql = "SELECT name, email,createDay,updateDay from manager where manId=?";

        return jdbcTemplate.query(sql,resultSet->{
            if (resultSet.next()) {
                Manager manager = new Manager();
                manager.setManId(manId);
                manager.setName(resultSet.getString("name"));
                manager.setEmail(resultSet.getString("email"));
                manager.setCreateDay(resultSet.getDate("createDay"));
                manager.setUpdateDay(resultSet.getDate("updateDay"));
                return manager;
            }
            return null;
        }, manId);


    }


    public List<Manager> findAllManager() {
        String sql = "SELECT * from manager ORDER BY manID";

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Manager manager = new Manager();
            manager.setManId(resultSet.getString("manId"));
            manager.setName(resultSet.getString("name"));
            manager.setEmail(resultSet.getString("email"));
            manager.setCreateDay(resultSet.getDate("createDay"));
            manager.setUpdateDay(resultSet.getDate("updateDay"));
            return manager;
        });
    }
}

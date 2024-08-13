package com.kcm.demo.repository;

import com.kcm.demo.dto.EventResponseDto;
import com.kcm.demo.entity.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class EventRepository {

    private final JdbcTemplate jdbcTemplate;

    public EventRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Event save(Event event) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO Event (todo, name, password, createDay, updateDay) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, event.getTodo());
            preparedStatement.setString(2, event.getName());
            preparedStatement.setString(3, event.getPassword());
            preparedStatement.setDate(4, event.getCreateDay());
            preparedStatement.setDate(5, event.getUpdateDay());
            return preparedStatement;
        }, keyHolder);

        Long eventId = keyHolder.getKey().longValue();
        event.setEventId(eventId);

        return event;
    }

    public Event findById(Long eventId) {
        String sql = "SELECT todo, name, createDay, updateDay from event where eventId = ?";

        return jdbcTemplate.query(sql,resultSet->{
            if(resultSet.next()){
                Event event = new Event();
                event.setEventId(eventId);
                event.setTodo(resultSet.getString("todo"));
                event.setName(resultSet.getString("name"));
                event.setCreateDay(resultSet.getDate("createDay"));
                event.setUpdateDay(resultSet.getDate("updateDay"));
                return event;
            }
            else{
                return null;
            }
        },eventId);

    }
}

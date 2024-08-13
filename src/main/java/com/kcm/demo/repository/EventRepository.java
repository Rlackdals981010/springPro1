package com.kcm.demo.repository;

import com.kcm.demo.dto.EventRequestDto;
import com.kcm.demo.entity.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

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
            return null;
        },eventId);

    }

    public List<Event> findByUpdateOrName(Date updateDay, String name) {
        String sql = "SELECT eventId, todo, name, createDay, updateDay from event where updateDay = ? or name = ?";

        // java.util.Date를 java.sql.Date로 변환
        java.sql.Date sqlUpdateDay = new java.sql.Date(updateDay.getTime()); // 필수

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Event event = new Event();
            event.setEventId(resultSet.getLong("eventId"));
            event.setTodo(resultSet.getString("todo"));
            event.setName(resultSet.getString("name"));
            event.setCreateDay(resultSet.getDate("createDay"));
            event.setUpdateDay(resultSet.getDate("updateDay"));
            return event;
        }, sqlUpdateDay, name);
    }


    public void updateById(Long eventId, EventRequestDto eventRequestDto) {
        String sql = "UPDATE event SET todo = ?, name = ? WHERE eventId = ? and password = ?";
        jdbcTemplate.update(sql, eventRequestDto.getTodo(), eventRequestDto.getName(), eventId,eventRequestDto.getPassword());
    }

    public void deleteById(Long eventId) {
        String sql = "DELETE FROM event WHERE eventId =?";
        jdbcTemplate.update(sql, eventId);
    }
}

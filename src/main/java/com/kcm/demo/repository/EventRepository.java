package com.kcm.demo.repository;

import com.kcm.demo.dto.EventRequestDto;
import com.kcm.demo.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

@Repository // 빈 객체
public class EventRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired//생성자 1개일떄 생략 가능
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
        String sql = "SELECT eventId, todo, name, createDay, updateDay from event where updateDay = ? or name = ? ORDER BY updateDay desc";

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


    public Event updateById(Long eventId, EventRequestDto eventRequestDto) {
        String sql;
        Object[] params;

        if (eventRequestDto.getName() == null) {
            sql = "UPDATE event SET todo = ? WHERE eventId = ? and password = ?";
            params = new Object[]{eventRequestDto.getTodo(), eventId, eventRequestDto.getPassword()};
        } else if (eventRequestDto.getTodo() == null) {
            sql = "UPDATE event SET name = ? WHERE eventId = ? and password = ?";
            params = new Object[]{eventRequestDto.getName(), eventId, eventRequestDto.getPassword()};
        } else {
            sql = "UPDATE event SET todo = ?, name = ? WHERE eventId = ? and password = ?";
            params = new Object[]{eventRequestDto.getTodo(), eventRequestDto.getName(), eventId, eventRequestDto.getPassword()};
        }

        jdbcTemplate.update(sql, params);

        String selectSql = "SELECT * FROM event WHERE eventId = ?";
        return jdbcTemplate.queryForObject(selectSql, (resultSet, rowNum) -> {
            Event event = new Event();
            event.setEventId(resultSet.getLong("eventId"));
            event.setTodo(resultSet.getString("todo"));
            event.setName(resultSet.getString("name"));
            event.setCreateDay(resultSet.getDate("createDay"));
            event.setUpdateDay(resultSet.getDate("updateDay"));
            return event;
        }, eventId);
    }



    public void deleteById(Long eventId,EventRequestDto eventRequestDto) {
        String sql = "DELETE FROM event WHERE eventId =? and password=?";
        jdbcTemplate.update(sql, eventId,eventRequestDto.getPassword());
    }
}

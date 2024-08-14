package com.kcm.demo.repository;

import com.kcm.demo.dto.EventRequestDto;
import com.kcm.demo.entity.Event;
import com.kcm.demo.entity.Page;
import com.kcm.demo.exception.IncorrectPasswordException;
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

        String sql = "INSERT INTO Event (todo, manId, password, createDay, updateDay) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, event.getTodo());
            preparedStatement.setString(2, event.getManId());
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
        String sql = "SELECT todo, manId, createDay, updateDay from event where eventId = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Event event = new Event();
                event.setEventId(eventId);
                event.setTodo(resultSet.getString("todo"));
                event.setManId(resultSet.getString("manId"));
                event.setCreateDay(resultSet.getDate("createDay"));
                event.setUpdateDay(resultSet.getDate("updateDay"));
                return event;
            }
            return null;
        }, eventId);

    }

    public List<Event> findByUpdateOrName(Date updateDay, String name) {
        String sql = "SELECT eventId, todo, manId, createDay, updateDay from event where updateDay = ? or manId = ? ORDER BY updateDay desc";

        // java.util.Date를 java.sql.Date로 변환
        java.sql.Date sqlUpdateDay = new java.sql.Date(updateDay.getTime()); // 필수

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Event event = new Event();
            event.setEventId(resultSet.getLong("eventId"));
            event.setTodo(resultSet.getString("todo"));
            event.setManId(resultSet.getString("manId"));
            event.setCreateDay(resultSet.getDate("createDay"));
            event.setUpdateDay(resultSet.getDate("updateDay"));
            return event;
        }, sqlUpdateDay, name);
    }


    public Event updateById(Long eventId, EventRequestDto eventRequestDto) {
        String sql;
        Object[] params;

        String pw = checkPW(eventId);
        if (!pw.equals(eventRequestDto.getPassword())) {
            throw new IncorrectPasswordException("비밀번호가 일치하지 않습니다.");
        }

        if (eventRequestDto.getManId() == null) {
            sql = "UPDATE event SET todo = ? , updateDay =? WHERE eventId = ? and password = ?";
            params = new Object[]{eventRequestDto.getTodo(), eventRequestDto.getUpdateDay(), eventId, eventRequestDto.getPassword()};
        } else if (eventRequestDto.getTodo() == null) {
            sql = "UPDATE event SET manId = ? , updateDay =? WHERE eventId = ? and password = ?";
            params = new Object[]{eventRequestDto.getManId(), eventRequestDto.getUpdateDay(), eventId, eventRequestDto.getPassword()};
        } else {
            sql = "UPDATE event SET todo = ?, manId = ? , updateDay =? WHERE eventId = ? and password = ?";
            params = new Object[]{eventRequestDto.getTodo(), eventRequestDto.getManId(), eventRequestDto.getUpdateDay(), eventId, eventRequestDto.getPassword()};
        }

        jdbcTemplate.update(sql, params);

//        String selectSql = "SELECT * FROM event WHERE eventId = ?";
//        return jdbcTemplate.queryForObject(selectSql, (resultSet, rowNum) -> {
//            Event event = new Event();
//            event.setEventId(resultSet.getLong("eventId"));
//            event.setTodo(resultSet.getString("todo"));
//            event.setManId(resultSet.getString("name"));
//            event.setCreateDay(resultSet.getDate("createDay"));
//            event.setUpdateDay(resultSet.getDate("updateDay"));
//            return event;
//        }, eventId);
        return findById(eventId);
    }


    public void deleteById(Long eventId, EventRequestDto eventRequestDto) {

        String pw = checkPW(eventId);
        if (!pw.equals(eventRequestDto.getPassword())) {
            throw new IncorrectPasswordException("비밀번호가 일치하지 않습니다.");
        }

        //비밀번호 검증
        String sql = "DELETE FROM event WHERE eventId =? and password=?";
        jdbcTemplate.update(sql, eventId, eventRequestDto.getPassword());
    }

    private String checkPW(Long eventId){
        String sql = "SELECT password FROM event WHERE eventId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{eventId}, String.class);
    }


}
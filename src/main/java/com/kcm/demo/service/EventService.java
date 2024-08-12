package com.kcm.demo.service;

import com.kcm.demo.dto.EventRequestDto;
import com.kcm.demo.dto.EventResponseDto;
import com.kcm.demo.entity.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

// 비즈니스 로직 구현
// DB 저장 및 조회는 Repository
public class EventService {

    private final JdbcTemplate jdbcTemplate;

    public EventService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public EventResponseDto createEvent(EventRequestDto eventRequestDto) {
        Event event = new Event(eventRequestDto);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO event (todo,name,password,update_day VALUES(?,?,?,?))";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, event.getTodo());
                    preparedStatement.setString(2, event.getName());
                    preparedStatement.setString(3, event.getPassword());
                    preparedStatement.setDate(4, (Date) event.getUpdateDay());
                    return preparedStatement;
                },
                keyHolder);

        Long id = keyHolder.getKey().longValue();
        event.setEventId(id);

        return new EventResponseDto(event);
    }


    public Event printEventOne(Long eventId,EventRequestDto eventRequestDto) {
        Event event = findById(eventId);
        if(event!=null){
            String sql = "SELECT * FROM Event WHERE eventId = ?";
            try {
                // 단일 결과 queryForObject
                return jdbcTemplate.queryForObject(sql, new Object[]{eventId}, new RowMapper<Event>() {
                    @Override
                    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Event event = new Event();
                        event.setEventId(rs.getLong("eventId"));
                        event.setUpdateDay(rs.getDate("updateDay"));
                        event.setName(rs.getString("name"));
                        return event;
                    }
                });
            } catch (Exception e) {
                // 로그를 추가하거나 다른 오류 처리를 할 수 있습니다.
                throw new IllegalArgumentException("이벤트를 찾을 수 없습니다.");
            }
        }
        else{
            throw new IllegalArgumentException("선택한 일정은 없습니다.");
        }

    }

    public List<Event> printEventAll(EventRequestDto eventRequestDto) {
        String sql = "SELECT * FROM Event WHERE updateDay =? or name =? ";
        List<Event> events = jdbcTemplate.query(sql, new Object[]{eventRequestDto.getUpdateDay(), eventRequestDto.getName()}, new RowMapper<Event>() {
            @Override
            public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
                Event event = new Event();
                event.setEventId(rs.getLong("eventid")); // Assuming there's an 'id' column
                event.setUpdateDay(rs.getDate("updateDay")); // Assuming 'updateDay' column
                event.setName(rs.getString("name")); // Assuming 'name' column
                // Map other fields as necessary
                return event;
            }
        });
        return events;
    }

    public Long updateEvent(Long eventId,EventRequestDto eventRequestDto) {
        Event event = findById(eventId);
        if(event!=null){
            String sql = "UPDATE event SET todo =? ,name=? WHERE eventId =?";
            jdbcTemplate.update(sql, eventRequestDto.getTodo(), eventRequestDto.getName(), eventId);
            return  eventId;
        }
        else{
            throw new IllegalArgumentException("선택한 일정은 없습니다.");
        }
    }

    public Long deleteEvent(Long eventId) {
        Event event = findById(eventId);
        if(event!=null){
            String sql = "DELETE FROM event WHERE eventId=?";
            jdbcTemplate.update(sql, eventId);
            return eventId;
        }
        else{
            throw new IllegalArgumentException("선택한 일정은 없습니다.");
        }
    }

    private Event findById(Long eventId) {
        String sql = "SELECT * FROM Event WHERE eventId = ?";

        return jdbcTemplate.query(sql, resultSet->{
            if(resultSet.next()){
                Event event = new Event();
                event.setTodo(resultSet.getString("todo"));
                event.setName(resultSet.getString("name"));
                event.setUpdateDay(resultSet.getDate("updateDay"));
                return event;
            }
            else {
                return null;
            }
        },eventId);
    }
}

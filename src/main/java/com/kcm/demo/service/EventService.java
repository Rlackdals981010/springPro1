package com.kcm.demo.service;

import com.kcm.demo.dto.EventRequestDto;
import com.kcm.demo.dto.EventResponseDto;
import com.kcm.demo.entity.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

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


    public void printEventOne(Long eventId) {
        Event event = findById(eventId);
        if(event!=null){}
        else{
            throw new IllegalArgumentException("선택한 일정은 없습니다.");
        }


    }

    public void printEventAll() {
    }

    public void updateEvent(Long eventId) {
        Event event = findById(eventId);
        if(event!=null){}
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

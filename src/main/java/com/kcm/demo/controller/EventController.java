package com.kcm.demo.controller;
// Client와 맞닿아 있음
// 요청에 대한 로직 처리는 Service에게 전달, Request 데이터가 있으면 같이 전달
// Service에서 response를 Clinet에게 전달

import com.kcm.demo.dto.EventRequestDto;
import com.kcm.demo.dto.EventResponseDto;
import com.kcm.demo.entity.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

@RestController
@RequestMapping("/events")
public class EventController {

    private final JdbcTemplate jdbcTemplate;

    public EventController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping()
    public EventResponseDto createEvent(@RequestBody EventRequestDto eventRequestDto) {//여기서 request 데이터를 받아야함
        Event event = new Event(eventRequestDto);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO event (todo,name,password,update_day VALUES(?,?,?,?))";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, event.getTodo());
                    preparedStatement.setString(2, event.getName());
                    preparedStatement.setString(3, event.getPassword());
                    preparedStatement.setDate(4, (Date) event.getUpdate_day());
                    return preparedStatement;
                },
                keyHolder);

        Long id = keyHolder.getKey().longValue();
        event.setEvent_id(id);

        EventResponseDto eventResponseDto = new EventResponseDto(event);
        return eventResponseDto;
    }
}

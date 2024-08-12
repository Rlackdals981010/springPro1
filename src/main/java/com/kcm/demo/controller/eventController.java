package com.kcm.demo.controller;


import com.kcm.demo.dto.EventRequestDto;
import com.kcm.demo.dto.EventResponseDto;
import com.kcm.demo.service.EventService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class eventController {

    private final JdbcTemplate jdbcTemplate;

    public eventController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping()
    public EventResponseDto createEvent(@RequestBody EventRequestDto eventRequestDto){
        EventService eventService = new EventService(jdbcTemplate);
        return eventService.createEvent(eventRequestDto);
    }
}

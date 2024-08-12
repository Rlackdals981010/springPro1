package com.kcm.demo.controller;
// Client와 맞닿아 있음
// 요청에 대한 로직 처리는 Service에게 전달, Request 데이터가 있으면 같이 전달
// Service에서 response를 Clinet에게 전달

import com.kcm.demo.dto.EventRequestDto;
import com.kcm.demo.dto.EventResponseDto;
import com.kcm.demo.service.EventService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/events")
public class EventController {

    private final JdbcTemplate jdbcTemplate;

    public EventController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping()
    public EventResponseDto createEvent(@RequestBody EventRequestDto eventRequestDto) {//여기서 request 데이터를 받아야함
        EventService eventService = new EventService(jdbcTemplate);
        return eventService.createEvent(eventRequestDto);
    }

    @GetMapping("{eventid}")
    public void printEventOne(@PathVariable Long eventId) {
        EventService eventService = new EventService(jdbcTemplate);
        return eventService.printEventOne(eventId);
    }

    @GetMapping()
    public void printEventAll() {
        EventService eventService = new EventService(jdbcTemplate);
        return eventService.printEventAll();
    }

    @PutMapping("{eventid}")
    public void updateEvent(@PathVariable Long eventId) {
        EventService eventService = new EventService(jdbcTemplate);
        return eventService.updateEvent(eventId);
    }

    @DeleteMapping("{eventid}")
    public Long deleteEvent(@PathVariable Long eventId) {
        EventService eventService = new EventService(jdbcTemplate);
        return eventService.deleteEvent(eventId);
    }




}

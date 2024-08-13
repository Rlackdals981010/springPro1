package com.kcm.demo.service;

import com.kcm.demo.dto.EventRequestDto;
import com.kcm.demo.dto.EventResponseDto;
import com.kcm.demo.entity.Event;
import com.kcm.demo.repository.EventRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class EventService {

    private final JdbcTemplate jdbcTemplate;

    public EventService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public EventResponseDto createEvent(EventRequestDto eventRequestDto) {
        Event event = new Event(eventRequestDto);

        EventRepository eventRepository = new EventRepository(jdbcTemplate);
        Event saveEvent = eventRepository.save(event);

        return new EventResponseDto(saveEvent);
    }

    public EventResponseDto selectEvent(Long eventId) {
        EventRepository eventRepository = new EventRepository(jdbcTemplate);
        Event selectEvent = eventRepository.findById(eventId);

        return new EventResponseDto(selectEvent);
    }

    public List<Event> selectEvents(EventRequestDto eventRequestDto) {
        EventRepository eventRepository = new EventRepository(jdbcTemplate);

        return eventRepository.findByUpdateOrName(eventRequestDto.getUpdateDay(),eventRequestDto.getName());
    }
}

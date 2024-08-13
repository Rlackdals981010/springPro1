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

    public Long updateEvent(Long eventId, EventRequestDto eventRequestDto) {
        EventRepository eventRepository = new EventRepository(jdbcTemplate);
        //여기서 걸러야함.
        Event updateEvent = eventRepository.findById(eventId);
        if(updateEvent!=null){
            eventRepository.updateById(eventId, eventRequestDto);
            return eventId;
        }
        else {
            throw new IllegalArgumentException("존재하지 않는 일정입니다.");
        }
    }

    public Long deleteEvent(Long eventId) {
        EventRepository eventRepository = new EventRepository(jdbcTemplate);

        Event updateEvent = eventRepository.findById(eventId);
        if(updateEvent!=null){
            eventRepository.deleteById(eventId);
            return eventId;
        }
        else {
            throw new IllegalArgumentException("존재하지 않는 일정입니다.");
        }

    }
}

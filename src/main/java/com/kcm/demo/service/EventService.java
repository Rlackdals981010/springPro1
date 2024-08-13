package com.kcm.demo.service;

import com.kcm.demo.dto.EventRequestDto;
import com.kcm.demo.dto.EventResponseDto;
import com.kcm.demo.entity.Event;
import com.kcm.demo.repository.EventRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class EventService {

    private final EventRepository eventRepository;

    public EventService(JdbcTemplate jdbcTemplate) {
        this.eventRepository = new EventRepository(jdbcTemplate);
    }


    public EventResponseDto createEvent(EventRequestDto eventRequestDto) {
        Event event = new Event(eventRequestDto);

        Event saveEvent = eventRepository.save(event);

        return new EventResponseDto(saveEvent);
    }

    public EventResponseDto selectEvent(Long eventId) {

        Event selectEvent = eventRepository.findById(eventId);
        if(selectEvent!=null){
            return new EventResponseDto(selectEvent);
        }
        else{
            throw new IllegalArgumentException("존재하지 않는 일정입니다.");
        }

    }

    public List<Event> selectEvents(EventRequestDto eventRequestDto) {

        List<Event> eventList = eventRepository.findByUpdateOrName(eventRequestDto.getUpdateDay(),eventRequestDto.getName());

        if(eventList!=null){
            return eventList;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public Long updateEvent(Long eventId, EventRequestDto eventRequestDto) {
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

    public Long deleteEvent(Long eventId,EventRequestDto eventRequestDto) {

        Event deleteEvent = eventRepository.findById(eventId);
        if(deleteEvent!=null){
            eventRepository.deleteById(eventId,eventRequestDto);
            return eventId;
        }
        else {
            throw new IllegalArgumentException("존재하지 않는 일정입니다.");
        }

    }
}

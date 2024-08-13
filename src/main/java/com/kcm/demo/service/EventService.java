package com.kcm.demo.service;

import com.kcm.demo.dto.EventRequestDto;
import com.kcm.demo.dto.EventResponseDto;
import com.kcm.demo.entity.Event;
import com.kcm.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // bean 객체 등록 . 즉, bean 클래스로 설정한다는 것.
public class EventService { //eventService라는 이름으로 IoC 컨테이너에 빈 등록

    private final EventRepository eventRepository;

    @Autowired//생성자 1개일떄 생략 가능
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
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

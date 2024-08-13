package com.kcm.demo.controller;


import com.kcm.demo.dto.EventRequestDto;
import com.kcm.demo.dto.EventResponseDto;
import com.kcm.demo.entity.Event;
import com.kcm.demo.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class eventController {

    private final  EventService eventService;

    public eventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping()
    public EventResponseDto createEvent(@RequestBody EventRequestDto eventRequestDto){

        return eventService.createEvent(eventRequestDto);
    }

    @GetMapping("/{eventId}")
    public EventResponseDto selectEvent(@PathVariable Long eventId) {

        return eventService.selectEvent(eventId);
    }

    @GetMapping()
    public List<Event> selectEvents(@RequestBody EventRequestDto eventRequestDto){

        return eventService.selectEvents(eventRequestDto);
    }

    @PutMapping("/{eventId}")
    public Long updateEvent(@PathVariable Long eventId, @RequestBody EventRequestDto eventRequestDto){

        return eventService.updateEvent(eventId,eventRequestDto);
    }

    @DeleteMapping("/{eventId}")
    public Long deleteEvent(@PathVariable Long eventId,@RequestBody EventRequestDto eventRequestDto){

        return eventService.deleteEvent(eventId,eventRequestDto);
    }

}

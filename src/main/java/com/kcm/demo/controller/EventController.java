package com.kcm.demo.controller;


import com.kcm.demo.dto.EventRequestDto;
import com.kcm.demo.dto.EventResponseDto;
import com.kcm.demo.entity.Event;
import com.kcm.demo.service.EventService;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/events")
@Validated
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping()
    public EventResponseDto createEvent(@Valid @RequestBody EventRequestDto eventRequestDto) {

        return eventService.createEvent(eventRequestDto);
    }

    @GetMapping("/{eventId}")
    public EventResponseDto selectEvent(@PathVariable Long eventId) {

        return eventService.selectEvent(eventId);
    }

    @GetMapping()
    public List<Event> selectEvents(
            @RequestParam(name = "manId") String manId,
            @RequestParam(name = "updateDay") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date updateDay){
        return eventService.selectEvents(updateDay,manId);
    }

    @PostMapping("/{eventId}")
    public EventResponseDto updateEvent(@PathVariable Long eventId, @Valid @RequestBody EventRequestDto eventRequestDto) {

        return eventService.updateEvent(eventId, eventRequestDto);
    }

    @DeleteMapping("/{eventId}")
    public Long deleteEvent(@PathVariable Long eventId, @RequestBody EventRequestDto eventRequestDto) {

        return eventService.deleteEvent(eventId, eventRequestDto);
    }


}
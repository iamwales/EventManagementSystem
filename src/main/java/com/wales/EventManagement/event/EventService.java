package com.wales.EventManagement.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    public EventResponse createEvent(EventRequest eventRequest) {
        Event mainEvent = eventMapper.toEvent(eventRequest);


        var event = eventRepository.save(mainEvent);


        return eventMapper.toEventResponse(event);
    }

    public EventStartResponse createEventStart(String name) {
        var mainEvent = new Event(name);

        var event = eventRepository.save(mainEvent);

        return eventMapper.toEventStartResponse(event);
    }
}

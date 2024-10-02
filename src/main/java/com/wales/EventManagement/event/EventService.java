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

    System.out.println("mainEvent "+ mainEvent);

        var event = eventRepository.save(mainEvent);

    System.out.println("event "+ event);

        return eventMapper.toEventResponse(event);
    }
}

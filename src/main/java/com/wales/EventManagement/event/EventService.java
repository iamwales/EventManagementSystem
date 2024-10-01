package com.wales.EventManagement.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    public EventResponse createEvent(EventRequest eventRequest) {
        var event = eventRepository.save(eventMapper.toEvent(eventRequest));

        return eventMapper.toEventResponse(event);
    }


}

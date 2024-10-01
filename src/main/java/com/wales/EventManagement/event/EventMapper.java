package com.wales.EventManagement.event;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface EventMapper {
    EventResponse toEventResponse(Event event);

    Event toEvent(EventRequest eventRequest);
}

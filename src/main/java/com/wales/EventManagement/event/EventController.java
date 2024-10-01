package com.wales.EventManagement.event;

import com.wales.EventManagement.session.Session;
import com.wales.EventManagement.session.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.query.ScrollSubrange;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Controller
public class EventController {

    private final EventRepository eventRepository;
    private final SessionRepository sessionRepository;

    private final EventService eventService;

    @MutationMapping
    EventResponse createEvent(@Argument EventRequest eventRequest) {
        return eventService.createEvent(eventRequest);
    }

    @QueryMapping
    List<Event> events() {
       return eventRepository.findAll();
    }

    @QueryMapping
    Optional<Event> event(@Argument UUID uuid) {
        return eventRepository.findById(uuid);
    }

    @SchemaMapping
    Window<Session> sessions(Event event, ScrollSubrange subrange) {
        ScrollPosition scrollPosition = subrange.position().orElse(ScrollPosition.offset());
        Limit limit = Limit.of(subrange.count().orElse(10));
        Sort sort = Sort.by("title").ascending();

        return sessionRepository.findByEventUuid(event.getUuid(), scrollPosition, limit, sort);
    }
}

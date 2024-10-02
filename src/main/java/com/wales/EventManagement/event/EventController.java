package com.wales.EventManagement.event;

import com.wales.EventManagement.session.Session;
import com.wales.EventManagement.session.SessionRepository;
import com.wales.EventManagement.user.User;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Controller
@EnableMethodSecurity
public class EventController {

    private final EventRepository eventRepository;
    private final SessionRepository sessionRepository;

    private final EventService eventService;

    @MutationMapping
    EventResponse createEvent(@Argument EventRequest eventRequest) {
        return eventService.createEvent(eventRequest);
    }

    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    List<Event> events(Authentication connectedUser) {
    System.out.println("connectedUser " + connectedUser);

    var user = ((User) connectedUser.getPrincipal());

    System.out.println("Connected Main User " + user);

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

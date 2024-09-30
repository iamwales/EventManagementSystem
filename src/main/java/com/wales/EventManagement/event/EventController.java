package com.wales.EventManagement.event;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Controller
public class EventController {

    private final EventRepository eventRepository;

    @QueryMapping
    List<Event> events() {
       return eventRepository.findAll();
    }

    @QueryMapping
    Optional<Event> event(@Argument UUID uuid) {
        return eventRepository.findById(uuid);
    }
}

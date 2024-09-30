package com.wales.EventManagement.session;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Controller
public class SessionController {
    private final SessionRepository sessionRepository;

    @QueryMapping
    List<Session> sessions() {
        return sessionRepository.findAll();
    }

    @QueryMapping
    Optional<Session> session(@Argument UUID uuid) {
        return sessionRepository.findById(uuid);
    }

}

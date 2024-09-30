package com.wales.EventManagement.speaker;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Controller
public class SpeakerController {

    private final SpeakerRepository speakerRepository;

    @QueryMapping
    List<Speaker> speakers() {
        return speakerRepository.findAll();
    }

    @QueryMapping
    Optional<Speaker> speaker(@Argument UUID uuid) {
        return speakerRepository.findById(uuid);
    }

}

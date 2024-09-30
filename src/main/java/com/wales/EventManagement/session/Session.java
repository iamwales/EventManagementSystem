package com.wales.EventManagement.session;

import com.wales.EventManagement.event.Event;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Level level;

    // TAGS
    @OneToMany
    private Set<Tag> tags = new HashSet<>();

    // EVENT
    @ManyToOne
    private Event event;
}

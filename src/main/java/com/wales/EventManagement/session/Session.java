package com.wales.EventManagement.session;

import com.wales.EventManagement.event.Event;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
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

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Level level;

    // TAGS
    @OneToMany
    private List<Tag> tags;

    // EVENT
    @ManyToOne
    private Event event;

    public Session(String title, String description, Level level, List<Tag> tags, Event event) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.tags = tags;
        this.event = event;
    }
}

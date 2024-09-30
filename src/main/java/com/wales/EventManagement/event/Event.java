package com.wales.EventManagement.event;

import com.wales.EventManagement.session.Session;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime cfpStartDate;

    private LocalDateTime cfpEndDate;

    private String location;

    private String website;

    // SESSIONS
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Session> sessions = new HashSet<>();
}

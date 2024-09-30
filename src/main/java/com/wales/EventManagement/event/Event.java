package com.wales.EventManagement.event;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
public class Event {
    @Id
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
}

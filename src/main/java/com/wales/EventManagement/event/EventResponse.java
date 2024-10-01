package com.wales.EventManagement.event;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
    private UUID uuid;

    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime cfpStartDate;

    private LocalDateTime cfpEndDate;

    private String location;

    private String website;
}

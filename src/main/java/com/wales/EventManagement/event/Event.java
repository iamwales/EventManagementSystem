package com.wales.EventManagement.event;

import com.wales.EventManagement.common.BaseEntity;
import com.wales.EventManagement.session.Session;
import com.wales.EventManagement.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
@Entity
public class Event extends BaseEntity {
    private String name;

    @Column(length = 1000)
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

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    public Event(String name, String description, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime cfpStartDate, LocalDateTime cfpEndDate, String location, String website) {
        this.name = name;

        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cfpStartDate = cfpStartDate;
        this.cfpEndDate = cfpEndDate;
        this.location = location;
        this.website = website;
    }
}

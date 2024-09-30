package com.wales.EventManagement.session;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

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
    private UUID uuid;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Level level;

    // TAGS

    // EVENT
}

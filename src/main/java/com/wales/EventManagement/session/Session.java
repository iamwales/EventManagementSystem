package com.wales.EventManagement.session;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Level level;

    // TAGS

    // EVENT
}

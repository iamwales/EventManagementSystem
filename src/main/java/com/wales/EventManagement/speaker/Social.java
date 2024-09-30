package com.wales.EventManagement.speaker;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Social {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String name;

    private String url;

    @ManyToOne()
    @JoinColumn(name = "speaker_uuid")
    private Speaker speaker;
}

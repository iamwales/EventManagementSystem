package com.wales.EventManagement.speaker;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String name;

    private String title;

    private String company;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    private String country;

    private String email;

    private String phoneNumber;

    @OneToMany(mappedBy = "speaker-uuid")
    private List<Social> socials;
}

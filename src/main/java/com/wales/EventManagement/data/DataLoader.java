package com.wales.EventManagement.data;


import com.github.javafaker.Faker;
import com.wales.EventManagement.event.Event;
import com.wales.EventManagement.event.EventRepository;
import com.wales.EventManagement.session.*;
import com.wales.EventManagement.speaker.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final EventRepository eventRepository;
    private final SpeakerRepository speakerRepository;
    private final SessionRepository sessionRepository;
    private final TagRepository tagRepository;
    private final Faker faker;
    private final SocialRepository socialRepository;

    public DataLoader(EventRepository eventRepository, SpeakerRepository speakerRepository, SessionRepository sessionRepository, TagRepository tagRepository, Faker faker, SocialRepository socialRepository) {
        this.eventRepository = eventRepository;
        this.speakerRepository = speakerRepository;
        this.sessionRepository = sessionRepository;
        this.tagRepository = tagRepository;
        this.faker = faker;
        this.socialRepository = socialRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if(tagRepository.count() == 0) {
            Tag springBootTag = new Tag("Spring Boot");
            Tag springFrameworkTag = new Tag("Spring Framework");
            tagRepository.saveAll(List.of(springBootTag,springFrameworkTag));
        }

        if(eventRepository.count() == 0) {

            var event = new Event(
                    "SpringOne at VMware Explore",
                    "Join us at the biggest gathering of Spring enthusiasts, beginners and practitioners who build the apps that make the world run. This year, we are excited to have the developer-focused SpringOne event unite with the incredible VMware Explore 2023 in Las Vegas. Learn how Spring can take you from code to production at speed with its support for modern application architectures that run seamlessly on any cloud.",
                    LocalDateTime.now().minusMonths(5),
                    LocalDateTime.now().minusWeeks(9),
                    LocalDateTime.now().minusDays(180),
                    LocalDateTime.now().minusDays(90),
                    "Las Vegas, NV",
                    "https://springone.io/");

            eventRepository.save(event);


            List<Speaker> speakers = new ArrayList<>();
            for (int i = 1; i <= 20; i++) {
                Social social = socialRepository.save(new Social("twitter", faker.name().username()));
                speakers.add(new Speaker(
                        faker.name().fullName(),
                        faker.name().title(),
                        faker.company().name(),
                        Gender.values()[faker.number().numberBetween(0,Gender.values().length)],
                        faker.address().country(),
                        faker.internet().emailAddress(),
                        faker.phoneNumber().phoneNumber(),
                        List.of(social)
                        ));

            }
            speakerRepository.saveAll(speakers);

            Tag springBootTag = new Tag("Spring Boot");



            List<Session> sessions = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                Tag springFramework = new Tag(faker.programmingLanguage().name());
                Tag tag = tagRepository.save(springFramework);
                sessions.add(new Session(
                        faker.book().title(),
                        faker.lorem().paragraph(),
                        Level.values()[faker.number().numberBetween(0, Level.values().length)],
                        List.of(tag),
                        event));
            }
            sessionRepository.saveAll(sessions);

        }

    }

    private Speaker randomSpeaker(List<Speaker> speakers) {
        return speakers.get(faker.number().numberBetween(0, speakers.size() - 1));
    }

}

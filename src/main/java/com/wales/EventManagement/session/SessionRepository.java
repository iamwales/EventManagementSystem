package com.wales.EventManagement.session;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    Window<Session> findByEventUuid(UUID eventUuid, ScrollPosition position, Limit limit, Sort sort);
}

package com.wales.EventManagement.speaker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SocialRepository extends JpaRepository<Social, UUID> {
}

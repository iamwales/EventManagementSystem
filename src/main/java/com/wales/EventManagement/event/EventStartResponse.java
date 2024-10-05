package com.wales.EventManagement.event;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EventStartResponse(UUID uuid, String name) {}

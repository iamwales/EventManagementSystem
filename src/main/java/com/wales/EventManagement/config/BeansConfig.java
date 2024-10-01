package com.wales.EventManagement.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.UUID;

@Configuration
@AllArgsConstructor
public class BeansConfig {


    @Bean
    public AuditorAware<UUID> auditorAware() {
        return new ApplicationAuditAware();
    }


}

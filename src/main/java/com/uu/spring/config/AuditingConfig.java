package com.uu.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

public class AuditingConfig {
    @Bean
    public AuditorAware<String> myAuditorProvider() {
        return new AuditorAwareImpl();
    }
}

package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * Аннотация @EnableJpaAuditing нужна, чтобы включить наш аудит сущностей
 */
@Configuration
@EnableJpaAuditing
public class AuditConfiguration {

    /**
     * Этот метод нужен чтобы мы могли заполнять в нашем аудите кто создавал или изменял сущность
     */
    @Bean
    public AuditorAware<String> auditorAware() {
//        SecurityContext.getCurrentUser().getEmail();    // вот так может быть реализовано в реально проекте
        return () -> Optional.of("Lirik"); // тут мы просто хардкодим какое-то имя и все
    }

}

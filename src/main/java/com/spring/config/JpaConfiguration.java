package com.spring.config;

import com.spring.config.condition.JpaCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Аннотация @Conditional позволяет гибко подходить к созданию конфигураций. В данном случае, конфигурация для JpaConfiguration
 * сработает, только если в классе JpaCondition выполнится условие и будет найден postgres драйвер.
 */

@Conditional(JpaCondition.class)
@Configuration
public class JpaConfiguration {

    @PostConstruct
    void init() {
        System.out.println("Jpa configuration is enabled");
    }
}

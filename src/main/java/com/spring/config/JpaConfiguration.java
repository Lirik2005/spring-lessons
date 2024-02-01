package com.spring.config;

import com.spring.config.condition.JpaCondition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
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

    /**
     * Метод ниже нужен, что смапить JpaConfiguration на DatabaseProperties.class.
     * Префикс указываем такой же, как и в application.yaml.
     * Также это можно и не делать, а указать @ConfigurationProperties(prefix = "database") прямо над DatabaseProperties.class и повесив
     * аннотацию @Component.
     * Вместо @Component над классом DatabaseProperties.class можно поставить аннотацию @ConfigurationPropertiesScan над классом
     * ApplicationRunner.class
     *
     */
//    @Bean
//    @ConfigurationProperties(prefix = "database")
//    public DatabaseProperties databaseProperties() {
//        return new DatabaseProperties();
//    }

    @PostConstruct
    void init() {
        System.out.println("Jpa configuration is enabled");
    }
}

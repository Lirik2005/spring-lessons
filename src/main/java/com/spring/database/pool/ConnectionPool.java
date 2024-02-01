package com.spring.database.pool;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Аннотация @Component используется для того, чтобы аннотация @Autowired могла создать бин данного класса. Если в аннотации @Component
 * не указать value (которое в скобках), то id бина будет соответствовать названию класса.
 */

@Component("pool1")
@RequiredArgsConstructor
public class ConnectionPool {

    @Value("${db.username}")
    private final String username;

    @Value("${db.pool.size}")
    private final Integer poolSize;

    /**
     * Вызывается сразу после конструкторов и сеттеров
     */
    @PostConstruct
    private void init() {
        System.out.println("Init connection pool");
    }

    /**
     * Данный метод будет вызываться только у бинов, которые singleton, потому что бины prototype не хранятся в application context
     */
    @PreDestroy
    private void destroy() {
        System.out.println("Clean connection pool");
    }
}

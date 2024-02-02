package com.spring.integration;

import com.spring.database.pool.ConnectionPool;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.SpyBean;

/**
 * Данный класс нам нужен для того, чтобы настроить Spring и сказать ему поднимать только один контекст для всех тестов.
 * Чтобы использовать этот класс во всех наших интеграционных тестах его надо указать в нашей аннотации @IT, добавив к имеющейся
 * там аннотации @SpringBootTest(classes = TestApplicationRunner.class).
 */

@TestConfiguration
public class TestApplicationRunner {

    @SpyBean(name = "pool1")
    private ConnectionPool pool1;

}
